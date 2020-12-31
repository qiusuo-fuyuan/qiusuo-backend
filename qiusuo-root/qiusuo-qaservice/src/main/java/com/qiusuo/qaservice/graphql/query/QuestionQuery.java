package com.qiusuo.qaservice.graphql.query;

import com.qiusuo.qaservice.domain.model.Question;
import com.qiusuo.qaservice.domain.service.QuestionService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class QuestionQuery implements GraphQLQueryResolver {

    private QuestionService questionService;

    public QuestionQuery(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Collection<Question> questionsOfCommunity(String communityId) {
        return questionService.getQuestionsForCommunity(communityId);
    }

    public Question question(String questionId) {
        return questionService.getQuestionById(questionId);
    }

}
