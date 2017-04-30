package com.careerfocus.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.QuestionPaperDAO;
import com.careerfocus.entity.Question;
import com.careerfocus.entity.QuestionOption;
import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperCategory;
import com.careerfocus.entity.QuestionPaperSubCategory;
import com.careerfocus.model.request.QuestionVO;
import com.careerfocus.repository.QuestionOptionsRepository;
import com.careerfocus.repository.QuestionPaperCategoryRepository;
import com.careerfocus.repository.QuestionPaperRepository;
import com.careerfocus.repository.QuestionPaperSubCategoryRepository;
import com.careerfocus.repository.QuestionsRepository;

@Service
public class QuestionPaperService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	QuestionPaperRepository qPaperRepository;

	@Autowired
	QuestionPaperCategoryRepository categoryRepository;

	@Autowired
	QuestionPaperSubCategoryRepository subCategoryRepository;

	@Autowired
	QuestionPaperDAO qPaperDAO;

	@Autowired
	QuestionsRepository questionsRepository;
	
	@Autowired
	QuestionOptionsRepository questionsOptionsRepository;

	public QuestionPaper addQuestionPaper(QuestionPaper qPaper) {
		return qPaperRepository.save(qPaper);
	}

	public Collection<QuestionPaper> getAllQuestionPapersWithFullDetails() {
		return qPaperRepository.findAll();
	}

	public Collection<QuestionPaper> getAllQuestionPapers() {
		return qPaperDAO.getAllQuestionPapers();
	}

	public QuestionPaper getQuestionPaper(int questionPaperId) {
		return qPaperRepository.findOne(questionPaperId);
	}

	public List<QuestionPaperCategory> saveQuestionPaperCategories(List<QuestionPaperCategory> categoryList) {
		return categoryRepository.save(categoryList);
	}

	public List<QuestionPaperCategory> getQuestionPaperCategories(int questionPaperId) {
		return categoryRepository.findByQuestionPaperId(questionPaperId);
	}

	public List<QuestionPaperSubCategory> saveQuestionPaperSubCategory(
			List<QuestionPaperSubCategory> qPaperSubCategoryList) {
		return subCategoryRepository.save(qPaperSubCategoryList);
	}

	@Transactional
	public void saveQuestion(List<QuestionVO> qList) {
		qList.forEach(question -> {
			Question savedQuestion = questionsRepository
					.save(new Question(question.getQuestion(), question.getCorrectOptionNo()));
			
			List<QuestionOption> oList = new ArrayList<QuestionOption>();
			question.getOptions().forEach(option -> {
				oList.add(new QuestionOption(savedQuestion.getQuestionId(), option.getOptionNo(), option.getOption()));
			});
			questionsOptionsRepository.save(oList);
			
			
		});

		
	}

}
