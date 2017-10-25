package com.careerfocus.controller;

import com.careerfocus.constants.UIMessages;
import com.careerfocus.entity.VideoTutorial;
import com.careerfocus.service.QuestionPaperService;
import com.careerfocus.service.VideoTutorialService;
import com.careerfocus.util.response.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/video-tutorial")
public class VideoTutorialController {

    private static final Logger log = Logger.getLogger(VideoTutorialController.class);
	
    @Autowired
    VideoTutorialService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getAllVideoTutorials(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	log.debug("Entered VideoTutorialController.getAllVideoTutorials");
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
        service.deleteVideoTutorial(id);
        return Response.ok().message(UIMessages.VIDEO_TUTORIAL_DELETED).build();
    }

    @RequestMapping(value = "/page-size/{pageSize}/page-no/{pageNo}", method = RequestMethod.GET)
    public Response getTutorialsForPage(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("pageSize") Integer pageSize, @PathVariable("pageNo") Integer pageNo) throws Exception {
        return Response.ok(service.getVideoTutorials(pageSize, pageNo)).build();
    }

}
