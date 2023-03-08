package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_position",
uniqueConstraints = {
        @UniqueConstraint(name = "position_bk", 
                columnNames = {"position_code"}
        )})
public class Position extends BaseEntity {
	@Column(nullable = false, length = 5)
	private String positionCode;
	
	@Column(nullable = false, length = 50)
	private String positionName;

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
