package com.delicloud.service;

import com.delicloud.entity.DepartmentEmploy;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
public interface DepartmentEmployService {

    void createDepartUser(Long companyId, Long departmentId, Integer count);

    void query();

    List<DepartmentEmploy> queryUsers(Long employId);

}
