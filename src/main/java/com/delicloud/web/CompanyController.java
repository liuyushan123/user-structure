package com.delicloud.web;

import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.service.CompanyService;
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
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("query")
    public RespBase<CompanyTreeVo> queryAll() {
        CompanyTreeVo companyVos = companyService.queryTree();
        System.out.println(companyVos.toString());
        return new RespBase<>(companyVos);
    }

    @PostMapping("create/{companyId}/{count}")
    public RespBase<List<CompanyVo>> create(
            @PathVariable("count") Integer count,
            @PathVariable("companyId") Long companyId
    ) {
        List<CompanyVo> companyList = companyService.createCompanyList(companyId, count);
        return new RespBase<>(companyList);
    }
}
