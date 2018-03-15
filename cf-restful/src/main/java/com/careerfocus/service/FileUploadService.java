package com.careerfocus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.CommonDAO;
import com.careerfocus.dao.FileUploadDAO;
import com.careerfocus.entity.UploadFile;
import com.careerfocus.repository.FileUploadRepository;

@Service
public class FileUploadService {

	@Autowired
	FileUploadRepository fuRepository;

	@Autowired
	FileUploadDAO fileUploadDAO;
	
	@Autowired
	CommonDAO commonDAO;

	public void saveFile(UploadFile file) {
		fuRepository.save(file);
	}

	public List<Map<String,Object>> getAllMaterials() {
		List<Map<String, Object>> coachingTypes = commonDAO.getCoachingTypes();
		List<Map<String, Object>> materials =  new ArrayList<Map<String, Object>>();
		Map<String, Object> material = null;
		for (int i = 0; i < coachingTypes.size(); i++) {
			material = new HashMap<String, Object>();
			material.put("name", coachingTypes.get(i).get("name"));
			material.put("materials", fuRepository.findAllByIsCurrentOrderByIdAsc(Integer.parseInt(coachingTypes.get(i).get("coachingTypeId").toString())));
		materials.add(material);
		}
		return materials;
	}

	public byte[] getMaterial(Integer fileId) {
		return fuRepository.findOne(fileId).getData();
	}

	public void deleteMaterrial(Integer fileId) {
		fuRepository.delete(fileId);

	}

}
