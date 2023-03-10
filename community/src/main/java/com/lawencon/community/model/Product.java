package com.lawencon.community.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_product",
uniqueConstraints = {
        @UniqueConstraint(name = "product_bk", 
                columnNames = {"productCode"}
        )})
public class Product extends BaseEntity {

	@Column(nullable = false, length = 5)
	private String productCode;
	
	@Column(nullable = false, length = 50)
	private String productName;
	
	private String productDescription;
	private String productLocation;
	
	@OneToOne
	@JoinColumn(name = "photo")
	private File file;
	
	@Column(nullable = false)
	private String providerName;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private LocalDateTime dateStart;
	
	@Column(nullable = false)
	private LocalDateTime dateEnd;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name = "product_type_id")
	private ProductType productType;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductLocation() {
		return productLocation;
	}

	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
