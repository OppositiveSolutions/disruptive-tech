package com.careerfocus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.entity.VideoTutorial;
import com.careerfocus.service.VideoTutorialService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/video-tutorial")
public class VideoTutorialController {


	@Autowired
	VideoTutorialService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllVideoTutorials(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return Response.ok(service.getAllVideoTutorials()).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Response getVideoTutorial(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer id) throws Exception {
		return Response.ok(service.getVideoTutorial(id)).build();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response saveVideoTutorial(HttpServletRequest request, HttpServletResponse response,
			@RequestBody VideoTutorial tutorial) throws Exception {
		return Response.ok(service.addNewVideoTutorial(tutorial)).build();
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response editVideoTutorial(HttpServletRequest request, HttpServletResponse response,
			@RequestBody VideoTutorial tutorial) throws Exception {
		return Response.ok(service.addNewVideoTutorial(tutorial)).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Response deleteVideoTutorial(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Integer id) throws Exception {
		return Response.ok(service.getVideoTutorial(id)).build();
	}

	@RequestMapping(value = "/page-size/{pageSize}/page-no/{pageNo}", method = RequestMethod.GET)
	public Response getTutorialsForPage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNo") Integer pageNo) throws Exception {
		return Response.ok(service.getVideoTutorials(pageSize, pageNo)).build();
	}

}
