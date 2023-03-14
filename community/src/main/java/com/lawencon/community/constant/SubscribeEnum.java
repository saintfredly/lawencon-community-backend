package com.lawencon.community.constant;

import java.math.BigDecimal;

public enum SubscribeEnum {
	PREMIUM("Premium", new BigDecimal("150.000"));

	private String name;
	private BigDecimal price;

	SubscribeEnum(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
