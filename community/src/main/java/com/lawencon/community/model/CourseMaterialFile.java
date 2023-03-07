package com.lawencon.community.model;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_course_material_file")
public class CourseMaterialFile extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "course_material_id")
	private CourseMaterial courseMaterial;
	
	@ManyToOne
	@JoinColumn(name = "file_id")

	private File file;

	public CourseMaterial getCourseMaterial() {
		return courseMaterial;
	}

	public void setCourseMaterial(CourseMaterial courseMaterial) {
		this.courseMaterial = courseMaterial;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
