package com.qiusuo.qaservice.domain.service;

import com.qiusuo.qaservice.domain.model.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);

    Question updateQuestion(Question question);

    void deleteQuestion(String id);

    Question getQuestionById(String id);

    List<Question> getQuestionsForCommunity(String communityId);

    void updateViewCount(Question question);
}
