package com.delicloud.entity;

import com.delicloud.platform.v2.common.data.entity.SoftDeletableEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@Entity
@Table(name = "t_department_user")
public class DepartmentUser extends SoftDeletableEntity {

    Long companyId;

    Long departmentId;

    Long userId;


}
