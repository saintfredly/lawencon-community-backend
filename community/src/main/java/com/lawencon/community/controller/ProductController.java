package com.lawencon.community.controller;

import java.util.List;

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

import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.product.PojoProductReq;
import com.lawencon.community.pojo.product.PojoProductRes;
import com.lawencon.community.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoProductReq data) {
		final PojoRes res = productService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoProductReq data) {
		final PojoRes res = productService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = productService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/course")
	public ResponseEntity<List<PojoProductRes>> getAllCourse() {
		final List<PojoProductRes> res = productService.getAllCourse();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/event")
	public ResponseEntity<List<PojoProductRes>> getAllEvent() {
		final List<PojoProductRes> res = productService.getAllEvent();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
