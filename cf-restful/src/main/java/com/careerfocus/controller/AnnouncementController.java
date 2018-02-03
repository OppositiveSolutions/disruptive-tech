package com.careerfocus.controller;

import com.careerfocus.entity.Announcements;
import com.careerfocus.service.AnnouncementService;
import com.careerfocus.util.CommonUtils;
import com.careerfocus.util.response.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    AnnouncementService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response saveAnnouncement(HttpServletRequest request, HttpServletResponse response,
                                     @RequestPart(required = true) String announcement,
                                     @RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
        log.debug("announcement: " + announcement.toString());
        log.debug("image: " + image.toString());
        return service.saveAnnouncement(announcement, image);
    }

    @RequestMapping(value = "/{announcementId}/image", method = RequestMethod.PUT)
    public Response saveAnnouncementImage(HttpServletRequest request, HttpServletResponse response,
                                          @RequestPart(required = true) String announcementId,
                                          @RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
        service.editAnnouncementImage(Integer.valueOf(announcementId), image);
        return Response.ok().build();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Response editAnnouncement(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody Announcements announcement) throws Exception {
        return Response.ok(service.editAnnouncement(announcement)).build();
    }

    @RequestMapping(value = "/all", method = RequestMethod.PUT)
    public Response editAnnouncements(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody List<Announcements> announcements) throws Exception {
        return Response.ok(service.editsAnnouncements(announcements)).build();
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getAllAnnouncements(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return Response.ok(service.getAllAnnouncements()).build();
    }

    @RequestMapping(value = "/{announcementId}/image", method = RequestMethod.GET)
    public byte[] getAnnouncementImage(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable("announcementId") int announcementId) throws Exception {
        byte[] data = service.getAnnouncementImage(announcementId);
        String contentType = CommonUtils.getImageTypeFromByteArray(data);
        log.info("ContentType: " + contentType);
        response.setContentType("image/" + contentType);
        response.flushBuffer();
        return data;
    }

}
