package com.delicloud.vo;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@Data
public class CompanyVo extends JsonBase {

    Long id;

    String name;

    Long parentCompanyId;
}
