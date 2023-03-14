package com.lawencon.community.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PaymentDao;
import com.lawencon.community.dao.ProductDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dao.VoucherDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Product;
import com.lawencon.community.model.User;
import com.lawencon.community.model.Voucher;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.payment.PojoPaymentReq;
import com.lawencon.community.util.GenerateId;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PaymentService {
	private final PaymentDao paymentDao;
	private final VoucherDao voucherDao;
	private final ProductDao productDao;
	private final FileDao fileDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	public PaymentService(PaymentDao paymentDao, VoucherDao voucherDao, ProductDao productDao,
			FileDao fileDao, UserDao userDao, PrincipalService principalService) {
		this.paymentDao = paymentDao;
		this.voucherDao = voucherDao;
		this.productDao = productDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	public PojoRes save(PojoPaymentReq data) {
		ConnHandler.begin();

		BigDecimal grandTotal = null;
		Payment payment = new Payment();
		
		if(data.getVoucherCode() != null) {
			final Voucher voucherId = voucherDao.getByVoucherCode(data.getVoucherCode()).get();
			
			final Voucher voucher = voucherDao.getByIdRef(voucherId.getId()).get();
			payment.setVoucher(voucher);			
		}
		
		final Product product = productDao.getByIdRef(data.getProductId()).get();
		payment.setProduct(product);

		payment.setDatePayment(LocalDateTime.now());
		grandTotal = product.getPrice();
//		if(voucher != null) {
//			grandTotal = product.getPrice() - voucher.getAmount();
//		}
		payment.setGrandTotal(grandTotal);
		
		final File file = new File();
		file.setFileName(data.getPhoto().getFileName());
		file.setFileContent(data.getPhoto().getFileContent());
		file.setFileExtension(data.getPhoto().getFileExtension());
		
		fileDao.save(file);
		payment.setFile(file);
		
		final User user = userDao.getByIdRef(principalService.getAuthPrincipal()).get();
		payment.setUser(user);
	
		payment.setIsApproved(false);
		payment.setIsActive(true);

		paymentDao.save(payment);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;
	}

	public PojoRes update(PojoPaymentReq data) {
		ConnHandler.begin();

		Payment payment = new Payment();

		payment = paymentDao.getByIdAndDetach(data.getId()).get();
		payment.setIsApproved(true);
		payment.setVersion(data.getVer());
		payment.setIsActive(data.getIsActive());

		paymentDao.save(payment);
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

		Boolean result = paymentDao.deleteById(Payment.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}
	}

//	public List<PojoVoucherRes> getAll() {
//		final List<Voucher> voucher = voucherDao.getAll();
//		final List<PojoVoucherRes> pojoResGet = new ArrayList<>();
//
//		for (int i = 0; i < voucher.size(); i++) {
//			final PojoVoucherRes pojoVoucherRes = new PojoVoucherRes();
//			pojoVoucherRes.setVoucherCode(voucher.get(i).getVoucherCode());
//			pojoVoucherRes.setVoucherName(voucher.get(i).getVoucherName());
//			pojoVoucherRes.setVoucherDescription(voucher.get(i).getVoucherDescription());
//			pojoVoucherRes.setAmount(voucher.get(i).getAmount());
//			pojoVoucherRes.setDateStart(voucher.get(i).getDateStart());
//			pojoVoucherRes.setDateExpired(voucher.get(i).getDateExpired());
//			pojoVoucherRes.setCreatedAt(voucher.get(i).getCreatedAt());
//			pojoVoucherRes.setIsActive(voucher.get(i).getIsActive());
//			pojoVoucherRes.setVer(voucher.get(i).getVersion());
//			pojoResGet.add(pojoVoucherRes);
//		}
//
//		return pojoResGet;
//	}

	public Optional<Payment> getById(String id) {
		return paymentDao.getById(id);
	}
}
