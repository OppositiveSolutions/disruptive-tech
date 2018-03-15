package com.careerfocus.util;

import com.careerfocus.entity.QuestionPaperCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sarath on 15/06/17.
 */
public class QuestionPaperUtils {

    public static List<Integer> getCategoryIds(List<QuestionPaperCategory> categoryList) {
        List<Integer> cIds = new ArrayList<>();
        for (QuestionPaperCategory category : categoryList) {
            cIds.add(category.getQuestionPaperCategoryId());
        }
        return cIds;
    }

    public static void copyCategoryListByCategoryId(List<QuestionPaperCategory> cList,
                                                    List<QuestionPaperCategory> categoryList) {
        for (QuestionPaperCategory cate : cList) {
            for (QuestionPaperCategory category : categoryList) {
                if (cate.getQuestionPaperCategoryId() == category.getQuestionPaperCategoryId()) {
                    cate.setNoOfQuestions(category.getNoOfQuestions());
                    cate.setNoOfSubCategory(category.getNoOfSubCategory());
                    cate.setCorrectAnswerMark(category.getCorrectAnswerMark());
                    cate.setNegativeMark(category.getNegativeMark());
                    cate.setDuration(category.getDuration());
                }
            }
        }
        cList.addAll(
                categoryList.stream().filter(c1 ->
                        c1.getQuestionPaperCategoryId() == 0).collect(Collectors.toList()
                ));
    }

}
