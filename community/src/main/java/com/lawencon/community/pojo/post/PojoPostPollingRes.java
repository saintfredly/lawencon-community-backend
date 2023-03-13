package com.lawencon.community.pojo.post;

import java.util.List;

public class PojoPostPollingRes {
	private String id;
	private String pollingTitle;
	private String photo;
	private List<PojoPostPollingDetailRes> pollingDetail;
	private Integer ver;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPollingTitle() {
		return pollingTitle;
	}

	public void setPollingTitle(String pollingTitle) {
		this.pollingTitle = pollingTitle;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<PojoPostPollingDetailRes> getPollingDetail() {
		return pollingDetail;
	}

	public void setPollingDetail(List<PojoPostPollingDetailRes> pollingDetail) {
		this.pollingDetail = pollingDetail;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
