package com.delicloud.web;

import com.delicloud.dto.req.DepartmentReq;
import com.delicloud.entity.Department;
import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.service.CompanyService;
import com.delicloud.service.DepartmentService;
import com.delicloud.vo.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lys
 * 2022/1/4
 */
@RestController()
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("{departmentId}")
    public RespBase<DepartmentVo> query(@PathVariable Long departmentId) {
        DepartmentVo departmentVo = departmentService.query(departmentId);
        return new RespBase<>(departmentVo);
    }

    @PostMapping("create/{companyId}/{departmentId}/{count}")
    public RespBase<List<DepartmentVo>> create(
            @PathVariable("companyId") Long companyId,
            @PathVariable("count") Integer count,
            @PathVariable("departmentId") Long departmentId
    ) {
        List<DepartmentVo> departmentList = departmentService.createDepartmentList(companyId, departmentId, count);
        return new RespBase<>(departmentList);
    }

    @DeleteMapping("{departmentId}")
    public RespBase<Void> delete(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return RespBase.OK_RESP_BASE;
    }

    @PutMapping("{departmentId}")
    public RespBase<Void> update(@PathVariable Long departmentId,
                                 @RequestBody DepartmentReq departmentReq) {
        departmentService.update(departmentId, departmentReq);
        return RespBase.OK_RESP_BASE;
    }

}
