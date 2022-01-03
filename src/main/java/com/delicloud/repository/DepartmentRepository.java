package com.delicloud.repository;

import com.delicloud.entity.Department;
import com.delicloud.platform.v2.common.data.repository.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/23
 */
public interface DepartmentRepository extends RepositoryBase<Department, Long> {

    List<Department> findAllByCompanyId(Long companyId);

}
