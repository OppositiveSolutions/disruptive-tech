package com.careerfocus.controller;

import com.careerfocus.constants.UIMessages;
import com.careerfocus.service.AdvertisementService;
import com.careerfocus.util.response.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	AdvertisementService service;

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public Response saveSliderImage(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		log.debug("image: " + image.toString());
		return service.saveSliderImage(image);
	}

	@RequestMapping(value = "/{sliderImageId}/image", method = RequestMethod.PUT)
	public Response updateSliderImage(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(required = true) String sliderId,
			@RequestPart(value = "file", required = true) final MultipartFile image) throws Exception {
		service.editSliderImage(Integer.valueOf(sliderId), image);
		return Response.ok().build();
	}

	@RequestMapping(value = "/image/all", method = RequestMethod.GET)
	public Response getAllSliderImages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return Response.ok(service.getAllSliderImages()).build();
	}

	@RequestMapping(value = "{sliderImageId}/image", method = RequestMethod.GET)
	public byte[] getSliderImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) int sliderImageId) throws Exception {
		return service.getSliderImage(sliderImageId);
	}
	
//	 @RequestMapping(value = "/iscurrent/{sliderImageId}/image", method = RequestMethod.PUT, consumes = { "text/plain" })
//		public Response updateSliderImageIsCurrent(HttpServletRequest request, HttpServletResponse response,
//				@PathVariable("sliderImageId") int sliderImageId) throws Exception {
//		 service.updateSliderImageIsCurrent(sliderImageId);
//			return Response.ok().build();
//		}
	
	@RequestMapping(value = "image/{id}", method = RequestMethod.DELETE)
    public Response deleteSliderImage(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("id") Integer id) throws Exception {
        service.deleteSliderImage(id);
        return Response.ok().message(UIMessages.SLIDER_IMAGE_DELETED).build();
    }

	@RequestMapping(value = "/announcement", method = RequestMethod.POST)
	public Response saveSliderAnnouncement(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(value = "file", required = true) String announcement) throws Exception {
		log.debug("image: " + announcement.toString());
		return service.saveSliderAnnouncement(announcement);
	}

	@RequestMapping(value = "/{sliderAnnouncementId}/announcement", method = RequestMethod.PUT)
	public Response updateSliderAnnouncement(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(required = true) String sliderId, @RequestPart(required = true) String announcement)
			throws Exception {
		service.editSliderAnnouncement(Integer.valueOf(sliderId), announcement);
		return Response.ok().build();
	}

	@RequestMapping(value = "/announcement/all", method = RequestMethod.GET)
	public Response getAllSliderAnnouncements(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return Response.ok(service.getAllSliderAnnouncements()).build();
	}

	@RequestMapping(value = "/{sliderAnnouncementId}/announcement", method = RequestMethod.GET)
	public String getSliderAnnouncement(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(required = true) int sliderAnnouncementId) throws Exception {
		return service.getSliderAnnouncement(sliderAnnouncementId);
	}
	
//	 @RequestMapping(value = "/iscurrent/{sliderAnnouncementId}/announcement", method = RequestMethod.PUT, consumes = { "text/plain" })
//		public Response updateSliderAnnouncementIsCurrent(HttpServletRequest request, HttpServletResponse response,
//				@PathVariable("sliderAnnouncementId") int sliderAnnouncementId) throws Exception {
//		 service.updateSliderAnnouncementIsCurrent(sliderAnnouncementId);
//			return Response.ok().build();
//		}
	
	@RequestMapping(value = "announcement/{id}", method = RequestMethod.DELETE)
    public Response deleteSliderAnnouncement(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("id") Integer id) throws Exception {
        service.deleteSliderAnnouncement(id);
        return Response.ok().message(UIMessages.SLIDER_ANNOUNCEMENT_DELETED).build();
    }

}
