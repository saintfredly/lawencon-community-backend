package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_course_material")
public class CourseMaterial extends BaseEntity {
	@Column(nullable = false, length = 50)
	private String courseMaterialTitle;
	
	@Column(nullable = false)
	private String courseMaterialContent;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public String getCourseMaterialTitle() {
		return courseMaterialTitle;
	}

	public void setCourseMaterialTitle(String courseMaterialTitle) {
		this.courseMaterialTitle = courseMaterialTitle;
	}

	public String getCourseMaterialContent() {
		return courseMaterialContent;
	}

	public void setCourseMaterialContent(String courseMaterialContent) {
		this.courseMaterialContent = courseMaterialContent;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
