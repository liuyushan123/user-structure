package com.delicloud.request.entity;

import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static User createUser(Employ employ, List<DepartmentEmploy> departmentEmploys) {
        User user = new User();
        user.setName(employ.getName());
        user.setExtendsProp(createExtendsProp(employ));
        user.setUserArchConfigList(createArchConfig(departmentEmploys));
        return user;
    }

    private static List<ExtendsProp> createExtendsProp(Employ employ) {
        List<ExtendsProp> propList = new ArrayList<>();
        propList.add(new ExtendsProp(2, employ.getJob().name()));
        propList.add(new ExtendsProp(3, employ.getGrade().name()));
        propList.add(new ExtendsProp(4, employ.getSex().toString()));
        return propList;
    }

    private static List<ArchConfig> createArchConfig(List<DepartmentEmploy> departmentEmploys) {
        Map<Long, List<DepartmentEmploy>> longListMap = departmentEmploys.stream().collect(Collectors.groupingBy(DepartmentEmploy::getCompanyId));
        List<ArchConfig> userArchConfigList = new ArrayList<>();
        longListMap.forEach((companyId, departmentEmploy) -> {
            List<NodeConfig> nodeConfigs = new ArrayList<>();
            departmentEmploy.forEach(d -> {
                nodeConfigs.add(new NodeConfig(d.getDepartmentId().toString(), false));
            });
            userArchConfigList.add(new ArchConfig(companyId.toString(), departmentEmploy.get(0).getDepartmentId().toString(), nodeConfigs));
        });
        return userArchConfigList;
    }

}
