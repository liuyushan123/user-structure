package com.delicloud.service;

import com.delicloud.entity.Department;

/**
 * @author liuyushan
 * Date: 2021/12/30
 */
public interface DepartmentService {

    void createDepartmentList(Long companyId, Long parentDepartment, Integer count);

    void query();

    Department queryOne(Long departmentId);
}
