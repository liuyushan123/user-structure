package com.delicloud.repository;

import com.delicloud.entity.Employ;
import com.delicloud.platform.v2.common.data.repository.RepositoryBase;

import java.util.Optional;

/**
 * @author liuyushan
 * Date: 2021/12/23
 */
public interface EmployRepository extends RepositoryBase<Employ, Long> {

    Optional<Employ> findByJobNumber(String jobNumber);
}
