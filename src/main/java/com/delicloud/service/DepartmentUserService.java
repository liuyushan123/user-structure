package com.delicloud.service;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
public interface DepartmentUserService {

    void createDepartUser(Long companyId, Long departmentId, Integer count);

    void query();

}
