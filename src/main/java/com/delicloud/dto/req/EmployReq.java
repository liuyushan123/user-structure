package com.delicloud.dto.req;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

/**
 * @author lys
 * 2022/1/5
 */
@Data
public class EmployReq extends JsonBase {

    String jobNumber;

    String name;

    Integer sex;

    Grade grade;

    Job job;
}
