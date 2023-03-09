package com.lawencon.community.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.model.Category;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.category.PojoCategoryReq;
import com.lawencon.community.pojo.category.PojoCategoryRes;
import com.lawencon.community.service.CategoryService;


@RestController
@RequestMapping("category")
public class CategoryController {

	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoCategoryReq data) {
		final PojoRes res = categoryService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoCategoryReq data) {
		final PojoRes res = categoryService.save(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = categoryService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PojoCategoryRes>> getAll() {
		final List<PojoCategoryRes> res = categoryService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Category>> getById(
			@PathVariable("id") String id){
		final Optional<Category> res = categoryService.getById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
