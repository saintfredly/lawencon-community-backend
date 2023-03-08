package com.lawencon.community.model;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_course_material_file",
uniqueConstraints = {
        @UniqueConstraint(name = "course_material_file_ck", 
                columnNames = {"courseMaterial", "file"}
        )})
public class CourseMaterialFile extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "course_material_id")
	private CourseMaterial courseMaterial;
	
	@OneToOne
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
