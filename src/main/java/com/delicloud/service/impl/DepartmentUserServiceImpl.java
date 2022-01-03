package com.delicloud.service.impl;

import com.delicloud.repository.DepartmentUserRepository;
import com.delicloud.entity.DepartmentUser;
import com.delicloud.entity.User;
import com.delicloud.service.DepartmentUserService;
import com.delicloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
@Service
public class DepartmentUserServiceImpl implements DepartmentUserService {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentUserRepository departmentUserRepository;

    @Override
    public void createDepartUser(Long companyId, Long departmentId, Integer count) {
        List<User> userList = userService.createUserList(count);
        List<DepartmentUser> departmentUsers = new ArrayList<>();
        userList.forEach(t -> {
            departmentUsers.add(new DepartmentUser(companyId, departmentId, t.getId()));
        });
        departmentUserRepository.saveAll(departmentUsers);
    }

    @Override
    public void query() {

    }
}
