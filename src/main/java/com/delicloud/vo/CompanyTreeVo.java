package com.delicloud.vo;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author liuyushan
 * Date: 2021/12/29
 */
public class CompanyTreeVo extends JsonBase implements TreeNode<Long> {

    Long id;
    Long parentId;
    String name;
    @JsonIgnore
    Boolean root;
    List<CompanyTreeVo> children;
    List<DepartmentTreeVo> departmentTreeVos;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Long getParentId() {
        return this.parentId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isRoot() {
        return id == 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

    public List<DepartmentTreeVo> getDepartmentTreeVos() {
        return departmentTreeVos;
    }

    public void setDepartmentTreeVos(List<DepartmentTreeVo> departmentTreeVos) {
        this.departmentTreeVos = departmentTreeVos;
    }

    @Override
    public List<? extends TreeNode<Long>> getChildren() {
        return this.children;
    }
}
