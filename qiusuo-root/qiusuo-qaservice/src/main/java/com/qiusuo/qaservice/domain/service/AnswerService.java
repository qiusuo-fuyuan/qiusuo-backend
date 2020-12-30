package com.qiusuo.qaservice.domain.service;

import com.qiusuo.qaservice.domain.model.Answer;

import java.util.List;

public interface AnswerService {

    Answer addAnswer(String questionId, String userId, String content);

    void deleteAnswer(String questionId, String answerId);

    void acceptAnswer(Answer answer);

    void updateAnswer(Answer answer);

    List<Answer> getAnswersForQuestion(String questionId);

    /**
     * get answer by questionId and answerId.
     *
     * @param questionId
     * @param answerId
     * @return
     */
    Answer getAnswer(String questionId, String answerId);
}
