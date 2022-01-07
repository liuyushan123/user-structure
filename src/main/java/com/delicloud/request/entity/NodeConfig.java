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
public class NodeConfig extends JsonBase {

    String nodeId;
    Boolean isManager;
    String archId;
    String nodeIdPath;
    String nodeName;
    String nodeNamePath;

}
