package com.delicloud.util;

import com.delicloud.platform.v2.common.lang.bo.page.RespPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuyushan
 * Date: 2022/1/4
 */
@Slf4j
@Component
public class SystemButtJoint {

    public final static String checkinQuery = "checkin_query";
public final static String checkinAcsQuery  = "checkin_acs_query";
    public final static String archQuery  = "arch_query";
    public final static String archOperate = "arch_operate";
    public final static String userQuery = "user_query";
    public final static String userOperate = "user_operate";

    private String key = "deli";
    private String secret = "deli.zhqd";
    private String url = "http://192.168.0.202:8080";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Object> sendMessage(HttpMethod method, String path, String cmd, Object body) {
        Long time = System.currentTimeMillis();
        String s = path + time + key + secret;
        log.info(s);
        String md5 = DigestUtils.md5DigestAsHex(s.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("App-Key", key);
        httpHeaders.add("App-Sig", md5);
        httpHeaders.add("App-Timestamp", String.valueOf(time));
        httpHeaders.add("Api-Cmd", cmd);
        HttpEntity<Object> entity = new HttpEntity<>(body, httpHeaders);
        String s1 = url + path;
        ResponseEntity<Object> response = restTemplate.exchange(s1, method, entity, Object.class);
        return response;
    }

    public ResponseEntity<RespPage> sendMessagePage(HttpMethod method, String path, String cmd, Object body) {
        Long time = System.currentTimeMillis();
        String s = path + time + key + secret;
        log.info(s);
        String md5 = DigestUtils.md5DigestAsHex(s.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("App-Key", key);
        httpHeaders.add("App-Sig", md5);
        httpHeaders.add("App-Timestamp", String.valueOf(time));
        httpHeaders.add("Api-Cmd", cmd);
        HttpEntity<Object> entity = new HttpEntity<>(body, httpHeaders);
        String s1 = url + path;
        ResponseEntity<RespPage> response = restTemplate.exchange(s1, method, entity, RespPage.class);
        return response;
    }


}