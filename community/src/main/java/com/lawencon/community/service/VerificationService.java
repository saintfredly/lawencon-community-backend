package com.lawencon.community.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.constant.RoleEnum;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dao.VerificationDao;
import com.lawencon.community.model.EmailDetails;
import com.lawencon.community.model.User;
import com.lawencon.community.model.Verification;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.verification.PojoVerificationReq;
import com.lawencon.community.util.GenerateId;

@Service
public class VerificationService {
	private final VerificationDao verificationDao;
	private final UserDao userDao;
	private final EmailService emailService;
	
	public VerificationService(VerificationDao verificationDao, UserDao userDao,
			EmailService emailService) {
		this.verificationDao = verificationDao;
		this.userDao = userDao;
		this.emailService = emailService;
	}
	
	public PojoRes save(PojoVerificationReq data) {
		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(6);

		Verification verification = new Verification();
		final User systemId = userDao.getUserByRoleCode(RoleEnum.SYSTEM.getRoleCode()).get();

		verification.setEmail(data.getEmail());
		verification.setVerificationCode(generateCode);
		verification.setExpire(LocalDateTime.now().plusHours(1));
		verification.setIsActive(true);

		verificationDao.saveNoLogin(verification, ()-> systemId.getId());
		ConnHandler.commit();
		
		final EmailDetails email = new EmailDetails();
		email.setRecipient(data.getEmail());
		email.setSubject("Kode Verifikasai");
		email.setMsgBody("Hai, untuk menyelesaikan proses registrasi, anda harus memasukkan kode yang diberikan pada email ini. Berikut kode verifikasinya " + 
				"\n Kode verifikasi anda adalah: " + verification.getVerificationCode());
		
		new Thread(() -> emailService.sendSimpleMail(email)).start();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Send Verification Code Success!");
		
		return pojoRes;
	}
	
	public PojoRes update(PojoVerificationReq data) {
		ConnHandler.begin();

		Verification verification = new Verification();
		final User systemId = userDao.getUserByRoleCode(RoleEnum.SYSTEM.getRoleCode()).get();

		verification = verificationDao.getByIdAndDetach(data.getId()).get();
		verification.setVerificationCode(data.getVerificationCode());
		verification.setVersion(data.getVer());
		verification.setIsActive(data.getIsActive());

		verificationDao.saveNoLogin(verification, ()-> systemId.getId());
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

		Boolean result = verificationDao.deleteById(Verification.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}

	}
	
	public Optional<Verification> getById(String id) {
		return verificationDao.getById(id);
	}
	
	public Optional<Verification> getByIdAndDetach(String id) {
		return verificationDao.getByIdAndDetach(id);
	}
}
