package com.careerfocus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.careerfocus.entity.Achievers;
import com.careerfocus.service.AchieversService;
import com.careerfocus.util.response.Response;

@RestController
@RequestMapping("/achievers")
public class AchieversController {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    AchieversService service;
	
	 @RequestMapping(value = "", method = RequestMethod.POST)
	    public Response saveAchiever(HttpServletRequest request, HttpServletResponse response,
	                                     @RequestPart(required = true) String achiever,
	                                     @RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
	        log.debug("achiever: " + achiever.toString());
	        log.debug("image: " + image.toString());
	        return service.saveAchiever(achiever, image);
	    }

	    @RequestMapping(value = "/{achieverId}/image", method = RequestMethod.PUT)
	    public Response saveAchieverImage(HttpServletRequest request, HttpServletResponse response,
	                                          @RequestPart(required = true) String achieverId,
	                                          @RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
	        service.editAchieverImage(Integer.valueOf(achieverId), image);
	        return Response.ok().build();
	    }

	    @RequestMapping(value = "", method = RequestMethod.PUT)
	    public Response editAchiever(HttpServletRequest request, HttpServletResponse response,
	                                     @RequestBody Achievers achiever) throws Exception {
	        return Response.ok(service.editAchiever(achiever)).build();
	    }
	    
	    @RequestMapping(value = "/all", method = RequestMethod.PUT)
	    public Response editAchievers(HttpServletRequest request, HttpServletResponse response,
	                                      @RequestBody List<Achievers> achievers) throws Exception {
	        return Response.ok(service.editAchievers(achievers)).build();
	    }


	    @RequestMapping(value = "", method = RequestMethod.GET)
	    public Response getAllAchievers(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        return Response.ok(service.getAllAchievers()).build();
	    }

	    @RequestMapping(value = "/{achieverId}/image", method = RequestMethod.GET)
	    public byte[] getAchieverImage(HttpServletRequest request, HttpServletResponse response,
	                                       @PathVariable("achieverId") int achieverId) throws Exception {
	        return service.getAchieverImage(achieverId);
	    }

}
