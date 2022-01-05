package com.delicloud.service;

import com.delicloud.dto.req.EmployReq;
import com.delicloud.entity.Employ;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
public interface EmployService {

    List<Employ> createUserList(Integer count);

    void query();

    Employ queryOne(Long employId);

    Employ update(Long employId, EmployReq employReq);

    Employ queryOne(String jobNumber);
}
