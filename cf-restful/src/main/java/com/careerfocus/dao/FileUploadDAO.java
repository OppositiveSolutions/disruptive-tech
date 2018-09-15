package com.careerfocus.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {

	private JdbcTemplate template;

	@Autowired
	public FileUploadDAO(JdbcTemplate template) {
		this.template = template;
	}

	public int tagFileToCoachingType(int coachingType, int fileId) {
		String query = "insert into coaching_type_material (coaching_type_id,file_id) values (?,?)";
		return template.update(query, coachingType, fileId);
	}

	public int tagFileToCoachingTypeCategory(int coachingTypeCategory, int fileId) {
		String query = "insert into coaching_type_category_material (coaching_type_category_id,file_id) values (?,?)";
		return template.update(query, coachingTypeCategory, fileId);
	}

	public int tagFileToCoachingTypeCategorySub(int coachingTypeCategorySub, int fileId) {
		String query = "insert into coaching_type_category_sub_material (coaching_type_category_sub_id,file_id) values (?,?)";
		return template.update(query, coachingTypeCategorySub, fileId);
	}

	public int tagFileToCoachingTypeCategorySubUnit(int coachingTypeCategorySubUnit, int fileId) {
		String query = "insert into coaching_type_category_sub_unit_material (coaching_type_category_sub_unit_id,file_id) values (?,?)";
		return template.update(query, coachingTypeCategorySubUnit, fileId);
	}

	public List<Map<String, Object>> getAllCoachingTypeMaterials(Integer coachingTypeCategoryId) {
		String query = "select fu.file_id as id, fu.file_name as fileName from files_upload fu"
				+ " inner join coaching_type_material m on fu.file_id = m.file_id" + " WHERE m.coaching_type_id = ?";
		return template.queryForList(query, coachingTypeCategoryId);
	}

	public List<Map<String, Object>> getAllCoachingTypeCategoryMaterials(Integer coachingTypeCategoryId) {
		String query = "select fu.file_id as id, fu.file_name as fileName from files_upload fu"
				+ " inner join coaching_type_category_material mc on fu.file_id = mc.file_id"
				+ " WHERE mc.coaching_type_category_id = ?";
		return template.queryForList(query, coachingTypeCategoryId);
	}

	public List<Map<String, Object>> getAllCoachingTypeCategorySubMaterials(Integer coachingTypeCategorySubId) {
		String query = "select fu.file_id as id, fu.file_name as fileName from files_upload fu"
				+ " inner join coaching_type_category_material_sub mcs on fu.file_id = mcs.file_id"
				+ " WHERE mcs.coaching_type_category_sub_id = ?";
		return template.queryForList(query, coachingTypeCategorySubId);
	}

	public List<Map<String, Object>> getAllCoachingTypeCategorySubUnitMaterials(Integer coachingTypeCategorySubUnitId) {
		String query = "select fu.file_id as id, fu.file_name as fileName from files_upload fu"
				+ " inner join coaching_type_category_material_sub_unit mcsu on fu.file_id = mcsu.file_id"
				+ " WHERE mcsu.coaching_type_category_sub_unit_id = ?";
		return template.queryForList(query, coachingTypeCategorySubUnitId);
	}

	public List<Map<String, Object>> getCoachingTypeCategorys(int coachingTypeId) {
		String query = "SELECT coaching_type_category_id as coachingTypeCategoryId, name FROM coaching_type_category WHERE coaching_type_id = ?";
		return template.queryForList(query, coachingTypeId);
	}

	public List<Map<String, Object>> getCoachingTypeCategorySubs(int coachingTypeCategoryId) {
		String query = "SELECT coaching_type_category_sub_id as coachingTypeCategorySubId, name FROM coaching_type_category_sub WHERE coaching_type_category_id = ?";
		return template.queryForList(query, coachingTypeCategoryId);
	}

	public List<Map<String, Object>> getCoachingTypeCategorySubUnits(int coachingTypeCategorySubId) {
		String query = "SELECT coaching_type_category_sub_unit_id as coachingTypeCategorySubUnitId, name FROM coaching_type_category_sub_unit WHERE coaching_type_category_sub_id = ?";
		return template.queryForList(query, coachingTypeCategorySubId);
	}

}
