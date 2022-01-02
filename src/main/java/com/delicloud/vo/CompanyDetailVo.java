package com.delicloud.vo;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lys
 * 2022/1/2
 */
@Setter
@Getter
public class CompanyDetailVo extends JsonBase {

    Long id;
    String name;
    List<DepartmentTreeVo> departmentTreeVos;

}
