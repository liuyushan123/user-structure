package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.*;

/**
 * @author lys
 * 2022/1/4
 */
@Builder
@Setter
@Getter
public class ExtendsProp extends JsonBase {

    Integer propNo;
    String propName;
    String propValue;

}
