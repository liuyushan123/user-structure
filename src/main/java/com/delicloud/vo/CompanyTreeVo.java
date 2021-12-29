package com.delicloud.vo;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@Data
public class CompanyTreeVo extends JsonBase {

    Long id;
    String name;
    List<CompanyTreeVo> childCompany;
}
