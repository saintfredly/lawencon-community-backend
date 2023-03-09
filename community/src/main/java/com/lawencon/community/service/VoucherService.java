package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.VoucherDao;
import com.lawencon.community.model.Voucher;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.voucher.PojoVoucherReq;
import com.lawencon.community.pojo.voucher.PojoVoucherRes;
import com.lawencon.community.util.GenerateId;

@Service
public class VoucherService {
	private final VoucherDao voucherDao;

	public VoucherService(VoucherDao voucherDao) {
		this.voucherDao = voucherDao;
	}

	public PojoRes save(PojoVoucherReq data) {
		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(5);
		Voucher voucher = new Voucher();

		if (data.getVoucherCode() != null) {
			voucher.setVoucherCode(data.getVoucherCode());
		} else {
			voucher.setVoucherCode(generateCode);
		}

		voucher.setVoucherName(data.getVoucherName());
		voucher.setVoucherDescription(data.getVoucherDescription());
		voucher.setAmount(data.getAmount());
		voucher.setDateStart(data.getDateStart());
		voucher.setDateExpired(data.getDateExpired());

		voucher.setIsActive(true);

		voucherDao.save(voucher);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;
	}

	public PojoRes update(PojoVoucherReq data) {
		ConnHandler.begin();

		Voucher voucher = new Voucher();

		voucher = voucherDao.getByIdAndDetach(data.getId()).get();

		voucher.setVoucherCode(data.getVoucherCode());
		voucher.setVoucherName(data.getVoucherName());
		voucher.setVoucherDescription(data.getVoucherDescription());
		voucher.setAmount(data.getAmount());
		voucher.setDateStart(data.getDateStart());
		voucher.setDateExpired(data.getDateExpired());

		voucher.setVersion(data.getVer());
		voucher.setIsActive(data.getIsActive());

		voucherDao.save(voucher);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Update Success!");
		return pojoRes;
	}

	public PojoRes deleteById(String id) {
		ConnHandler.begin();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Delete Success!");
		final PojoRes pojoResFail = new PojoRes();
		pojoResFail.setMessage("Delete Failed!");

		Boolean result = voucherDao.deleteById(Voucher.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}
	}

	public List<PojoVoucherRes> getAll() {
		final List<Voucher> voucher = voucherDao.getAll();
		final List<PojoVoucherRes> pojoResGet = new ArrayList<>();

		for (int i = 0; i < voucher.size(); i++) {
			final PojoVoucherRes pojoVoucherRes = new PojoVoucherRes();
			pojoVoucherRes.setVoucherCode(voucher.get(i).getVoucherCode());
			pojoVoucherRes.setVoucherName(voucher.get(i).getVoucherName());
			pojoVoucherRes.setVoucherDescription(voucher.get(i).getVoucherDescription());
			pojoVoucherRes.setAmount(voucher.get(i).getAmount());
			pojoVoucherRes.setDateStart(voucher.get(i).getDateStart());
			pojoVoucherRes.setDateExpired(voucher.get(i).getDateExpired());
			pojoVoucherRes.setCreatedAt(voucher.get(i).getCreatedAt());
			pojoVoucherRes.setIsActive(voucher.get(i).getIsActive());
			pojoVoucherRes.setVer(voucher.get(i).getVersion());
			pojoResGet.add(pojoVoucherRes);
		}

		return pojoResGet;
	}

	public Optional<Voucher> getById(String id) {
		return voucherDao.getById(id);
	}

}
