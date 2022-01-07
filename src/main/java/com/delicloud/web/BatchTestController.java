package com.delicloud.web;

import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.platform.v2.common.lang.bo.page.RespPage;
import com.delicloud.platform.v2.common.lang.exception.PlatformException;
import com.delicloud.repository.CompanyRepository;
import com.delicloud.repository.DepartmentEmployRepository;
import com.delicloud.repository.DepartmentRepository;
import com.delicloud.repository.EmployRepository;
import com.delicloud.request.entity.Arch;
import com.delicloud.request.entity.Node;
import com.delicloud.request.entity.Record;
import com.delicloud.request.entity.User;
import com.delicloud.util.SystemButtJoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author liuyushan
 * Date: 2022/1/6
 * 因为希望快点开发在这里把service层省略了
 */
@Slf4j
@RestController
@RequestMapping("batch")
public class BatchTestController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployRepository employRepository;
    @Autowired
    private DepartmentEmployRepository departmentEmployRepository;


    private static String putArch = "/openApi/v1.0/arch/%s";
    private static String putNode = "/openApi/v1.0/arch/%s/node/%s";
    private static String putUser = "/openApi/v1.0/user/%s";
    private static String queryUser = "/openApi/v1.0/user/page?page=%s&size=%s";
    private static String assRecord = "/openApi/v1.0/ass/record?next_id=%s&page_size=%s&real_data_only=%s";
    private static String acsRecord = "/openApi/v1.0/acs/record?next_id=%s&page_size=%s&pass_only=%s";

    @Autowired
    private SystemButtJoint systemButtJoint;

    /**
     * 创建所有人员
     * @return
     */
    @PostMapping
    public RespBase<Void> batchCreate() {
        //创建架构
        List<Company> companys = companyRepository.findAll();
        for (Company company : companys) {
            if (Objects.equals(company.getId(), 0L)) {
                continue;
            }
            Arch arch = Arch.builder().name(company.getName()).build();
            systemButtJoint.sendMessage(HttpMethod.PUT, String.format(putArch, company.getId()), SystemButtJoint.archOperate, arch);
        }
        //创建节点
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (Objects.equals(department.getId(), 0L)) {
                continue;
            }
            Node node = Node.builder().parentNodeId(String.valueOf(department.getParentId())).name(department.getName()).build();
            systemButtJoint.sendMessage(HttpMethod.PUT, String.format(putNode, department.getCompanyId(), department.getId()), SystemButtJoint.archOperate, node);
        }
        //床架人员
        List<Employ> employs = employRepository.findAll();
        for (Employ employ : employs) {
            List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByEmployId(employ.getId());
            User user = User.createUser(employ, departmentEmploys);
            systemButtJoint.sendMessage(HttpMethod.PUT, String.format(putUser, employ.getJobNumber()), SystemButtJoint.userOperate, user);
        }
        return RespBase.OK_RESP_BASE;
    }

    @PutMapping
    public RespBase<Void> batchUpdate() {
        List<Company> orginCompanys = companyRepository.findAll();
        orginCompanys.forEach(t -> t.setName(t.getName() + "$"));
        List<Company> updateCompanies = companyRepository.saveAll(orginCompanys);
        for (Company company : updateCompanies) {
            if (Objects.equals(company.getId(), 0L)) {
                continue;
            }
            Arch arch = Arch.builder().name(company.getName()).build();
            systemButtJoint.sendMessage(HttpMethod.PUT, String.format(putArch, company.getId()), SystemButtJoint.archOperate, arch);
        }
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(t -> t.setName(t.getName() + "$"));
        List<Department> updateDepartments = departmentRepository.saveAll(departments);
        for (Department department : updateDepartments) {
            if (Objects.equals(department.getId(), 0L)) {
                continue;
            }
            Node node = Node.builder().parentNodeId(String.valueOf(department.getParentId())).name(department.getName()).build();
            systemButtJoint.sendMessage(HttpMethod.PUT, String.format(putNode, department.getCompanyId(), department.getId()), SystemButtJoint.archOperate, node);
        }

        List<Employ> employs = employRepository.findAll();
        employs.forEach(t -> t.setName(t.getName() + "$"));
        List<Employ> updateEmploys = employRepository.saveAll(employs);
        for (Employ employ : updateEmploys) {
            List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByEmployId(employ.getId());
            User user = User.createUser(employ, departmentEmploys);
            systemButtJoint.sendMessage(HttpMethod.PUT, String.format(putUser, employ.getJobNumber()), SystemButtJoint.userOperate, user);
        }
        return RespBase.OK_RESP_BASE;
    }

    @DeleteMapping("arch")
    public RespBase<Void> batchDeleteArch() {
        List<Employ> employs = employRepository.findAll();
        for (Employ employ : employs) {
            systemButtJoint.sendMessage(HttpMethod.DELETE, String.format(putUser, employ.getJobNumber()), SystemButtJoint.userOperate, null);
        }
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (Objects.equals(department.getId(), 0L)) {
                continue;
            }
            systemButtJoint.sendMessage(HttpMethod.DELETE, String.format(putArch, department.getId()), SystemButtJoint.archOperate, null);
        }
        List<Company> companys = companyRepository.findAll();
        for (Company company : companys) {
            if (Objects.equals(company.getId(), 0L)) {
                continue;
            }
            systemButtJoint.sendMessage(HttpMethod.DELETE, String.format(putArch, company.getId()), SystemButtJoint.archOperate, null);
        }
        return RespBase.OK_RESP_BASE;
    }

    @GetMapping("acsRecord")
    public RespBase<List<Object>> acsRecord() {
        List<Object> objects = queryAcsRecord();
        return new RespBase<List<Object>>(objects);
    }

    @GetMapping("assRecord")
    public RespBase<List<Object>> assRecord() {
        List<Object> objects = queryAssRecord();
        return new RespBase<List<Object>>(objects);
    }

    @GetMapping("checkUser")
    public RespBase<List<Map<String, Object>>> checkUser() {
        List<Map<String, Object>> maps = queryAllUser();
        return new RespBase<List<Map<String, Object>>>(maps);
    }

    private List<Map<String,Object>> queryAllUser(){
        int page = 1;
        List<Map<String, Object>> list = new ArrayList<>();
        while (true){
            ResponseEntity<RespPage<Map<String, Object>>> response = systemButtJoint.sendMessage(HttpMethod.GET, String.format(queryUser, page, 1000), SystemButtJoint.userQuery , null, new ParameterizedTypeReference<RespPage<Map<String, Object>>>() {
            });
            RespPage<Map<String, Object>> body = response.getBody();
            if (body.getTotalPage() <= page){
                break;
            }
            list.addAll(body.getRows());
            page++;
        }
        return list;
    }

    /**
     * 返回所有的考勤记录，如果要详细类型可以把Object换成别的
     * @return
     */
    private List<Object> queryAssRecord(){
        String nextId = "0";
        List<Object> list = new ArrayList<>();
        while (true){
            ResponseEntity<RespBase<Record<Object>>> response = systemButtJoint.sendMessage(HttpMethod.GET, String.format(assRecord, nextId, 500, true), SystemButtJoint.checkinQuery, null, new ParameterizedTypeReference<RespBase<Record<Object>>>() {
            });
            RespBase<Record<Object>> body = response.getBody();
            Record record = null;
            try {
                record = body.validateAndReturn();
            } catch (PlatformException e) {
                e.printStackTrace();
            }
            list.addAll(record.getData());
            if (record.getData().isEmpty()){
                break;
            }
            nextId = record.getNextId();
        }
        return list;
    }

    /**
     * 返回通行记录，如果要详细类型可以把Object换成别的
     * @return
     */
    private List<Object> queryAcsRecord(){
        String nextId = "0";
        List<Object> list = new ArrayList<>();
        while (true){
            ResponseEntity<RespBase<Record<Object>>> response = systemButtJoint.sendMessage(HttpMethod.GET, String.format(acsRecord, nextId, 500, true), SystemButtJoint.checkinAcsQuery, null, new ParameterizedTypeReference<RespBase<Record<Object>>>() {
            });
            RespBase<Record<Object>> body = response.getBody();
            Record record = null;
            try {
                record = body.validateAndReturn();
            } catch (PlatformException e) {
                e.printStackTrace();
            }
            list.addAll(record.getData());
            if (record.getData().isEmpty()){
                break;
            }
            nextId = record.getNextId();
        }
        return list;
    }


}
