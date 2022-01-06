package com.delicloud.entity;

import com.delicloud.commonEnum.Grade;
import com.delicloud.commonEnum.Job;
import com.delicloud.platform.v2.common.data.entity.SoftDeletableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/22
 */
@Entity
@Data
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = SoftDeletableEntity.SQL_CLAUSE_SOFT_DELETE)
public class Employ extends SoftDeletableEntity {

    String jobNumber;

    String name;

    Integer sex;

    String phoneNumber;

    Grade grade;

    Job job;

    @ElementCollection(targetClass = String.class)
    List<String> hobbies;




}

