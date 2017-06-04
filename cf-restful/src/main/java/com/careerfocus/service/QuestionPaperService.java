package com.careerfocus.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.QuestionPaperDAO;
import com.careerfocus.entity.Question;
import com.careerfocus.entity.QuestionOption;
import com.careerfocus.entity.QuestionPaper;
import com.careerfocus.entity.QuestionPaperCategory;
import com.careerfocus.entity.QuestionPaperQuestion;
import com.careerfocus.entity.QuestionPaperSubCategory;
import com.careerfocus.model.request.QuestionVO;
import com.careerfocus.model.response.QuestionPaperVO;
import com.careerfocus.repository.QuestionOptionsRepository;
import com.careerfocus.repository.QuestionPaperCategoryRepository;
import com.careerfocus.repository.QuestionPaperQuestionRepository;
import com.careerfocus.repository.QuestionPaperRepository;
import com.careerfocus.repository.QuestionPaperSubCategoryRepository;
import com.careerfocus.repository.QuestionsRepository;

@Service
public class QuestionPaperService {

	private static final Logger log = Logger.getLogger(QuestionPaperService.class);

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

	public Page<QuestionPaperVO> getAllQuestionPapers(int pageSize, int pageNo) {
		Pageable page = new PageRequest(pageNo - 1, pageSize);
		return qPaperRepository.findAllQuestionsPapers(page);
	}

	public Page<QuestionPaperVO> searchQuestionPaper(String key, int pageSize, int pageNo) {
		Pageable page = new PageRequest(pageNo - 1, pageSize);
		return qPaperRepository.searchQuestionsPaper("%" + key + "%", page);
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

		Set<Integer> subCatIds = new HashSet<>();
		qList.forEach(qstn -> {

			Question question = new Question(qstn.getQuestion(), qstn.getCorrectOptionNo());
			if (qstn.getQuestionId() != 0)
				question.setQuestionId(qstn.getQuestionId());
			Question savedQuestion = questionsRepository.save(question);

			List<QuestionOption> oList = new ArrayList<>();
			qstn.getOptions().forEach(option ->
					oList.add(new QuestionOption(savedQuestion.getQuestionId(),
							option.getOptionNo(), option.getOption())));
			questionsOptionsRepository.save(oList);

			questionPaperQuestionRepository.save(new QuestionPaperQuestion(qstn.getQuestionPaperSubCategoryId(),
					qstn.getQuestionNo(), savedQuestion));

			qstn.setQuestionId(savedQuestion.getQuestionId());
			subCatIds.add(qstn.getQuestionPaperSubCategoryId());
		});
		qPaperDAO.updateLastModidiedByQuestionPaperSubCategoryIds(new ArrayList<>(subCatIds));
		return qList;
	}

	@Transactional
	public void updateIsDemo(int questionPaperId, boolean isDemo) {
		qPaperRepository.updateIsDemo(isDemo, questionPaperId);
	}

	private void updateLastModified(List<QuestionPaperCategory> categoryList) {
		Set<Integer> qIds = new HashSet<>();
		categoryList.forEach(category -> {
			int questionPaperId = category.getQuestionPaperId();
			qIds.add(questionPaperId);
		});
		qPaperDAO.updateLastModified(new ArrayList<>(qIds));
	}

	private void updateLastModifiedBySubCategorys(List<QuestionPaperSubCategory> subCategoryList) {
		Set<Integer> qIds = new HashSet<>();
		subCategoryList.forEach(category -> {
			int id = category.getQuestionPaperSubCategoryId();
			qIds.add(id);
		});
		qPaperDAO.updateLastModidiedByQuestionPaperSubCategoryIds(new ArrayList<>(qIds));
	}

//	@Async
	public boolean isQuestionPaperComplete(int questionPaperId) {
		QuestionPaper qPaper = qPaperRepository.findOne(questionPaperId);
		Set<QuestionPaperCategory> categories = qPaper.getQuestionPaperCategorys();

		int categoryQuestionsCount = 0;
		int questionsCount = 0;
		for (QuestionPaperCategory category : categories) {
			categoryQuestionsCount += category.getNoOfQuestions();

			Set<QuestionPaperSubCategory> subCategories = category.getQuestionPaperSubCategorys();
			if (category.getNoOfSubCategory() != subCategories.size()) {
				log.info("CHECKPOINT1");
				return false;
			}

			int subCategoryQuestionsCount = 0;
			for (QuestionPaperSubCategory subCategory : subCategories) {
				subCategoryQuestionsCount += subCategory.getNoOfQuestions();

				Set<QuestionPaperQuestion> questions = subCategory.getQuestions();
				if (subCategory.getNoOfQuestions() != questions.size()) {
					log.info("CHECKPOINT2");
					return false;
				}

				for (QuestionPaperQuestion qpQuestion : questions) {
					questionsCount++;
					Question question = qpQuestion.getQuestion();

					Set<QuestionOption> options = question.getOptions();
					if (options.size() != qPaper.getNoOfOptions()) {
						log.info("CHECKPOINT3");
						return false;
					}
				}
			}
			if (category.getNoOfQuestions() != subCategoryQuestionsCount) {
				log.info("CHECKPOINT4");
				return false;
			}
		}

		if (qPaper.getNoOfQuestions() != categoryQuestionsCount || questionsCount != qPaper.getNoOfQuestions()) {
			log.info("CHECKPOINT5");
			return false;
		}

		return true;
	}

}
