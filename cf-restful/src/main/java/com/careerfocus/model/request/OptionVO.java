package com.careerfocus.model.request;

import java.io.Serializable;

public class OptionVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3045693289755395623L;

    private int optionNo;

    private String option;

    public int getOptionNo() {
        return this.optionNo;
    }

    public void setOptionNo(int optionNo) {
        this.optionNo = optionNo;
    }


    public String getOption() {
        return this.option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
