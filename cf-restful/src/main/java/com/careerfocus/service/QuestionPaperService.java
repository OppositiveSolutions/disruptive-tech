package com.careerfocus.service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.dao.BundleDAO;
import com.careerfocus.dao.QuestionPaperDAO;
import com.careerfocus.dao.TestDAO;
import com.careerfocus.entity.*;
import com.careerfocus.entity.id.QuestionPaperQuestionId;
import com.careerfocus.model.request.OptionVO;
import com.careerfocus.model.request.QuestionVO;
import com.careerfocus.model.response.QPSubCategoryVO;
import com.careerfocus.model.response.QuestionPaperVO;
import com.careerfocus.repository.*;
import com.careerfocus.util.QuestionPaperUtils;
import com.careerfocus.util.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionPaperService {

	public static final int DEFAUL_QP_BUNDLE = 10;
	public static final int BUNDLE_STATUS_CREATED = 0;
	public static final int BUNDLE_STATUS_ENABLED = 1;
	public static final int BUNDLE_STATUS_DISABLED = 2;
	public static final int BUNDLE_STATUS_DELETED = 3;

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
	BundleDAO bundleDAO;

	@Autowired
	TestDAO testDAO;

	@Autowired
	QuestionsRepository questionsRepository;

	@Autowired
	QuestionOptionsRepository questionsOptionsRepository;

	@Autowired
	QuestionImageRepository questionImageRepository;

	@Autowired
	QuestionPaperQuestionRepository questionPaperQuestionRepository;

	public QuestionPaper addQuestionPaper(QuestionPaper qPaper) {
		Date date = new Date();
		qPaper.setLastModified(date);
		qPaper.setCreatedDate(date);
		qPaper = qPaperRepository.save(qPaper);
		// int bundleQPId = bundleDAO.addQptoBundle(DEFAUL_QP_BUNDLE,
		// qPaper.getQuestionPaperId());
		// testDAO.createTestDefault(bundleQPId, qPaper.isIsDemo());
		return qPaper;
	}

	public QuestionPaper editQuestionPaper(QuestionPaper qPaper) {

		QuestionPaper paper = qPaperRepository.findOne(qPaper.getQuestionPaperId());
		if (paper == null) {
			throw new RuntimeException();
		}

		paper.setCourseName(qPaper.getCourseName());
		paper.setDuration(qPaper.getDuration());
		paper.setExamCode(qPaper.getExamCode());
		paper.setIsDemo(qPaper.isIsDemo());
		paper.setCoachingType(qPaper.getCoachingType());
		paper.setName(qPaper.getName());
		paper.setNoOfOptions(qPaper.getNoOfOptions());
		paper.setNoOfQuestions(qPaper.getNoOfQuestions());
		paper.setLastModified(new Date());
		paper.setCreatedDate(new Date());

		return qPaperRepository.save(paper);
	}

	public Map<String, Object> enableQuestionPaper(int questionPaperId, int isDemo) {
		boolean status = true;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int bundleQPId = bundleDAO.addQptoBundle(DEFAUL_QP_BUNDLE, questionPaperId);
		if (bundleQPId > 0)
			status = testDAO.createTestDefault(bundleQPId, isDemo == 1 ? true : false);
		if (status && qPaperDAO.changeQPStatus(questionPaperId, BUNDLE_STATUS_ENABLED)) {
			returnMap.put("status", true);
			returnMap.put("message", "Successfully Enabled Question Paper.");
		} else {
			returnMap.put("status", false);
			returnMap.put("message", "Failed to Enable Question Paper.");
		}
		return returnMap;
	}

	public Collection<QuestionPaper> getAllQuestionPapersWithFullDetails(String coachingType) {
		List<QuestionPaper> qps = qPaperRepository.findAll();
		List<QuestionPaper> returnList = null;
		int ct = coachingType != null ? Integer.parseInt(coachingType) : 0;
		if (ct > 0) {
			returnList = new ArrayList<QuestionPaper>();
			for (QuestionPaper qp : qps)
				if (qp.getStatus() != BUNDLE_STATUS_DELETED && qp.getCoachingType() == ct)
					returnList.add(qp);
		} else {
			returnList = new ArrayList<QuestionPaper>();
			for (QuestionPaper qp : qps)
				if (qp.getStatus() != BUNDLE_STATUS_DELETED)
					returnList.add(qp);
		}
		return returnList;
	}

	public List<Map<String, Object>> getAllQuestionPapers(int coachingType) {
		return qPaperDAO.getAllQuestionPapers(coachingType);
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

	public boolean deleteQP(int questionPaperId) {
		return qPaperDAO.changeQPStatus(questionPaperId, BUNDLE_STATUS_DELETED);
	}

	public Map<String, Object> changeQPStatus(int questionPaperId, int status) {
		if (status == 1)
			return enableQuestionPaper(questionPaperId, qPaperDAO.getIsDemoQP(questionPaperId));
		else {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			if (qPaperDAO.changeQPStatus(questionPaperId, status)) {
				returnMap.put("status", true);
				returnMap.put("message", "Successfully Disabled Question Paper.");
			} else {
				returnMap.put("status", false);
				returnMap.put("message", "Failed to Disable Question Paper.");
			}
			return returnMap;
		}
	}

	@Transactional
	public List<QuestionPaperCategory> saveQuestionPaperCategories(List<QuestionPaperCategory> categoryList) {
		categoryList = categoryRepository.save(categoryList);
		updateLastModified(categoryList);
		return categoryRepository.save(categoryList);
	}

	@Transactional
	public List<QuestionPaperCategory> editQuestionPaperCategories(List<QuestionPaperCategory> categoryList,
			int questionPaperId) {

		List<QuestionPaperCategory> oldCList = categoryRepository.findByQuestionPaperId(questionPaperId);

		List<QuestionPaperCategory> cListToUpdate = categoryRepository
				.findByQuestionPaperCategoryIdIn(QuestionPaperUtils.getCategoryIds(categoryList));

		List<QuestionPaperCategory> cListToDelete = new ArrayList<>(oldCList);

		cListToDelete = cListToDelete.stream()
				.filter(c1 -> cListToUpdate.stream()
						.noneMatch(c2 -> c1.getQuestionPaperCategoryId() == c2.getQuestionPaperCategoryId()))
				.collect(Collectors.toList());

		QuestionPaperUtils.copyCategoryListByCategoryId(cListToUpdate, categoryList);

		cListToUpdate.forEach(questionPaperCategory -> log.info("duration: " + questionPaperCategory.getDuration()));

		categoryList = categoryRepository.save(cListToUpdate);
		categoryRepository.delete(cListToDelete);

		updateLastModified(categoryList);
		return categoryRepository.save(categoryList);
	}

	public List<QuestionPaperCategory> getQuestionPaperCategories(int questionPaperId) {
		return categoryRepository.findByQuestionPaperId(questionPaperId);
	}

	public List<QPSubCategoryVO> getQuestionPaperSubCategories(int questionPaperSubCategoryId) {
		return subCategoryRepository.findByQuestionPaperCategoryId(questionPaperSubCategoryId);
	}

	@Transactional
	public List<QuestionPaperSubCategory> saveQuestionPaperSubCategory(
			List<QuestionPaperSubCategory> qPaperSubCategoryList) {
		qPaperSubCategoryList = subCategoryRepository.save(qPaperSubCategoryList);
		updateLastModifiedBySubCategorys(qPaperSubCategoryList);
		return subCategoryRepository.save(qPaperSubCategoryList);
	}

	@Transactional
	public List<QuestionPaperSubCategory> editQuestionPaperSubCategory(
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
			qstn.getOptions().forEach(option -> oList
					.add(new QuestionOption(savedQuestion.getQuestionId(), option.getOptionNo(), option.getOption())));
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
	public List<QuestionVO> editQuestion(List<QuestionVO> qList) {

		List<QuestionPaperQuestion> qpQuestionList = new ArrayList<>();
		qList.forEach(questionVO -> {

			QuestionPaperQuestion qpQuestion = questionPaperQuestionRepository.findOne(new QuestionPaperQuestionId(
					questionVO.getQuestionPaperSubCategoryId(), questionVO.getQuestionNo()));

			Question question = qpQuestion.getQuestion();
			question.setQuestionId(questionVO.getQuestionId());
			question.setQuestion(questionVO.getQuestion());
			question.setCorrectOptionNo(question.getCorrectOptionNo());

			Set<QuestionOption> options = question.getOptions();
			Iterator<QuestionOption> it = options.iterator();
			for (int i = 0; i < questionVO.getOptions().size(); i++) {
				if (it.hasNext()) {
					QuestionOption option = it.next();
					OptionVO optionVO = questionVO.getOptions().get(i);

					option.setOption(optionVO.getOption());
					option.setOptionNo(optionVO.getOptionNo());
					option.setQuestionId(questionVO.getQuestionId());
				}
			}
			qpQuestionList.add(qpQuestion);

		});
		questionPaperQuestionRepository.save(qpQuestionList);
		return qList;
	}
	
//	public Response editAchiever(String achieverJson, MultipartFile image) throws IOException {
//		Achievers achiever = new ObjectMapper().readValue(achieverJson, Achievers.class);
//		Achievers existingAchiever = achieverRepository.findOne(achiever.getAchieverId());
//		if (existingAchiever == null) {
//			return Response.status(ErrorCodes.VALIDATION_FAILED).message(ErrorCodes.ACHIEVER_NAME_EMPTY_MSG).build();
//		}
//		if (image != null) {
//			AchieverImage aImage = new AchieverImage(achiever.getAchieverId(), image.getBytes());
//			aiRepository.save(aImage);
//			existingAchiever.setachieverImage(aImage);
//			existingAchiever.setImgFileName(image.getOriginalFilename());
//		}
//		existingAchiever.setContact(achiever.getContact());
//		existingAchiever.setDescription(achiever.getDescription());
//		existingAchiever.setName(achiever.getName());
//		existingAchiever.setYear(achiever.getYear());
//		return Response.ok(achieverRepository.save(existingAchiever)).build();
//	}

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

	// @Async
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

	public List<QuestionPaperQuestion> getQuestions(int subCategoryId) {
		return questionPaperQuestionRepository.findByQuestionPaperSubCategoryId(subCategoryId);
	}

	public byte[] getQuestionImage(int userId) {
		return questionImageRepository.findOne(userId).getImage();
	}

	public String getQuestionImageDescription(int userId) {
		return questionImageRepository.findOne(userId).getDescription();
	}

	public boolean removeQuestionImage(int userId) {
		try {
			questionImageRepository.delete(userId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Response createQuestionImage(int userId, MultipartFile image, String description) throws IOException {
		QuestionImage qImage = new QuestionImage(userId, image.getBytes(), description);
		return Response.ok(questionImageRepository.save(qImage)).build();
	}
}
