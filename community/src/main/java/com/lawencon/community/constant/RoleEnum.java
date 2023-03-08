package com.lawencon.community.constant;

public enum RoleEnum {
	SUPER_ADMIN("R0001", "Super Admin"), ADMIN("R0002", "Admin"), MEMBER("R0003", "Member"),
	SYSTEM("R0004", "System");

	private String roleCode;
	private String roleName;

	RoleEnum(String roleCode, String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}
}
