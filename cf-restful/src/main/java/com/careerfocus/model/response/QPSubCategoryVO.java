package com.careerfocus.model.response;

/**
 * Created by sarath on 24/07/17.
 */
public class QPSubCategoryVO {

	private int questionPaperSubCategoryId;

	private String direction;

	private String description;

	private int noOfQuestions;

	private String isImage;

	public QPSubCategoryVO(int questionPaperSubCategoryId, String direction, String description, int noOfQuestions,
			String isImage) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
		this.direction = direction;
		this.description = description;
		this.noOfQuestions = noOfQuestions;
		this.isImage = isImage;
	}

	public int getQuestionPaperSubCategoryId() {
		return questionPaperSubCategoryId;
	}

	public void setQuestionPaperSubCategoryId(int questionPaperSubCategoryId) {
		this.questionPaperSubCategoryId = questionPaperSubCategoryId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public String getIsImage() {
		return isImage;
	}

	public void setIsImage(String isImage) {
		this.isImage = isImage;
	}
}
