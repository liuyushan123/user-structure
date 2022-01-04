package com.delicloud.service.impl;

import com.delicloud.dto.req.DepartmentReq;
import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.platform.v2.common.lang.util.PropertyCopyUtil;
import com.delicloud.repository.CompanyRepository;
import com.delicloud.repository.DepartmentEmployRepository;
import com.delicloud.repository.DepartmentRepository;
import com.delicloud.repository.EmployRepository;
import com.delicloud.service.DepartmentService;
import com.delicloud.vo.DepartmentVo;
import com.delicloud.vo.EmployVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyushan
 * Date: 2021/12/30
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentEmployRepository departmentEmployRepository;
    @Autowired
    private EmployRepository employRepository;

    @Override
    public void createDepartmentList(Long companyId, Long parentDepartment, Integer count) {
        Department department = departmentRepository.findById(parentDepartment).orElseThrow(() -> new RuntimeException("未找到部门"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("未找到公司"));
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            departments.add(new Department(company.getId(), department.getId(), "部门" + (i + 1)));
        }
        departmentRepository.saveAll(departments);
    }

    @Override
    public DepartmentVo query(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("未找到部门"));
        Company company = companyRepository.findById(department.getCompanyId()).orElseThrow(() -> new RuntimeException("未找到公司"));
        DepartmentVo departmentVo = PropertyCopyUtil.copyProperties(department, DepartmentVo.class);
        departmentVo.setCompanyName(company.getName());
        List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByDepartmentId(departmentVo.getId());
        if (!departmentEmploys.isEmpty()) {
            List<Long> employId = departmentEmploys.stream().map(DepartmentEmploy::getEmployId).collect(Collectors.toList());
            List<Employ> employs = employRepository.findAllById(employId);
            List<EmployVo> employVos = PropertyCopyUtil.copyCollectionProperties(employs, EmployVo.class);
            departmentVo.setEmploys(employVos);
        }
        return departmentVo;
    }

    @Override
    public Department queryOne(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("没有该部门"));
        return department;
    }

    @Override
    @Transactional
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("未找到公司"));
        List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByDepartmentId(departmentId);
        departmentEmployRepository.deleteAll(departmentEmploys);
        departmentRepository.delete(department);
    }

    @Override
    public Department update(Long departmentId, DepartmentReq departmentReq) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("未找到部门"));
        Assert.isTrue(department.getCompanyId().equals(departmentReq.getCompanyId()),"不让更改公司");
        Assert.isTrue(departmentId.equals(department.getId()),"不让更改部门");
        department.setName(departmentReq.getName());
        department.setParentId(departmentReq.getParentId());
        return departmentRepository.save(department);
    }
}
