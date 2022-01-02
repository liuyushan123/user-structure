package com.delicloud.service;

import com.delicloud.entity.User;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
public interface UserService {

    List<User> createUserList(Integer count);

    void query();
}
