package com.delicloud.dto.req;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

/**
 * @author lys
 * 2022/1/5
 */
@Data
public class CompanyReq extends JsonBase {

    Long parentId;
    String name;
}
