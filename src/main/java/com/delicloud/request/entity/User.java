package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

import java.util.List;

/**
 * @author lys
 * 2022/1/4
 */
@Data
public class User extends JsonBase {

    String name;
    String userType;
    List<ExtendsProp> extendsProp;
    List<ArchConfig> userArchConfigList;

}
