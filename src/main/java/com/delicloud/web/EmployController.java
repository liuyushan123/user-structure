package com.delicloud.web;

import com.delicloud.dto.req.EmployReq;
import com.delicloud.entity.Department;
import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.service.DepartmentEmployService;
import com.delicloud.service.EmployService;
import com.delicloud.vo.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lys
 * 2022/1/5
 */
@RestController()
@RequestMapping("/employ")
public class EmployController {

    @Autowired
    private EmployService employService;
    @Autowired
    private DepartmentEmployService departmentEmployService;

    @PutMapping("{employId}")
    public RespBase<Void> update(@PathVariable Long employId,
                                 @RequestBody EmployReq employReq) {
        employService.update(employId, employReq);
        return RespBase.OK_RESP_BASE;
    }

    @PostMapping("create/{companyId}/{departmentId}/{count}")
    public RespBase<Void> create(
            @PathVariable("companyId") Long companyId,
            @PathVariable("count") Integer count,
            @PathVariable("departmentId") Long departmentId
    ) {
        departmentEmployService.createDepartUser(companyId, departmentId, count);
        return RespBase.OK_RESP_BASE;
    }
}
