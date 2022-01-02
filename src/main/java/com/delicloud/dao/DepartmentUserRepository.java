package com.delicloud.dao;

import com.delicloud.entity.DepartmentUser;
import com.delicloud.platform.v2.common.data.repository.RepositoryBase;
import org.springframework.stereotype.Repository;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
public interface DepartmentUserRepository extends RepositoryBase<DepartmentUser, Long> {

    Integer countByDepartmentId(Long departmentId);
}
