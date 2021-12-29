package com.delicloud.util;

import com.delicloud.commonEnum.NameType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
public class NameGetter {

    private static final Map<Long, String> nameMap = new HashMap();

    public static String getCompanyName(Long id) {
        String s = nameMap.get(id);
        return null;
    }

}
