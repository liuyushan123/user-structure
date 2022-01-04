package com.delicloud.web;

import com.delicloud.entity.Company;
import com.delicloud.entity.Department;
import com.delicloud.entity.User;
import com.delicloud.service.CompanyService;
import com.delicloud.service.DepartmentService;
import com.delicloud.service.UserService;
import com.delicloud.util.SystemButtJoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liuyushan
 * Date: 2022/1/4
 */
@Slf4j
@RestController
public class ArchController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SystemButtJoint systemButtJoint;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    @PutMapping("/arch/{archId}")
    public ResponseEntity<Object> putArch(@PathVariable Long archId,
                                          HttpServletRequest request,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          @RequestBody Map body) {
        companyService.queryOne(archId);
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/arch/{archId}")
    public ResponseEntity<Object> getArch(@PathVariable Long archId,
                                          HttpServletRequest request,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          @RequestBody Map body) {
        companyService.queryOne(archId);
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/arch/all")
    public ResponseEntity<Object> getArchAll(HttpServletRequest request,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             @RequestBody Map body) {
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @PutMapping("/arch/{archId}/node/{nodeId}")
    public ResponseEntity<Object> getNode(@PathVariable Long archId,
                                          @PathVariable Long nodeId,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request,
                                          @RequestBody Map body) {
        Department department = departmentService.queryOne(nodeId);
        Assert.isTrue(department.getCompanyId().equals(archId), "部门不在公司下面");
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @DeleteMapping("/arch/{archId}")
    public ResponseEntity<Object> deleteArch(@PathVariable Long archId,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody Map body) {
        Company company = companyService.queryOne(archId);
        //TODO 删除公司未做
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
                                             @RequestBody Map body) {
        Department department = departmentService.queryOne(nodeId);
        Assert.isTrue(department.getCompanyId().equals(archId), "部门不在公司下面");
        //TODO 删除部门未做
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @PutMapping("/user/{no}")
    public ResponseEntity<Object> putUser(@PathVariable Long no,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request,
                                          @RequestBody Map body) {
        User user = userService.queryOne(no);
//        Assert.isTrue(department.getCompanyId().equals(archId), "部门不在公司下面");
        //TODO 添加人员未做
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/user/{no}")
    public ResponseEntity<Object> getUser(@PathVariable Long no,
                                          @RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request,
                                          @RequestBody Map body) {
        userService.queryOne(no);
        String method = request.getMethod();

        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/user/page")
    public ResponseEntity<Object> getUser(@RequestHeader(value = "Api-Cmd") String apiCmd,
                                          HttpServletRequest request,
                                          @RequestBody Map body) {
        String method = request.getMethod();
        //TODO /user/page?page=1&size=10分页未作
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @DeleteMapping("/user/{no}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long no,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody Map body) {
        userService.queryOne(no);
        String method = request.getMethod();
        //TODO 删除人员未作
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @PutMapping("/user/{no}/feature/{type}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long no,
                                             @PathVariable String type,
                                             @RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody Map body) {
        userService.queryOne(no);
        String method = request.getMethod();
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/ass/record")
    public ResponseEntity<Object> assRecord(@RequestHeader(value = "Api-Cmd") String apiCmd,
                                             HttpServletRequest request,
                                             @RequestBody Map body) {
        String method = request.getMethod();
        //TODO 未作/ass/record?next_id=0&page_size=50&real_data_only=true
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }

    @GetMapping("/acs/record")
    public ResponseEntity<Object> acsRecord(@RequestHeader(value = "Api-Cmd") String apiCmd,
                                            HttpServletRequest request,
                                            @RequestBody Map body) {
        String method = request.getMethod();
        //TODO 未作/ass/record?next_id=0&page_size=50&real_data_only=true
        String path = request.getRequestURI();
        ResponseEntity<Object> response = systemButtJoint.sendMessage(HttpMethod.valueOf(method), path, apiCmd, body);
        return response;
    }
}
