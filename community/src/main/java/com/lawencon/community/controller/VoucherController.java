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

import com.lawencon.community.model.Voucher;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.voucher.PojoVoucherReq;
import com.lawencon.community.pojo.voucher.PojoVoucherRes;
import com.lawencon.community.service.VoucherService;

@RestController
@RequestMapping("voucher")
public class VoucherController {

	private final VoucherService voucherService;

	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoVoucherReq data) {
		final PojoRes res = voucherService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoVoucherReq data) {
		final PojoRes res = voucherService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = voucherService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PojoVoucherRes>> getAll() {
		final List<PojoVoucherRes> res = voucherService.getAll();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Voucher>> getById(@PathVariable("id") String id) {
		final Optional<Voucher> res = voucherService.getById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
