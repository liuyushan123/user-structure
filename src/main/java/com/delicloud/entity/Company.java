package com.delicloud.entity;

import com.delicloud.platform.v2.common.data.entity.SoftDeletableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/22
 */
@Entity
@Table(name = "t_company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company extends SoftDeletableEntity {

    Long parentCompanyId;

    String name;

}
