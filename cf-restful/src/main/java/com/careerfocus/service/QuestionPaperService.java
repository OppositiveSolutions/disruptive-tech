package com.careerfocus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.repository.QuestionPaperRepository;

@Service
public class QuestionPaperService {
	
	@Autowired
	QuestionPaperRepository qPaperRepository;
	
	public QuestionPaper addQuestionPaper(QuestionPaper qPaper) {
		return qPaperRepository.save(qPaper);
	}

}
