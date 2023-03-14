package com.lawencon.community.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.userpremium.PojoUserPremiumReq;
import com.lawencon.community.pojo.userpremium.PojoUserPremiumRes;
import com.lawencon.community.service.UserPremiumService;

@RestController
@RequestMapping("users/premium")
public class UserPremiumController {
	private final UserPremiumService userPremiumService;

	public UserPremiumController(UserPremiumService userPremiumService) {
		this.userPremiumService = userPremiumService;
	}
	
	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoUserPremiumReq data) {
		final PojoRes res = userPremiumService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoUserPremiumReq data) {
		final PojoRes res = userPremiumService.approve(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PojoUserPremiumRes>> getAll() {
		final List<PojoUserPremiumRes> res = userPremiumService.getAllApprove();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
