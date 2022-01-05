package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lys
 * 2022/1/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeConfig extends JsonBase {

    String nodeId;
    Boolean isManager;

}
