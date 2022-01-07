package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.*;

import java.util.List;

/**
 * @author lys
 * 2022/1/4
 */
@Builder
@Setter
@Getter
public class ArchConfig extends JsonBase {

    String archId;
    String archName;
    String mainNodeId;
    List<NodeConfig> userNodeList;
}
