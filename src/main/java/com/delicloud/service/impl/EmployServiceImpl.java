package com.delicloud.service.impl;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.dto.req.EmployReq;
import com.delicloud.entity.Employ;
import com.delicloud.repository.EmployRepository;
import com.delicloud.service.EmployService;
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
public class EmployServiceImpl implements EmployService {
    @Autowired
    private EmployRepository employRepository;

    @Override
    public List<Employ> createUserList(Integer count) {
        List<Employ> employs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employs.add(new Employ(UUID.randomUUID().toString(), "员工"+(i+1), i%2, Grade.P1, Job.EMPLOYEE));
        }
        return employRepository.saveAll(employs);
    }

    @Override
    public void query() {

    }

    @Override
    public Employ queryOne(Long employId) {
        Employ employ = employRepository.findById(employId).orElseThrow(() -> new RuntimeException("未找到员工"));
        return employ;
    }

    @Override
    public Employ update(Long employId, EmployReq employReq) {
        Employ employ = employRepository.findById(employId).orElseThrow(() -> new RuntimeException("未找到员工"));
        employ.setGrade(employReq.getGrade());
        employ.setJob(employReq.getJob());
        employ.setJobNumber(employReq.getJobNumber());
        employ.setSex(employ.getSex());
        employ.setName(employReq.getName());
        return employRepository.save(employ);
    }
}
