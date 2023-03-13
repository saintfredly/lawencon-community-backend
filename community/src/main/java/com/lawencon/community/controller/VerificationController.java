package com.lawencon.community.controller;

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

import com.lawencon.community.model.Verification;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.verification.PojoVerificationReq;
import com.lawencon.community.service.VerificationService;

@RestController
@RequestMapping("verification")
public class VerificationController {
    private final VerificationService verificationService;
	
	public VerificationController(VerificationService verificationService) {
		this.verificationService = verificationService;
	}
	
	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoVerificationReq data) {
		final PojoRes res = verificationService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
//	@PutMapping
//	public ResponseEntity<PojoRes> update(@RequestBody PojoVerificationReq data) {
//		final PojoRes res = verificationService.update(data);
//		return new ResponseEntity<>(res, HttpStatus.OK);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = verificationService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Verification>> getById(
			@PathVariable("id") String id){
		final Optional<Verification> res = verificationService.getById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
