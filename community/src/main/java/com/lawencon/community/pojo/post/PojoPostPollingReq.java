package com.lawencon.community.pojo.post;

import java.util.List;

import com.lawencon.community.pojo.file.PojoFileReq;

public class PojoPostPollingReq {
	private String id;
	private String pollingTitle;
	private PojoFileReq photo;
	private List<PojoPostPollingDetailReq> pollingDetail;
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

	public PojoFileReq getPhoto() {
		return photo;
	}

	public void setPhoto(PojoFileReq photo) {
		this.photo = photo;
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

	public List<PojoPostPollingDetailReq> getPollingDetail() {
		return pollingDetail;
	}

	public void setPollingDetail(List<PojoPostPollingDetailReq> pollingDetail) {
		this.pollingDetail = pollingDetail;
	}

}
