package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.careerfocus.constants.UIMessages;
import com.careerfocus.entity.UploadFile;
import com.careerfocus.service.FileUploadService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/material")
public class MaterialController {

	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	FileUploadService service;

	@RequestMapping(value = "/upload/{coachingType}", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @PathVariable(required = true) int coachingType,
			@RequestPart(value = "file", required = true) final MultipartFile fileUpload) throws Exception {

		if (fileUpload != null && fileUpload.getSize() > 0) {
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());

			UploadFile file = new UploadFile(fileUpload.getOriginalFilename(), fileUpload.getBytes(), coachingType, 1);
			service.saveFile(file);
		}

		return "Success";
	}

	@RequestMapping(value = "/category/upload/{coachingType}", method = RequestMethod.POST)
	public String handleCategoryFileUpload(HttpServletRequest request, @PathVariable(required = true) int coachingType,
			@RequestPart(value = "file", required = true) final MultipartFile fileUpload) throws Exception {

		if (fileUpload != null && fileUpload.getSize() > 0) {
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());

			UploadFile file = new UploadFile(fileUpload.getOriginalFilename(), fileUpload.getBytes(), 0, 1);
			file = service.saveFile(file);
			if (file.getId() > 0)
				if (service.tagFileToCoachingTypeCategory(coachingType, file.getId()))
					return "Success";
		}
		return "Failed";
	}

	@RequestMapping(value = "/category/sub/upload/{coachingType}", method = RequestMethod.POST)
	public String handleCategorySubFileUpload(HttpServletRequest request,
			@PathVariable(required = true) int coachingType,
			@RequestPart(value = "file", required = true) final MultipartFile fileUpload) throws Exception {

		if (fileUpload != null && fileUpload.getSize() > 0) {
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());

			UploadFile file = new UploadFile(fileUpload.getOriginalFilename(), fileUpload.getBytes(), coachingType, 1);
			file = service.saveFile(file);
			if (file.getId() > 0)
				if (service.tagFileToCoachingTypeCategorySub(coachingType, file.getId()))
					return "Success";
		}
		return "Failed";
	}

	@RequestMapping(value = "/category/sub/unit/upload/{coachingType}", method = RequestMethod.POST)
	public String handleCategorySubUnitFileUpload(HttpServletRequest request,
			@PathVariable(required = true) int coachingType,
			@RequestPart(value = "file", required = true) final MultipartFile fileUpload) throws Exception {

		if (fileUpload != null && fileUpload.getSize() > 0) {
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());

			UploadFile file = new UploadFile(fileUpload.getOriginalFilename(), fileUpload.getBytes(), coachingType, 1);
			file = service.saveFile(file);
			if (file.getId() > 0)
				if (service.tagFileToCoachingTypeCategorySubUnit(coachingType, file.getId()))
					return "Success";
		}
		return "Failed";
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public Response saveCoachingTypeCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(required = true) String coachingTypeCategory,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		log.debug("coachingTypeCategory: " + coachingTypeCategory.toString());
		log.debug("image: " + image.toString());
		return service.saveCoachingTypeCategory(coachingTypeCategory, image);
	}

	@RequestMapping(value = "/category/sub", method = RequestMethod.POST)
	public Response saveCoachingTypeCategorySub(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(required = true) String coachingTypeCategorySub,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		log.debug("coachingTypeCategorySub: " + coachingTypeCategorySub.toString());
		log.debug("image: " + image.toString());
		return service.saveCoachingTypeCategorySub(coachingTypeCategorySub, image);
	}

	@RequestMapping(value = "/category/sub/unit", method = RequestMethod.POST)
	public Response saveCoachingTypeCategorySubUnit(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(required = true) String coachingTypeCategorySubUnit,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		log.debug("coachingTypeCategorySubUnit: " + coachingTypeCategorySubUnit.toString());
		log.debug("image: " + image.toString());
		return service.saveCoachingTypeCategorySubUnit(coachingTypeCategorySubUnit, image);
	}

	@RequestMapping(value = "/category/{coachingTypeId}", method = RequestMethod.GET)
	public Response getAllCoachingTypeCategory(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer coachingTypeId) throws Exception {
		return Response.ok(service.getAllCoachingTypeCategory(coachingTypeId)).build();
	}

	@RequestMapping(value = "/category/sub/{coachingTypeCategoryId}", method = RequestMethod.GET)
	public Response getAllCoachingTypeCategorySub(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer coachingTypeCategoryId) throws Exception {
		return Response.ok(service.getAllCoachingTypeCategorySub(coachingTypeCategoryId)).build();
	}

	@RequestMapping(value = "/category/sub/unit/{coachingTypeCategorySubId}", method = RequestMethod.GET)
	public Response getAllCoachingTypeCategorySubUnit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer coachingTypeCategorySubId) throws Exception {
		return Response.ok(service.getAllCoachingTypeCategorySubUnit(coachingTypeCategorySubId)).build();
	}

	@RequestMapping(value = "/category/{coachingTypeCategoryId}/all", method = RequestMethod.GET)
	public Response getAllCoachingTypeCategoryMaterials(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer coachingTypeCategoryId) throws Exception {
		return Response.ok(service.getAllCoachingTypeCategoryMaterials(coachingTypeCategoryId)).build();
	}

	@RequestMapping(value = "/category/sub/{coachingTypeCategorySubId}/all", method = RequestMethod.GET)
	public Response getAllCoachingTypeCategorySubMaterials(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer coachingTypeCategorySubId) throws Exception {
		return Response.ok(service.getAllCoachingTypeCategorySubMaterials(coachingTypeCategorySubId)).build();
	}

	@RequestMapping(value = "/category/sub/unit/{coachingTypeCategorySubUnitId}/all", method = RequestMethod.GET)
	public Response getAllCoachingTypeCategorySubUnitMaterials(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer coachingTypeCategorySubUnitId) throws Exception {
		return Response.ok(service.getAllCoachingTypeCategorySubUnitMaterials(coachingTypeCategorySubUnitId)).build();
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Response getAllMaterials(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return Response.ok(service.getAllMaterials()).build();
	}

	@RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
	public boolean getMaterial(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer fileId) throws Exception {
		return service.getMaterial(fileId, request, response);
	}

	@RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
	public Response deleteSliderImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileId") Integer fileId) throws Exception {
		service.deleteMaterial(fileId);
		return Response.ok().message(UIMessages.SLIDER_MATERIAL_DELETED).build();
	}

}
