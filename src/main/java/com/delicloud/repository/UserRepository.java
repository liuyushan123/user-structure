package com.delicloud.repository;

import com.delicloud.entity.User;
import com.delicloud.platform.v2.common.data.repository.RepositoryBase;
import org.springframework.stereotype.Repository;

/**
 * @author liuyushan
 * Date: 2021/12/23
 */
public interface UserRepository extends RepositoryBase<User, Long> {
}
