package com.delicloud.web;

import com.delicloud.dto.req.CompanyReq;
import com.delicloud.entity.Company;
import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.service.CompanyService;
import com.delicloud.vo.CompanyDetailVo;
import com.delicloud.vo.CompanyTreeVo;
import com.delicloud.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@RestController()
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("queryTree")
    public RespBase<CompanyTreeVo> queryAll() {
        CompanyTreeVo companyVos = companyService.queryTree();
        return new RespBase<>(companyVos);
    }

    @GetMapping("query/{companyId}")
    public RespBase<CompanyDetailVo> queryAll(@PathVariable Long companyId) {
        CompanyDetailVo companyDetailVo = companyService.queryDetailCompany(companyId);
        return new RespBase<>(companyDetailVo);
    }

    @PostMapping("create/{companyId}/{count}")
    public RespBase<List<CompanyVo>> create(
            @PathVariable("count") Integer count,
            @PathVariable("companyId") Long companyId
    ) {
        List<CompanyVo> companyList = companyService.createCompanyList(companyId, count);
        return new RespBase<>(companyList);
    }

    @DeleteMapping("{companyId}")
    public RespBase<Void> delete(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return RespBase.OK_RESP_BASE;
    }

    @PutMapping("{companyId}")
    public RespBase<Void> update(@PathVariable Long companyId,
                                 @RequestBody CompanyReq companyReq) {
        companyService.update(companyId, companyReq);
        return RespBase.OK_RESP_BASE;
    }
}
