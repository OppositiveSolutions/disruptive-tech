package com.careerfocus.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	private static final int BUFFER_SIZE = 16384;

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

	// public Blob getMaterial(Integer fileId) {
	// Blob blob = null;
	// try {
	// // System.out.println(fuRepository.findOne(fileId).getData() +
	// // "\n\n\n");
	// // System.out.println(fuRepository.findOne(fileId).getData().toString());
	// System.out.println(new String(fuRepository.findOne(fileId).getData()));
	// blob = new
	// javax.sql.rowset.serial.SerialBlob(fuRepository.findOne(fileId).getData());
	// // Blob blob = result.getBlob("photo");
	// InputStream inputStream = blob.getBinaryStream();
	// OutputStream outputStream = new
	// FileOutputStream(fuRepository.findOne(fileId).getFileName());
	//
	// int bytesRead = -1;
	// byte[] buffer = new byte[BUFFER_SIZE];
	// while ((bytesRead = inputStream.read(buffer)) != -1) {
	// outputStream.write(buffer, 0, bytesRead);
	// }
	//
	// inputStream.close();
	// outputStream.close();
	// System.out.println("File saved");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// // return true;
	// return blob;
	// }

	public boolean getMaterial(Integer fileId, HttpServletRequest request, HttpServletResponse response) {
		try {
//			System.out.println("Name = " + fuRepository.findOne(fileId).getFileName());
			String filename = fuRepository.findOne(fileId).getFileName().split("\\.")[0];
			String fileType = fuRepository.findOne(fileId).getFileName().split("\\.")[1];
			if (fileType.trim().equalsIgnoreCase("txt")) {
				response.setContentType("text/plain");
			} else if (fileType.trim().equalsIgnoreCase("doc")) {
				response.setContentType("application/msword");
			} else if (fileType.trim().equalsIgnoreCase("xls")) {
				response.setContentType("application/vnd.ms-excel");
			} else if (fileType.trim().equalsIgnoreCase("pdf")) {
				response.setContentType("application/pdf");
			} else if (fileType.trim().equalsIgnoreCase("ppt")) {
				response.setContentType("application/ppt");
			} else {
				response.setContentType("application/octet-stream");
			}

			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			response.setHeader("cache-control", "no-cache");
			response.setHeader("cache-control", "must-revalidate");

			ServletOutputStream outs = response.getOutputStream();
			outs.write(fuRepository.findOne(fileId).getData());
			outs.flush();
			outs.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	};

	public void deleteMaterrial(Integer fileId) {
		fuRepository.delete(fileId);

	}

}
