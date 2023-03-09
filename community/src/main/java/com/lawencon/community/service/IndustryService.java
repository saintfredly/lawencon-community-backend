package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.model.Industry;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.industry.PojoIndustryReq;
import com.lawencon.community.pojo.industry.PojoIndustryResGetAll;
import com.lawencon.community.util.GenerateId;

@Service
public class IndustryService {
	private final IndustryDao industryDao;
	
	public IndustryService(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}
	
	public PojoRes save(PojoIndustryReq data) {
		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(5);

		Industry industry = new Industry();
		
		industry.setIndustryCode(generateCode);
		industry.setIndustryName(data.getIndustryName());
		industry.setIsActive(true);

		industryDao.save(industry);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;
	}
	
	public PojoRes update(PojoIndustryReq data) {
		ConnHandler.begin();

		Industry industry = new Industry();

		industry = industryDao.getByIdAndDetach(data.getId()).get();
		industry.setIndustryName(data.getIndustryName());
		industry.setVersion(data.getVer());
		industry.setIsActive(data.getIsActive());

		industryDao.save(industry);
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

		Boolean result = industryDao.deleteById(Industry.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}

	}

	public List<PojoIndustryResGetAll> getAll() {
		final List<Industry> industry = industryDao.getAll();
		final List<PojoIndustryResGetAll> pojoResGet = new ArrayList<>();

		for (int i = 0; i < industry.size(); i++) {
			final PojoIndustryResGetAll pojoIndustryRes = new PojoIndustryResGetAll();
			
			pojoIndustryRes.setIndustryId(industry.get(i).getId());
			pojoIndustryRes.setIndustryCode(industry.get(i).getIndustryCode());
			pojoIndustryRes.setIndustryName(industry.get(i).getIndustryName());
			pojoIndustryRes.setCreatedAt(industry.get(i).getCreatedAt());
			pojoIndustryRes.setIsActive(industry.get(i).getIsActive());
			pojoIndustryRes.setVer(industry.get(i).getVersion());
			
			pojoResGet.add(pojoIndustryRes);
		}

		return pojoResGet;

	}
	
	public Optional<Industry> getById(String id) {
		return industryDao.getById(id);
	}
	
	public Optional<Industry> getByIdAndDetach(String id) {
		return industryDao.getByIdAndDetach(id);
	}
}
