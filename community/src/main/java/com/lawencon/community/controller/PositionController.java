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

import com.lawencon.community.model.Position;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.position.PojoPositionReq;
import com.lawencon.community.pojo.position.PojoPositionResGetAll;
import com.lawencon.community.service.PositionService;

@RestController
@RequestMapping("position")
public class PositionController {
	
	private final PositionService positionService;
	
	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoPositionReq data) {
		final PojoRes res = positionService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoPositionReq data) {
		final PojoRes res = positionService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = positionService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PojoPositionResGetAll>> getAll() {
		final List<PojoPositionResGetAll> res = positionService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Position>> getById(
			@PathVariable("id") String id){
		final Optional<Position> res = positionService.getById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
