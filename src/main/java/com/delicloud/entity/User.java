package com.delicloud.entity;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.platform.v2.common.data.entity.SoftDeletableEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author liuyushan
 * Date: 2021/12/22
 */
@Entity
@Data
@Table(name = "t_user")
public class User extends SoftDeletableEntity {

    String jobNumber;

    String name;

    Integer sex;

    Grade grade;

    Job job;





}

