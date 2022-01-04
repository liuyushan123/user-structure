package com.delicloud.service.impl;

import com.delicloud.entity.Employ;
import com.delicloud.repository.DepartmentEmployRepository;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.service.DepartmentEmployService;
import com.delicloud.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/31
 */
@Service
public class DepartmentEmployServiceImpl implements DepartmentEmployService {
    @Autowired
    private EmployService employService;
    @Autowired
    private DepartmentEmployRepository departmentEmployRepository;

    @Override
    public void createDepartUser(Long companyId, Long departmentId, Integer count) {
        List<Employ> employList = employService.createUserList(count);
        List<DepartmentEmploy> departmentEmploys = new ArrayList<>();
        employList.forEach(t -> {
            departmentEmploys.add(new DepartmentEmploy(companyId, departmentId, t.getId()));
        });
        departmentEmployRepository.saveAll(departmentEmploys);
    }

    @Override
    public void query() {

    }

    @Override
    public List<DepartmentEmploy> queryUsers(Long employId) {
        List<DepartmentEmploy> departmentEmploys = departmentEmployRepository.findAllByEmployId(employId);
        return departmentEmploys;
    }
}
