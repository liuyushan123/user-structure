package com.delicloud.request.entity;

import com.delicloud.entity.DepartmentEmploy;
import com.delicloud.entity.Employ;
import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import lombok.Data;
import org.apache.tomcat.util.buf.StringUtils;

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

    String id;
    String name;
    String userType;
    List<ExtendsProp> extendsProp;
    List<ArchConfig> userArchList;

    public static User createUser(Employ employ, List<DepartmentEmploy> departmentEmploys) {
        User user = new User();
        user.setName(employ.getName());
        user.setUserType("测试");
        user.setExtendsProp(createExtendsProp(employ));
        user.setUserArchList(createArchConfig(departmentEmploys));
        return user;
    }

    private static List<ExtendsProp> createExtendsProp(Employ employ) {
        List<ExtendsProp> propList = new ArrayList<>();
        propList.add(ExtendsProp.builder().propNo(3).propValue(employ.getSex() == 0 ? "男" : "女").build());
        propList.add(ExtendsProp.builder().propNo(4).propValue(employ.getPhoneNumber()).build());
        propList.add(ExtendsProp.builder().propNo(5).propValue(employ.getJob().name()).build());
        propList.add(ExtendsProp.builder().propNo(9).propValue(StringUtils.join(employ.getHobbies(), '/')).build());
        return propList;
    }

    private static List<ArchConfig> createArchConfig(List<DepartmentEmploy> departmentEmploys) {
        Map<Long, List<DepartmentEmploy>> longListMap = departmentEmploys.stream().collect(Collectors.groupingBy(DepartmentEmploy::getCompanyId));
        List<ArchConfig> userArchConfigList = new ArrayList<>();
        longListMap.forEach((companyId, departmentEmploy) -> {
            List<NodeConfig> nodeConfigs = new ArrayList<>();
            departmentEmploy.forEach(d -> {
                nodeConfigs.add(NodeConfig.builder().nodeId(d.getDepartmentId().toString()).isManager(false).build());
            });
            userArchConfigList.add(ArchConfig.builder().archId(companyId.toString()).mainNodeId(departmentEmploy.get(0).getDepartmentId().toString()).userNodeList(nodeConfigs).build());
        });
        return userArchConfigList;
    }

}
