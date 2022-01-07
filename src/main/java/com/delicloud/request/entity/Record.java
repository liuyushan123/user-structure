package com.delicloud.request.entity;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2022/1/7
 */
@Data
public class Record<T> extends JsonBase {

    String nextId;
    List<T> data;

}
