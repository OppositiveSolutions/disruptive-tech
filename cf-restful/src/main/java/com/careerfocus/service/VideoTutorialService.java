package com.careerfocus.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.careerfocus.entity.VideoTutorial;
import com.careerfocus.repository.VideoTutorialRepository;

@Service
public class VideoTutorialService {

	@Autowired
	VideoTutorialRepository videoTutorialDAO;

	public Collection<VideoTutorial> getAllVideoTutorials() {
		return videoTutorialDAO.findAll();
	}

	public Page<VideoTutorial> getVideoTutorials(int pageSize, int pageNumber) {
		Pageable page = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "videoTutorialId");
		return videoTutorialDAO.findAll(page);
	}

	@Transactional(rollbackOn = Exception.class)
	public VideoTutorial addNewVideoTutorial(VideoTutorial tutorial) throws Exception {
		return videoTutorialDAO.save(tutorial);
	}

	public VideoTutorial getVideoTutorial(int id) {
		return videoTutorialDAO.findOne(id);
	}

	public void deleteVideoTutorial(int id) {
		videoTutorialDAO.delete(id);
	}
	
}
