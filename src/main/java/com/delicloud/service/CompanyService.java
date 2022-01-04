package com.delicloud.service;

import com.delicloud.entity.Company;
import com.delicloud.vo.CompanyDetailVo;
import com.delicloud.vo.CompanyTreeVo;
import com.delicloud.vo.CompanyVo;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
public interface CompanyService {

    /**
     * 创建count个公司
     * @param companyId 父id
     * @param count 数量
     * @return 公司List
     */
    List<CompanyVo> createCompanyList(Long companyId, Integer count);

    CompanyVo createCompany();

    boolean deleteComany();

    /**
     * 查询出公司树
     * @return 公司树
     */
    CompanyTreeVo queryTree();

    CompanyDetailVo queryDetailCompany(Long companyId);

    Company queryOne(Long companyId);

}
