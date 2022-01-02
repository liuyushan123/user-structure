package com.delicloud.service;

import com.delicloud.vo.CompanyDetailVo;
import com.delicloud.vo.CompanyTreeVo;
import com.delicloud.vo.CompanyVo;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
public interface CompanyService {

    List<CompanyVo> createCompanyList(Long companyId, Integer count);

    CompanyVo createCompany();

    boolean deleteComany();

    CompanyTreeVo query();

    CompanyDetailVo queryCompany(Long companyId);

}
