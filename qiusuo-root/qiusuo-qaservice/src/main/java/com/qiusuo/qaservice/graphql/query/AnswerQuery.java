package com.qiusuo.qaservice.graphql.query;

import com.qiusuo.qaservice.domain.model.Answer;
import com.qiusuo.qaservice.domain.service.AnswerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AnswerQuery implements GraphQLQueryResolver {

    private AnswerService answerService;

    public AnswerQuery(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * get all answers of a question
     *
     * @param questionId
     * @return
     */
    public Collection<Answer> answersOfQuestion(String questionId) {
        List<Answer> answerList = answerService.getAnswersForQuestion(questionId);
        return answerList;
    }

    /**
     * get answer by questionId and answerId
     *
     * @param questionId
     * @param id         answerId
     * @return
     */
    public Answer answer(String questionId, String id) {
        return answerService.getAnswer(questionId, id);
    }
}