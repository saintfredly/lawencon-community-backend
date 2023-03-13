package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.constant.RoleEnum;
import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dao.UserProfileDao;
import com.lawencon.community.dao.VerificationDao;
import com.lawencon.community.model.EmailDetails;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.community.model.UserProfile;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.user.PojoUserReq;
import com.lawencon.community.util.GenerateId;

@Service
public class UserService implements UserDetailsService {
	private final UserDao userDao;
	private final RoleDao roleDao;
	private final UserProfileDao userProfileDao;
	private final PositionDao positionDao; 
	private final VerificationDao verificationDao;
	private final EmailService emailService;
	
	@Autowired
    private PasswordEncoder encoder;

	public UserService(UserDao userDao, RoleDao roleDao, UserProfileDao userProfileDao,
			PositionDao positionDao, VerificationDao verificationDao, EmailService emailService) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.userProfileDao = userProfileDao;
		this.positionDao = positionDao;
		this.verificationDao = verificationDao;
		this.emailService = emailService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> user = userDao.getByEmail(username);

		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(username, user.get().getPasswords(),
					new ArrayList<>());
		}
		throw new UsernameNotFoundException("Email and Password Not Match!");
	}

	public Optional<User> getByEmail(String email) {
		return userDao.getByEmail(email);
	}
	
	public PojoRes register(PojoUserReq data) {
		ConnHandler.begin();
//		final String verificationCode = GenerateId.generateCode(6);
		
		if(verificationDao.getByVerificationCode(data.getEmail(), data.getVerificationCode()).isEmpty()) {
			throw new RuntimeException("Kode verifikasi anda tidak cocok atau masa berlakunya telah berakhir!");
		}
		
		final User systemId = userDao.getUserByRoleCode(RoleEnum.SYSTEM.getRoleCode()).get();
		final Role role = roleDao.getRoleByRoleCode(RoleEnum.MEMBER.getRoleCode()).get();
		
		final Position position = positionDao.getByIdRef(data.getPositionId()).get();
		
		final User user = new User();
		user.setEmail(data.getEmail());
		user.setPasswords(encoder.encode(data.getPassword()));
		user.setRole(role);
		user.setVerificationCode(data.getVerificationCode());
		user.setIsVerified(true);
		user.setCreatedBy(systemId.getId());
		user.setIsActive(true);
		userDao.saveNoLogin(user, ()-> systemId.getId());
		
		final UserProfile userProfile = new UserProfile();
		userProfile.setUser(user);
		userProfile.setFullName(data.getFullName());
		userProfile.setCompany(data.getCompany());
		userProfile.setPosition(position);
		userProfile.setCreatedBy(systemId.getId());
		userProfile.setIsActive(true);
		userProfileDao.saveNoLogin(userProfile, ()-> systemId.getId());
		ConnHandler.commit();
		
		final EmailDetails email = new EmailDetails();
		email.setRecipient(data.getEmail());
		email.setSubject("Registrasi Berhasil");
//		ini harusnya stringbuilder
		email.setMsgBody("Hai, selamat anda telah berhasil registrasi, password yang anda buat: " + data.getPassword());
		
		new Thread(() -> emailService.sendSimpleMail(email)).start();
		
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Register Success!");
		
		return pojoRes;
	}
}
