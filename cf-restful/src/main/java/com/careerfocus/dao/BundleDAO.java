package com.careerfocus.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.careerfocus.entity.Bundle;

@Repository
public class BundleDAO {

	private JdbcTemplate template;

	@Autowired
	public BundleDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<Map<String, Object>> getQPBundleList(int coachingType) {
		String query = "select * from bundle where coaching_type = ?";
		return template.queryForList(query, coachingType);
	}

	public String editBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBundleQPs(Integer bundleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addQptoBundle(Integer bundleId, Integer qpId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String removeQpfromBundle(Integer bundleId, Integer qpId) {
		// TODO Auto-generated method stub
		return null;
	}

}
