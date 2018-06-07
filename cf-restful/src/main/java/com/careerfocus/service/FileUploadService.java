package com.careerfocus.service;

import java.io.IOException;
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
			List<UploadFile> finalFile = new ArrayList<UploadFile>(); 
			List<UploadFile> file = fuRepository.findAllByIsCurrentOrderByIdAsc(
					Integer.parseInt(coachingTypes.get(i).get("coachingTypeId").toString()));
			for (UploadFile uFile: file) {
				uFile.setData(null);
				finalFile.add(uFile);
			}
			material.put("materials", finalFile);
			materials.add(material);
		}
		return materials;
	}

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
