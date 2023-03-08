package com.lawencon.community.pojo.profile;

public class PojoPasswordReqUpdate {
	private String oldPassword;
	private String newPassword;
	private String usedId;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getUsedId() {
		return usedId;
	}
	public void setUsedId(String usedId) {
		this.usedId = usedId;
	}
}
