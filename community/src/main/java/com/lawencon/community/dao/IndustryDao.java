package com.lawencon.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.model.Industry;

@Repository
public class IndustryDao extends BaseMasterDao<Industry> {


	@SuppressWarnings("unchecked")
	@Override
	List<Industry> getAll() {
		final String sql = "SELECT * FROM t_industry WHERE is_active = TRUE";
		final List<Industry> res = ConnHandler.getManager().createNativeQuery(sql, Industry.class).getResultList();		
		return res;
	}


	@Override
	Optional<Industry> getById(Long id) {
		final Industry industry = ConnHandler.getManager().find(Industry.class, id);
		return Optional.ofNullable(industry);
	}

}
