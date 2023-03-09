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

import com.lawencon.community.model.Industry;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.industry.PojoIndustryReq;
import com.lawencon.community.pojo.industry.PojoIndustryResGetAll;
import com.lawencon.community.service.IndustryService;

@RestController
@RequestMapping("industry")
public class IndustryController {
	
	private final IndustryService industryService;
	
	public IndustryController(IndustryService industryService) {
		this.industryService = industryService;
	}
	
	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoIndustryReq data) {
		final PojoRes res = industryService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoIndustryReq data) {
		final PojoRes res = industryService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = industryService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PojoIndustryResGetAll>> getAll() {
		final List<PojoIndustryResGetAll> res = industryService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Industry>> getById(
			@PathVariable("id") String id){
		final Optional<Industry> res = industryService.getById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
