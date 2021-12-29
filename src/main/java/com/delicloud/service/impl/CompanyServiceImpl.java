package com.delicloud.service.impl;

import com.delicloud.dao.CompanyRepository;
import com.delicloud.entity.Company;
import com.delicloud.platform.v2.common.lang.util.PropertyCopyUtil;
import com.delicloud.service.CompanyService;
import com.delicloud.vo.CompanyTreeVo;
import com.delicloud.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<CompanyVo> createCompanyList(Long companyId, Integer count) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        Assert.isTrue(optionalCompany.isPresent(), "公司不存在");
        Company company = optionalCompany.get();
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

    @Override
    public boolean deleteComany() {
        return false;
    }

    @Override
    public CompanyTreeVo query() {
        List<Company> all = companyRepository.findAll();
        Company company = companyRepository.findById(0L).get();
        CompanyTreeVo companyTreeVo = PropertyCopyUtil.copyProperties(company, CompanyTreeVo.class);
        tree(all, companyTreeVo);
        return companyTreeVo;
    }

    private void tree(List<Company> companies, CompanyTreeVo companyTreeVo) {
        List<Company> collect = companies.stream().filter(t -> t.getId().equals(companyTreeVo.getId())).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return;
        }
        List<CompanyTreeVo> companyTreeVos = PropertyCopyUtil.copyCollectionProperties(collect, CompanyTreeVo.class);
        companyTreeVo.setChildCompany(companyTreeVos);
        for (CompanyTreeVo company:companyTreeVos) {
            tree(companies, company);
        }
    }

}
