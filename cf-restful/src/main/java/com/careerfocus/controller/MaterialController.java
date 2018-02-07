package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.careerfocus.dao.FileUploadDAO;
import com.careerfocus.entity.UploadFile;

@RestController
@RequestMapping("/material")
public class MaterialController {

	@Autowired
	FileUploadDAO fileUploadDAO;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @RequestPart(value = "file", required = true) final MultipartFile fileUpload)
			throws Exception {

		if (fileUpload != null && fileUpload.getSize() > 0) {
//			for (MultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + fileUpload.getOriginalFilename());

				UploadFile uploadFile = new UploadFile();
				uploadFile.setFileName(fileUpload.getOriginalFilename());
				uploadFile.setData(fileUpload.getBytes());
				fileUploadDAO.save(uploadFile);
			}
//		}

		return "Success";
	}

}
