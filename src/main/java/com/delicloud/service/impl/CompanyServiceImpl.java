package com.delicloud.service.impl;

import com.delicloud.dto.req.CompanyReq;
import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.platform.v2.common.lang.util.PropertyCopyUtil;
import com.delicloud.repository.CompanyRepository;
import com.delicloud.repository.DepartmentEmployRepository;
import com.delicloud.repository.DepartmentRepository;
import com.delicloud.service.CompanyService;
import com.delicloud.util.TreeUtils;
import com.delicloud.vo.CompanyDetailVo;
import com.delicloud.vo.CompanyTreeVo;
import com.delicloud.vo.CompanyVo;
import com.delicloud.vo.DepartmentTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentEmployRepository departmentEmployRepository;

    @Override
    public List<CompanyVo> createCompanyList(Long companyId, Integer count) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("公司不存在"));
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            companies.add(new Company(company.getId(), "公司" + (i + 1)));
        }
        List<Company> saveAll = companyRepository.saveAll(companies);
        List<CompanyVo> companyVos = PropertyCopyUtil.copyCollectionProperties(saveAll, CompanyVo.class);
        return companyVos;
    }

    @Override
    public CompanyVo createCompany() {
        return null;
    }

    @Transactional
    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("未找到公司"));
        Long id = company.getId();
        List<Department> departments = departmentRepository.findAllByCompanyId(id);
        List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByCompanyId(id);
        departmentEmployRepository.deleteAll(departmentEmploys);
        departmentRepository.deleteAll(departments);
        companyRepository.delete(company);
    }

    @Override
    public CompanyTreeVo queryTree() {
        List<Company> all = companyRepository.findAll();
        List<CompanyTreeVo> companyTreeVos = PropertyCopyUtil.copyCollectionProperties(all, CompanyTreeVo.class);
        CompanyTreeVo companyTreeVo = TreeUtils.generateTrees(companyTreeVos).get(0);
        return companyTreeVo;
    }

    @Override
    public CompanyDetailVo queryDetailCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("未找到公司"));
        CompanyDetailVo companyDetailVo = PropertyCopyUtil.copyProperties(company, CompanyDetailVo.class);
        List<Department> departments = departmentRepository.findAllByCompanyId(companyDetailVo.getId());
        List<DepartmentTreeVo> departmentTreeVos = new ArrayList<>();
        for (Department department : departments) {
            DepartmentTreeVo departmentTreeVo = PropertyCopyUtil.copyProperties(department, DepartmentTreeVo.class);
            Integer count = departmentEmployRepository.countByDepartmentId(departmentTreeVo.getId());
            departmentTreeVo.setEmployeeCount(count);
            departmentTreeVos.add(departmentTreeVo);
        }
        List<DepartmentTreeVo> departmentTree = TreeUtils.generateTrees(departmentTreeVos);
        companyDetailVo.setDepartmentTreeVos(departmentTree);
        return companyDetailVo;
    }

    @Override
    public Company queryOne(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("未找到公司"));
        return company;
    }

    @Override
    public Company update(Long companyId, CompanyReq companyReq) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("未找到公司"));
        company.setName(companyReq.getName());
        companyRepository.findById(companyReq.getParentId()).orElseThrow(() -> new RuntimeException("未找到公司"));
        company.setParentId(companyReq.getParentId());
        return companyRepository.save(company);
    }


}
