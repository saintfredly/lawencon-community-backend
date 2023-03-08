package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_role",
uniqueConstraints = {
        @UniqueConstraint(name = "role_bk", 
                columnNames = {"role_code"}
        )})
public class Role extends BaseEntity {
	@Column(length = 5, nullable = false)
	private String roleCode;
	
	@Column(length = 30)
	private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
