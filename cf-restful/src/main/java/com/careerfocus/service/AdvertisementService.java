package com.careerfocus.service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.AnnouncementImage;
import com.careerfocus.entity.Announcements;
import com.careerfocus.repository.AnnouncementImageRepository;
import com.careerfocus.repository.AnnouncementRepository;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    AnnouncementImageRepository aiRepository;

    @Transactional
    public Response saveAnnouncement(String announcementJson, MultipartFile image)
            throws IOException {
        Announcements announcement = new ObjectMapper().readValue(announcementJson, Announcements.class);
        announcement.setImgFileName(image.getOriginalFilename());
        List<Error> errors = validateAnnouncement(announcement);
        if (errors != null && !errors.isEmpty()) {
            return Response.status(ErrorCodes.VALIDATION_FAILED)
                    .error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), errors).build();
        }

        announcement = announcementRepository.save(announcement);

        AnnouncementImage aImage = new AnnouncementImage(announcement.getAnnouncementId(), image.getBytes());
        aiRepository.save(aImage);

        if (announcement.isIsCurrent()) {
            updateCurrentAnnouncementsOrder(announcement);
        }
        return Response.ok(announcement).build();
    }

    public Announcements editAnnouncement(Announcements announcement) {
        announcement = announcementRepository.save(announcement);
        if (announcement.isIsCurrent()) {
            updateCurrentAnnouncementsOrder(announcement);
        }
        return announcement;
    }

    public List<Announcements> editsAnnouncements(List<Announcements> announcements) {
        return announcementRepository.save(announcements);
    }

    public void editAnnouncementImage(int announcementId, MultipartFile image) throws IOException {
        AnnouncementImage aImage = new AnnouncementImage(announcementId, image.getBytes());
        aiRepository.save(aImage);
    }

    public List<Announcements> getAllAnnouncements() {
        return announcementRepository.findAllByOrderByIsCurrentDescOrderAsc();
    }

    public List<Announcements> getCurrentAnnouncements() {
        return announcementRepository.findByIsCurrentOrderByOrderAsc(true);
    }

    public List<Announcements> getOldAnnouncements() {
        return announcementRepository.findByIsCurrentOrderByOrderAsc(false);
    }

    public byte[] getAnnouncementImage(int announcementId) {
        return aiRepository.findOne(announcementId).getImage();
    }

    private List<Error> validateAnnouncement(Announcements announcement) {
        List<Error> subErrors = new ArrayList<Error>();
        if (announcement.getName() == null || announcement.getName().isEmpty()) {
            subErrors.add(new Error(ErrorCodes.ANNOUNCEMENT_NAME_EMPTY, ErrorCodes.ANNOUNCEMENT_NAME_EMPTY_MSG));
        }
        return subErrors;
    }

    private void updateCurrentAnnouncementsOrder(Announcements announcement) {
        List<Announcements> aList = announcementRepository.findByIsCurrentAndOrderGreaterThan(true,
                announcement.getOrder() - 1);
        aList.forEach(ancment -> {
            log.debug("Announcement Name:" + announcement.getName());
            if (ancment.getAnnouncementId() != announcement.getAnnouncementId())
                ancment.setOrder(ancment.getOrder() + 1);
        });
        announcementRepository.save(aList);
    }

}
