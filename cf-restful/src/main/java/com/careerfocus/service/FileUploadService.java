package com.careerfocus.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.CommonDAO;
import com.careerfocus.dao.FileUploadDAO;
import com.careerfocus.entity.CoachingTypeCategory;
import com.careerfocus.entity.CoachingTypeCategoryImage;
import com.careerfocus.entity.CoachingTypeCategorySub;
import com.careerfocus.entity.CoachingTypeCategorySubImage;
import com.careerfocus.entity.CoachingTypeCategorySubUnit;
import com.careerfocus.entity.CoachingTypeCategorySubUnitImage;
import com.careerfocus.entity.UploadFile;
import com.careerfocus.repository.CoachingTypeCategoryImageRepository;
import com.careerfocus.repository.CoachingTypeCategoryRepository;
import com.careerfocus.repository.CoachingTypeCategorySubImageRepository;
import com.careerfocus.repository.CoachingTypeCategorySubRepository;
import com.careerfocus.repository.CoachingTypeCategorySubUnitImageRepository;
import com.careerfocus.repository.CoachingTypeCategorySubUnitRepository;
import com.careerfocus.repository.FileUploadRepository;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FileUploadService {

	@Autowired
	FileUploadRepository fuRepository;

	@Autowired
	FileUploadDAO fileDAO;

	@Autowired
	CommonDAO commonDAO;

	@Autowired
	CoachingTypeCategoryRepository coachingTypeCategoryRepository;

	@Autowired
	CoachingTypeCategoryImageRepository ctcRepository;

	@Autowired
	CoachingTypeCategorySubRepository coachingTypeCategorySubRepository;

	@Autowired
	CoachingTypeCategorySubImageRepository ctcsRepository;

	@Autowired
	CoachingTypeCategorySubUnitRepository coachingTypeCategorySubUnitRepository;

	@Autowired
	CoachingTypeCategorySubUnitImageRepository ctcsuRepository;

	public UploadFile saveFile(UploadFile file) {
		return fuRepository.save(file);
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
			for (UploadFile uFile : file) {
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
			// System.out.println("Name = " +
			// fuRepository.findOne(fileId).getFileName());
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

	public void deleteMaterial(Integer fileId) {
		fuRepository.delete(fileId);

	}

	@Transactional
	public Response saveCoachingTypeCategory(String coachingTypeCategoryJson, MultipartFile image) throws IOException {
		CoachingTypeCategory coachingTypeCategory = new ObjectMapper().readValue(coachingTypeCategoryJson,
				CoachingTypeCategory.class);
		coachingTypeCategory.setImgFileName(image.getOriginalFilename());
		List<Error> errors = validateCoachingTypeCategory(coachingTypeCategory);
		if (errors != null && !errors.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED)
					.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		}

		coachingTypeCategory = coachingTypeCategoryRepository.save(coachingTypeCategory);

		CoachingTypeCategoryImage ctcImage = new CoachingTypeCategoryImage(
				coachingTypeCategory.getCoachingTypeCategoryId(), commonDAO.resizeImage(image, true));
		ctcRepository.save(ctcImage);
		return Response.ok(coachingTypeCategory).build();
	}

	private List<Error> validateCoachingTypeCategory(CoachingTypeCategory coachingTypeCategory) {
		List<Error> subErrors = new ArrayList<Error>();
		if (coachingTypeCategory.getName() == null || coachingTypeCategory.getName().isEmpty()) {
			subErrors.add(new Error(ErrorCodes.MATERIAL_NAME_EMPTY, ErrorCodes.MATERIAL_NAME_EMPTY_MSG));
		}
		return subErrors;
	}

	@Transactional
	public Response saveCoachingTypeCategorySub(String coachingTypeCategorySubJson, MultipartFile image)
			throws IOException {
		CoachingTypeCategorySub coachingTypeCategorySub = new ObjectMapper().readValue(coachingTypeCategorySubJson,
				CoachingTypeCategorySub.class);
		coachingTypeCategorySub.setImgFileName(image.getOriginalFilename());
		List<Error> errors = validateCoachingTypeCategorySub(coachingTypeCategorySub);
		if (errors != null && !errors.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED)
					.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		}

		coachingTypeCategorySub = coachingTypeCategorySubRepository.save(coachingTypeCategorySub);

		CoachingTypeCategorySubImage ctcsImage = new CoachingTypeCategorySubImage(
				coachingTypeCategorySub.getCoachingTypeCategorySubId(), commonDAO.resizeImage(image, true));
		ctcsRepository.save(ctcsImage);
		return Response.ok(coachingTypeCategorySub).build();
	}

	private List<Error> validateCoachingTypeCategorySub(CoachingTypeCategorySub coachingTypeCategorySub) {
		List<Error> subErrors = new ArrayList<Error>();
		if (coachingTypeCategorySub.getName() == null || coachingTypeCategorySub.getName().isEmpty()) {
			subErrors.add(new Error(ErrorCodes.MATERIAL_NAME_EMPTY, ErrorCodes.MATERIAL_NAME_EMPTY_MSG));
		}
		return subErrors;
	}

	@Transactional
	public Response saveCoachingTypeCategorySubUnit(String coachingTypeCategorySubUnitJson, MultipartFile image)
			throws IOException {
		CoachingTypeCategorySubUnit coachingTypeCategorySubUnit = new ObjectMapper()
				.readValue(coachingTypeCategorySubUnitJson, CoachingTypeCategorySubUnit.class);
		coachingTypeCategorySubUnit.setImgFileName(image.getOriginalFilename());
		List<Error> errors = validateCoachingTypeCategorySubUnit(coachingTypeCategorySubUnit);
		if (errors != null && !errors.isEmpty()) {
			return Response.status(ErrorCodes.VALIDATION_FAILED)
					.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
		}

		coachingTypeCategorySubUnit = coachingTypeCategorySubUnitRepository.save(coachingTypeCategorySubUnit);

		CoachingTypeCategorySubUnitImage ctcImage = new CoachingTypeCategorySubUnitImage(
				coachingTypeCategorySubUnit.getCoachingTypeCategorySubUnitId(), commonDAO.resizeImage(image, true));
		ctcsuRepository.save(ctcImage);
		return Response.ok(coachingTypeCategorySubUnit).build();
	}

	private List<Error> validateCoachingTypeCategorySubUnit(CoachingTypeCategorySubUnit coachingTypeCategorySubUnit) {
		List<Error> subErrors = new ArrayList<Error>();
		if (coachingTypeCategorySubUnit.getName() == null || coachingTypeCategorySubUnit.getName().isEmpty()) {
			subErrors.add(new Error(ErrorCodes.MATERIAL_NAME_EMPTY, ErrorCodes.MATERIAL_NAME_EMPTY_MSG));
		}
		return subErrors;
	}

	public boolean tagFileToCoachingTypeCategory(int coachingTypeCategory, int fileId) {
		return fileDAO.tagFileToCoachingTypeCategory(coachingTypeCategory, fileId) > 0 ? true : false;
	}

	public boolean tagFileToCoachingTypeCategorySub(int coachingTypeCategorySub, int fileId) {
		return fileDAO.tagFileToCoachingTypeCategorySub(coachingTypeCategorySub, fileId) > 0 ? true : false;
	}

	public boolean tagFileToCoachingTypeCategorySubUnit(int coachingTypeCategorySubUnit, int fileId) {
		return fileDAO.tagFileToCoachingTypeCategorySubUnit(coachingTypeCategorySubUnit, fileId) > 0 ? true : false;
	}

	public List<CoachingTypeCategory> getAllCoachingTypeCategory(int coachingTypeId) {
		List<CoachingTypeCategory> coachingTypes = coachingTypeCategoryRepository
				.findByCoachingTypeIdOrderByCoachingTypeIdDesc(coachingTypeId);
		return coachingTypes;
	}
	
	public List<CoachingTypeCategorySub> getAllCoachingTypeCategorySub(int coachingTypeCategoryId) {
		List<CoachingTypeCategorySub> coachingTypes = coachingTypeCategorySubRepository
				.findByCoachingTypeCategoryIdOrderByCoachingTypeCategoryIdDesc(coachingTypeCategoryId);
		return coachingTypes;
	}
	
	public List<CoachingTypeCategorySubUnit> getAllCoachingTypeCategorySubUnit(int coachingTypeCategorySubId) {
		List<CoachingTypeCategorySubUnit> coachingTypes = coachingTypeCategorySubUnitRepository
				.findByCoachingTypeCategorySubIdOrderByCoachingTypeCategorySubIdDesc(coachingTypeCategorySubId);
		return coachingTypes;
	}

	public List<Map<String, Object>> getAllCoachingTypeCategoryMaterials(Integer coachingTypeCategoryId) {
		return fileDAO.getAllCoachingTypeCategoryMaterials(coachingTypeCategoryId);
	}

	public List<Map<String, Object>> getAllCoachingTypeCategorySubMaterials(Integer coachingTypeCategorySubId) {
		return fileDAO.getAllCoachingTypeCategorySubMaterials(coachingTypeCategorySubId);
	}

	public List<Map<String, Object>> getAllCoachingTypeCategorySubUnitMaterials(Integer coachingTypeCategorySubUnitId) {
		return fileDAO.getAllCoachingTypeCategorySubUnitMaterials(coachingTypeCategorySubUnitId);
	}
}
