package com.delicloud.web;

import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.request.entity.Arch;
import com.delicloud.request.entity.Node;
import com.delicloud.request.entity.User;
import com.delicloud.service.CompanyService;
import com.delicloud.service.DepartmentEmployService;
import com.delicloud.service.DepartmentService;
import com.delicloud.service.EmployService;
import com.delicloud.util.SystemButtJoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author liuyushan
 * Date: 2022/1/4
 */
@Slf4j
@RestController
@RequestMapping("/openApi/v1.0")
public class ArchController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SystemButtJoint systemButtJoint;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployService employService;
    @Autowired
    private DepartmentEmployService departmentEmployService;

    @PutMapping("/arch/{archId}")
    public ResponseEntity<Object> putArch(@PathVariable Long archId,
                                          HttpServletRequest request,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd) {
        Company company = companyService.queryOne(archId);
        Arch arch = Arch.builder().name(company.getName()).build();
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, arch);
        return response;
    }

    @GetMapping("/arch/{archId}")
    public ResponseEntity<Object> getArch(@PathVariable Long archId,
                                          HttpServletRequest request,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          @RequestBody(required = false) Object body) {
        companyService.queryOne(archId);
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/arch/all")
    public ResponseEntity<Object> getArchAll(HttpServletRequest request,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             @RequestBody(required = false) Object body) {
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @PutMapping("/arch/{archId}/node/{nodeId}")
    public ResponseEntity<Object> getNode(@PathVariable Long archId,
                                          @PathVariable Long nodeId,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request) {
        Department department = departmentService.queryOne(nodeId);
        Assert.isTrue(department.getCompanyId().equals(archId), "部门不在公司下面");
        Node node = Node.builder().parentNodeId(String.valueOf(department.getParentId())).name(department.getName()).build();
        String method = request.getMethod();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, node);
        return response;
    }

    @DeleteMapping("/arch/{archId}")
    public ResponseEntity<Object> deleteArch(@PathVariable Long archId,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody(required = false) Object body) {
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @DeleteMapping("/arch/{archId}/node/{nodeId}")
    public ResponseEntity<Object> deleteNode(@PathVariable Long archId,
                                             @PathVariable Long nodeId,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody(required = false) Object body) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/arch/{archId}/node/{nodeId}")
    public ResponseEntity<Object> queryNode(@PathVariable Long archId,
                                             @PathVariable Long nodeId,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody(required = false) Object body) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @PutMapping("/user/{no}")
    public ResponseEntity<Object> putUser(@PathVariable String no,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request) {
        Employ employ = employService.queryOne(no);
        List<DepartmentEmploy> departmentEmploys = departmentEmployService.queryUsers(employ.getId());
        User user = User.createUser(employ, departmentEmploys);
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, user);
        return response;
    }

    @GetMapping("/user/{no}")
    public ResponseEntity<Object> getUser(@PathVariable String no,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request,
                                          @RequestBody(required = false) Object body) {
        employService.queryOne(no);
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/user/page")
    public ResponseEntity<Object> getUser(@RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request,
                                          @RequestBody(required = false) Object body) {
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path + "?" + queryString, apiCmd, body);
        return response;
    }

    @DeleteMapping("/user/{no}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long no,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody(required = false) Object body) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @PutMapping("/user/{no}/feature/{type}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long no,
                                             @PathVariable String type,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody(required = false) Object body) {
        employService.queryOne(no);
        String method = request.getMethod();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/ass/record")
    public ResponseEntity<Object> assRecord(@RequestHeader(value = "Api-Cmd") String apiCmd,
                                            HttpServletRequest request,
                                            @RequestBody(required = false) Object body) {
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path + "?" + queryString, apiCmd, body);
        return response;
    }

    @GetMapping("/acs/record")
    public ResponseEntity<Object> acsRecord(@RequestHeader(value = "Api-Cmd") String apiCmd,
                                            HttpServletRequest request,
                                            @RequestBody(required = false) Object body) {
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path + "?" + queryString, apiCmd, body);
        return response;
    }
}
