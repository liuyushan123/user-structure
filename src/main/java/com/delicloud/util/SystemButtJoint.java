package com.delicloud.util;

import com.delicloud.platform.v2.common.lang.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
    private String url = "http://127.0.0.1:8080";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Object> sendMessage(HttpMethod method, String path, String cmd, Object body) {
        return sendMessage(method, path, null, cmd, body);
    }

    public ResponseEntity<Object> sendMessage(HttpMethod method, String path, String queryParam, String cmd, Object body) {
        return sendMessage(method, path, queryParam, cmd, body, Object.class);
    }

    public <T> ResponseEntity<T> sendMessage(HttpMethod method, String path, String queryParam, String cmd, Object body, Class<T> tClass) {
        return sendMessage(method, path, queryParam, cmd, body, new ParameterizedTypeReference<T>() {
        });
    }
    public <T> ResponseEntity<T> sendMessage(HttpMethod method, String path, String cmd, Object body, Class<T> tClass) {
        return sendMessage(method, path, null, cmd, body, new ParameterizedTypeReference<T>() {
        });
    }

    public <T> ResponseEntity<T> sendMessage(HttpMethod method, String path, String cmd, Class<T> tClass) {
        return sendMessage(method, path, null, cmd, null, new ParameterizedTypeReference<T>() {
        });
    }

    public <T> ResponseEntity<T> sendMessage(HttpMethod method, String path, String queryParam, String cmd, Object body, ParameterizedTypeReference<T> typeReference) {
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
        String s1 = url + path + "?" + queryParam;
        log.info(JacksonUtil.bean2Json(body));
        ResponseEntity<T> response = restTemplate.exchange(s1, method, entity, typeReference);
        log.info(response.getBody().toString());
        return response;
    }

}
