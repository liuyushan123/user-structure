package com.delicloud.service.impl;

import com.delicloud.dao.CompanyRepository;
import com.delicloud.dao.DepartmentRepository;
import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyushan
 * Date: 2021/12/30
 */
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

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
    public void query() {
        List<Department> departments = departmentRepository.findAll();
        List<Long> collect = departments.stream().map(Department::getCompanyId).distinct().collect(Collectors.toList());
        List<Company> companies = companyRepository.findAllById(collect);

    }
}
