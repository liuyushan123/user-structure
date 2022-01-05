package com.delicloud.vo;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

/**
 * @author lys
 * 2022/1/4
 */
@Data
public class EmployVo extends JsonBase {

    Long id;

    String jobNumber;

    String name;

    Integer sex;

    Grade grade;

    Job job;


}
