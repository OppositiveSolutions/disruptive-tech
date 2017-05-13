package com.careerfocus.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.QuestionPaperDAO;
import com.careerfocus.entity.Question;
import com.careerfocus.entity.QuestionOption;
import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperCategory;
import com.careerfocus.entity.QuestionPaperQuestion;
import com.careerfocus.entity.QuestionPaperSubCategory;
import com.careerfocus.model.request.QuestionVO;
import com.careerfocus.repository.QuestionOptionsRepository;
import com.careerfocus.repository.QuestionPaperCategoryRepository;
import com.careerfocus.repository.QuestionPaperQuestionRepository;
import com.careerfocus.repository.QuestionPaperRepository;
import com.careerfocus.repository.QuestionPaperSubCategoryRepository;
import com.careerfocus.repository.QuestionsRepository;

@Service
public class QuestionPaperService {

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

	@Autowired
	QuestionPaperQuestionRepository questionPaperQuestionRepository;

	public QuestionPaper addQuestionPaper(QuestionPaper qPaper) {
		qPaper.setLastModified(new Date());
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

	@Transactional
	public List<QuestionPaperCategory> saveQuestionPaperCategories(List<QuestionPaperCategory> categoryList) {
		categoryList = categoryRepository.save(categoryList);
		updateLastModified(categoryList);
		return categoryRepository.save(categoryList);
	}

	public List<QuestionPaperCategory> getQuestionPaperCategories(int questionPaperId) {
		return categoryRepository.findByQuestionPaperId(questionPaperId);
	}

	@Transactional
	public List<QuestionPaperSubCategory> saveQuestionPaperSubCategory(
			List<QuestionPaperSubCategory> qPaperSubCategoryList) {
		qPaperSubCategoryList = subCategoryRepository.save(qPaperSubCategoryList);
		updateLastModifiedBySubCategorys(qPaperSubCategoryList);
		return subCategoryRepository.save(qPaperSubCategoryList);
	}

	@Transactional
	public List<QuestionVO> saveQuestion(List<QuestionVO> qList) {
		
		
		Set<Integer> subCatIds = new HashSet<Integer>();
		qList.forEach(qstn -> {

			Question question = new Question(qstn.getQuestion(), qstn.getCorrectOptionNo());
			if (qstn.getQuestionId() != 0)
				question.setQuestionId(qstn.getQuestionId());
			Question savedQuestion = questionsRepository.save(question);

			List<QuestionOption> oList = new ArrayList<QuestionOption>();
			qstn.getOptions().forEach(option -> {
				oList.add(new QuestionOption(savedQuestion.getQuestionId(), option.getOptionNo(), option.getOption()));
			});
			questionsOptionsRepository.save(oList);

			questionPaperQuestionRepository.save(new QuestionPaperQuestion(qstn.getQuestionPaperSubCategoryId(),
					qstn.getQuestionNo(), savedQuestion));

			qstn.setQuestionId(savedQuestion.getQuestionId());
			subCatIds.add(qstn.getQuestionPaperSubCategoryId());
		});
		qPaperDAO.updateLastModidiedByQuestionPaperSubCategoryIds(new ArrayList<Integer>(subCatIds));
		return qList;
	}
	
	@Transactional
	public void updateIsDemo(int questionPaperId, boolean isDemo) {
		qPaperRepository.updateIsDemo(isDemo, questionPaperId);
	}

	private void updateLastModified(List<QuestionPaperCategory> categoryList) {
		Set<Integer> qIds = new HashSet<Integer>();
		categoryList.forEach(category -> {
			int questionPaperId = category.getQuestionPaperId();
			qIds.add(questionPaperId);
		});
		qPaperDAO.updateLastModified(new ArrayList<Integer>(qIds));
	}
	
	private void updateLastModifiedBySubCategorys(List<QuestionPaperSubCategory> subCategoryList) {
		Set<Integer> qIds = new HashSet<Integer>();
		subCategoryList.forEach(category -> {
			int id = category.getQuestionPaperSubCategoryId();
			qIds.add(id);
		});
		qPaperDAO.updateLastModidiedByQuestionPaperSubCategoryIds(new ArrayList<Integer>(qIds));
	}

}
