package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * @author lys
 * 2022/1/4
 */
@Builder
@Getter
public class Arch extends JsonBase {

    String name;
}
