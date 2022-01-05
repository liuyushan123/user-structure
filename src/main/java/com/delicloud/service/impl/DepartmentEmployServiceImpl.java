package com.delicloud.service.impl;

import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.repository.CompanyRepository;
import com.delicloud.repository.DepartmentEmployRepository;
import com.delicloud.repository.DepartmentRepository;
import com.delicloud.service.DepartmentEmployService;
import com.delicloud.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
@Service
public class DepartmentEmployServiceImpl implements DepartmentEmployService {
    @Autowired
    private EmployService employService;
    @Autowired
    private DepartmentEmployRepository departmentEmployRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void createDepartUser(Long companyId, Long departmentId, Integer count) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("未找到部门"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("未找到公司"));
        Assert.isTrue(department.getCompanyId().equals(company.getId()), "部门不在公司下面");
        List<Employ> employList = employService.createUserList(count);
        List<DepartmentEmploy> departmentEmploys = new ArrayList<>();
        employList.forEach(t -> {
            departmentEmploys.add(new DepartmentEmploy(companyId, departmentId, t.getId()));
        });
        departmentEmployRepository.saveAll(departmentEmploys);
    }

    @Override
    public void query() {

    }

    @Override
    public List<DepartmentEmploy> queryUsers(Long employId) {
        List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByEmployId(employId);
        return departmentEmploys;
    }
}
