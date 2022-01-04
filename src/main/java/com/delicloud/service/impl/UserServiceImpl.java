package com.delicloud.service.impl;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.entity.User;
import com.delicloud.repository.UserRepository;
import com.delicloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lys
 * 2022/1/2
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> createUserList(Integer count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new User(UUID.randomUUID().toString(), "员工"+(i+1), i%2, Grade.P1, Job.EMPLOYEE));
        }
        return userRepository.saveAll(users);
    }

    @Override
    public void query() {

    }

    @Override
    public User queryOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("未找到员工"));
        return user;
    }
}
