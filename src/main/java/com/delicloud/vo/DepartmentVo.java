package com.delicloud.vo;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/30
 */
@Data
public class DepartmentVo extends JsonBase {

    Long id;

    String name;

    Long companyId;

    String companyName;

    List<EmployVo> employs;

}
