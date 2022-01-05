package com.delicloud.service;

import com.delicloud.dto.req.DepartmentReq;
import com.delicloud.entity.Department;
import com.delicloud.vo.DepartmentVo;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/30
 */
public interface DepartmentService {

    List<DepartmentVo> createDepartmentList(Long companyId, Long parentDepartment, Integer count);

    DepartmentVo query(Long departmentId);

    Department queryOne(Long departmentId);

    void deleteDepartment(Long departmentId);

    Department update(Long departmentId, DepartmentReq departmentReq);
}
