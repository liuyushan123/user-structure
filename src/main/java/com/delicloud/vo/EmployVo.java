package com.delicloud.vo;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

import java.util.List;

/**
 * @author lys
 * 2022/1/4
 */
@Data
public class EmployVo extends JsonBase {

    Long id;

    String jobNumber;

    String name;

    String phoneNumber;

    List<String> hobbies;

    Integer sex;

    Grade grade;

    Job job;


}
