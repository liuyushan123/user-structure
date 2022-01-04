package com.delicloud.repository;

import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.platform.v2.common.data.repository.RepositoryBase;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
public interface DepartmentEmployRepository extends RepositoryBase<DepartmentEmploy, Long> {

    Integer countByDepartmentId(Long departmentId);

    List<DepartmentEmploy> findAllByCompanyId(Long companyId);

    List<DepartmentEmploy> findAllByDepartmentId(Long departmentId);

    List<DepartmentEmploy> findAllByEmployId(Long employId);

}
