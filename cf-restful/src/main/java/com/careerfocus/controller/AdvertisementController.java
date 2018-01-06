package com.careerfocus.controller;

import com.careerfocus.entity.Announcements;
import com.careerfocus.service.AdvertisementService;
import com.careerfocus.service.AnnouncementService;
import com.careerfocus.util.response.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    AdvertisementService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response saveSlider(HttpServletRequest request, HttpServletResponse response,
                                     @RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
        log.debug("image: " + image.toString());
        return service.saveSliderImage(image);
    }

    @RequestMapping(value = "/{sliderId}/image", method = RequestMethod.PUT)
    public Response updateSliderImage(HttpServletRequest request, HttpServletResponse response,
                                          @RequestPart(required = true) String sliderId,
                                          @RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
        service.editSliderImage(Integer.valueOf(sliderId), image);
        return Response.ok().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getAllSlides(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return Response.ok(service.getAllSlides()).build();
    }

}
