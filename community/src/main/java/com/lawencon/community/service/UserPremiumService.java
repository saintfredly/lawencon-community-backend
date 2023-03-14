package com.lawencon.community.service;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.constant.SubscribeEnum;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dao.UserPremiumDao;
import com.lawencon.community.dao.UserProfileDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.User;
import com.lawencon.community.model.UserPremium;
import com.lawencon.community.model.UserProfile;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.userpremium.PojoUserPremiumReq;
import com.lawencon.security.principal.PrincipalService;

@Service
public class UserPremiumService {
	private final UserPremiumDao userPremiumDao;
	private final FileDao fileDao;
	private final UserDao userDao;
	private final UserProfileDao userProfileDao;

	@Inject
	private final PrincipalService principalService;

	public UserPremiumService(UserProfileDao userProfileDao, UserDao userDao, FileDao fileDao,
			UserPremiumDao userPremiumDao, PrincipalService principalService) {
		this.userPremiumDao = userPremiumDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
		this.userDao = userDao;
		this.userProfileDao = userProfileDao;
	}

	public PojoRes save(PojoUserPremiumReq data) {
		ConnHandler.begin();

		UserPremium userPremium = new UserPremium();

		userPremium.setDatePayment(LocalDateTime.now());
		userPremium.setPrice(SubscribeEnum.PREMIUM.getPrice());

		final File file = new File();
		file.setFileName(data.getProofOfPayment().getFileName());
		file.setFileContent(data.getProofOfPayment().getFileContent());
		file.setFileExtension(data.getProofOfPayment().getFileExtension());
		file.setIsActive(true);

		fileDao.save(file);

		userPremium.setFile(file);

		final User user = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		userPremium.setUser(user);

		userPremium.setIsApproved(false);

		userPremium.setIsActive(true);
		userPremiumDao.save(userPremium);

		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Subscribe Premium Success, Please wait!");
		return pojoRes;
	}

	public PojoRes approve(PojoUserPremiumReq data) {
		ConnHandler.begin();

		UserPremium userPremium = new UserPremium();
		userPremium = userPremiumDao.getByIdAndDetach(UserPremium.class, data.getId());
		userPremium.setIsApproved(true);
		userPremium.setVersion(data.getVer());
		
		userPremiumDao.save(userPremium);
		
		UserProfile userProfiles = new UserProfile();
		userProfiles = userProfileDao.getUserProfile(userPremium.getUser().getId()).get();
		
		UserProfile userProfile = new UserProfile();
		userProfile = userProfileDao.getByIdAndDetach(UserProfile.class, userProfiles.getId());
		
		userProfile.setSubscribe(true);
		userProfile.setVersion(userProfiles.getVersion());
		
		userProfileDao.save(userProfile);

		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Approved!");
		return pojoRes;
	}
}
