package com.delicloud.entity;

import com.delicloud.platform.v2.common.data.entity.SoftDeletableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
@Entity
@Table(name = "t_department_user")
@Data
@Where(clause = SoftDeletableEntity.SQL_CLAUSE_SOFT_DELETE)
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentUser extends SoftDeletableEntity {

    Long companyId;

    Long departmentId;

    Long userId;


}
