package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

/**
 * @author lys
 * 2022/1/4
 */
@Data
public class ExtendsProp extends JsonBase {

    Integer propNo;
    String propValue;

}
