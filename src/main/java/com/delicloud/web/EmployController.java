package com.delicloud.web;

import com.delicloud.dto.req.EmployReq;
import com.delicloud.platform.v2.common.lang.bo.RespBase;
import com.delicloud.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lys
 * 2022/1/5
 */
@RestController()
@RequestMapping("/employ")
public class EmployController {

    @Autowired
    private EmployService employService;

    @PutMapping("{employId}")
    public RespBase<Void> update(@PathVariable Long employId,
                                 @RequestBody EmployReq employReq) {
        employService.update(employId, employReq);
        return RespBase.OK_RESP_BASE;
    }
}
