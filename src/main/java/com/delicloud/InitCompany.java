package com.delicloud;

import com.delicloud.service.CompanyService;
import com.delicloud.service.DepartmentEmployService;
import com.delicloud.service.DepartmentService;
import com.delicloud.vo.CompanyVo;
import com.delicloud.vo.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2022/1/6
 */
@Component
public class InitCompany implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentEmployService departmentEmployService;

    @Override
    public void run(String... args) throws Exception {

        List<CompanyVo> companyList = companyService.createCompanyList(0L, 5);
        companyList.forEach(t -> {
            List<DepartmentVo> departmentList = departmentService.createDepartmentList(t.getId(), 0L, 3);
            departmentList.forEach(j -> {
                departmentEmployService.createDepartUser(j.getCompanyId(), j.getId(), 50);
            });
        });
    }
}
