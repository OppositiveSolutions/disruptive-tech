package com.careerfocus.model.response;

import com.careerfocus.model.request.OptionVO;
import java.util.ArrayList;

public class QuestionPopulateVO {

    private int qId;

    private String content;

    private int qNo;

    private ArrayList<OptionVO> options;

    private int selectedOptionNo;

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public ArrayList<OptionVO> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<OptionVO> options) {
		this.options = options;
	}

	public int getSelectedOptionNo() {
		return selectedOptionNo;
	}

	public void setSelectedOptionNo(int selectedOptionNo) {
		this.selectedOptionNo = selectedOptionNo;
	}


}
