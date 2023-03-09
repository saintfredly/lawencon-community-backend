package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.model.Position;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.position.PojoPositionReq;
import com.lawencon.community.pojo.position.PojoPositionResGetAll;
import com.lawencon.community.util.GenerateId;

@Service
public class PositionService {
	private final PositionDao positionDao;
	
	public PositionService(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
	
	public PojoRes save(PojoPositionReq data) {
		ConnHandler.begin();

		final String generateCode = GenerateId.generateCode(5);

		Position position = new Position();

		position.setPositionCode(generateCode);
		position.setPositionName(data.getPositionName());
		position.setIsActive(true);

		positionDao.save(position);
		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Save Success!");
		return pojoRes;
	}
	
	public PojoRes update(PojoPositionReq data) {
		ConnHandler.begin();

		Position position = new Position();

		position = positionDao.getByIdAndDetach(data.getId()).get();
		position.setPositionName(data.getPositionName());
		position.setVersion(data.getVer());
		position.setIsActive(data.getIsActive());

		positionDao.save(position);
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

		Boolean result = positionDao.deleteById(Position.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}

	}

	public List<PojoPositionResGetAll> getAll() {
		final List<Position> position = positionDao.getAll();
		final List<PojoPositionResGetAll> pojoResGet = new ArrayList<>();

		for (int i = 0; i < position.size(); i++) {
			final PojoPositionResGetAll pojoPositionRes = new PojoPositionResGetAll();
			
			pojoPositionRes.setPositionId(position.get(i).getId());
			pojoPositionRes.setPositionCode(position.get(i).getPositionCode());
			pojoPositionRes.setPositionName(position.get(i).getPositionName());
			pojoPositionRes.setCreatedAt(position.get(i).getCreatedAt());
			pojoPositionRes.setIsActive(position.get(i).getIsActive());
			pojoPositionRes.setVer(position.get(i).getVersion());
			
			pojoResGet.add(pojoPositionRes);
		}

		return pojoResGet;

	}
	
	public Optional<Position> getById(String id) {
		return positionDao.getById(id);
	}
	
	public Optional<Position> getByIdAndDetach(String id) {
		return positionDao.getByIdAndDetach(id);
	}
	
}
