package com.delicloud.vo;

import com.delicloud.platform.v2.common.lang.bo.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @author lys
 * 2022/1/2
 */
public class DepartmentTreeVo extends JsonBase implements TreeNode<Long> {
    Long id;
    Long parentId;
    String name;
    @JsonIgnore
    Boolean root;
    Integer employeeCount;
    List<CompanyTreeVo> children;
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Long getParentId() {
        return this.parentId;
    }

    @Override
    public boolean isRoot() {
        return parentId == 0;
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

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

    @Override
    public List<? extends TreeNode<Long>> getChildren() {
        return this.children;
    }
}
