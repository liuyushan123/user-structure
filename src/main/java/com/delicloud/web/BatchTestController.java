package com.delicloud.web;

import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.platform.v2.common.lang.bo.page.RespPage;
import com.delicloud.repository.CompanyRepository;
import com.delicloud.repository.DepartmentEmployRepository;
import com.delicloud.repository.DepartmentRepository;
import com.delicloud.repository.EmployRepository;
import com.delicloud.request.entity.Arch;
import com.delicloud.request.entity.Node;
import com.delicloud.request.entity.User;
import com.delicloud.util.SystemButtJoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

/**
 * @author liuyushan
 * Date: 2022/1/6
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
    private static String queryUser = "/user/page?page=%s&size=%s";

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

    public void equalsUserCount(){
        Integer page = 1;
        List list = new ArrayList();
        while (true){
            ResponseEntity<RespPage> response = systemButtJoint.sendMessagePage(HttpMethod.GET, String.format(queryUser, page, 1000), SystemButtJoint.userQuery, null);
            RespPage body = response.getBody();
            list.addAll(body.getRows());
            if (body.getTotalPage() >= page){
                break;
            }

        }
    }


}
