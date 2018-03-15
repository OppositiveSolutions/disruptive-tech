package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Autowired
	FileUploadService service;

	@RequestMapping(value = "/upload/{coachingType}", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @PathVariable(required = true) int coachingType,
			@RequestPart(value = "file", required = true) final MultipartFile fileUpload) throws Exception {

		if (fileUpload != null && fileUpload.getSize() > 0) {
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());

			// UploadFile uploadFile = new UploadFile();
			// uploadFile.setFileName(fileUpload.getOriginalFilename());
			// uploadFile.setData(fileUpload.getBytes());
			// fileUploadDAO.save(uploadFile);
			UploadFile file = new UploadFile(fileUpload.getOriginalFilename(), fileUpload.getBytes(), coachingType, 1);
			service.saveFile(file);
		}

		return "Success";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Response getAllMaterials(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return Response.ok(service.getAllMaterials()).build();
	}

	@RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
	public byte[] getMaterial(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) Integer fileId) throws Exception {
		return service.getMaterial(fileId);
	}

	@RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
	public Response deleteSliderImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileId") Integer fileId) throws Exception {
		service.deleteMaterrial(fileId);
		return Response.ok().message(UIMessages.SLIDER_MATERIAL_DELETED).build();
	}

}
