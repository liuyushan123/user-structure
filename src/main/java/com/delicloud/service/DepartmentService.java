package com.delicloud.service;

/**
 * @author liuyushan
 * Date: 2021/12/30
 */
public interface DepartmentService {

    void createDepartmentList(Long companyId, Long parentDepartment, Integer count);

    void query();
}
