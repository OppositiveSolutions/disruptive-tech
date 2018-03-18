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

//	private static final int BUFFER_SIZE = 4096;

	public void saveFile(UploadFile file) {
		fuRepository.save(file);
	}

	public List<Map<String, Object>> getAllMaterials() {
		List<Map<String, Object>> coachingTypes = commonDAO.getCoachingTypes();
		List<Map<String, Object>> materials = new ArrayList<Map<String, Object>>();
		Map<String, Object> material = null;
		for (int i = 0; i < coachingTypes.size(); i++) {
			material = new HashMap<String, Object>();
			material.put("name", coachingTypes.get(i).get("name"));
			material.put("materials", fuRepository.findAllByIsCurrentOrderByIdAsc(
					Integer.parseInt(coachingTypes.get(i).get("coachingTypeId").toString())));
			materials.add(material);
		}
		return materials;
	}

	public byte[] getMaterial(Integer fileId) {
//		Blob blob;
//		try {
//			blob = new javax.sql.rowset.serial.SerialBlob(fuRepository.findOne(fileId).getData());
//			// Blob blob = result.getBlob("photo");
//			InputStream inputStream = blob.getBinaryStream();
//			OutputStream outputStream = new FileOutputStream(fuRepository.findOne(fileId).getFileName());
//
//			int bytesRead = -1;
//			byte[] buffer = new byte[BUFFER_SIZE];
//			while ((bytesRead = inputStream.read(buffer)) != -1) {
//				outputStream.write(buffer, 0, bytesRead);
//			}
//
//			inputStream.close();
//			outputStream.close();
//			System.out.println("File saved");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
		return fuRepository.findOne(fileId).getData();
	}

	public void deleteMaterrial(Integer fileId) {
		fuRepository.delete(fileId);

	}

}
