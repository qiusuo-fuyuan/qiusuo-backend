package com.qiusuo.qaservice.graphql.mutation;

import com.qiusuo.qaservice.domain.model.Question;
import com.qiusuo.qaservice.domain.service.QuestionService;
import com.qiusuo.qaservice.graphql.mutation.input.QuestionInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import qiusuo.platform.exception.QiuSuoException;
import qiusuo.platform.utils.string.QiuSuoStringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class QuestionMutation implements GraphQLMutationResolver {

    private QuestionService questionService;

    public QuestionMutation(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * add question
     *
     * @param questionInput
     * @return
     */
    public Question addQuestion(QuestionInput questionInput) {
        Question question = new Question();
        question.setId(UUID.randomUUID().toString());
        question.setCommunityId(questionInput.getCommunityId());
        question.setUserId(questionInput.getUserId());
        question.setTitle(questionInput.getTitle());
        question.setContent(questionInput.getContent());
        question.setTags(questionInput.getTags());
        question.setViewCount(0);
        question.setCreationTime(LocalDateTime.now());
        return questionService.addQuestion(question);
    }

    /**
     * update question
     *
     * @param id
     * @param title
     * @param content
     * @return
     */
    public Question updateQuestion(String id, String title, String content) throws QiuSuoException {
        Question oldQuestion = questionService.getQuestionById(id);
        if (oldQuestion == null) {
            throw new QiuSuoException(QiuSuoStringUtils.message("question with questionId={} not found", id));
        }
        oldQuestion.setTitle(title);
        oldQuestion.setContent(content);
        return questionService.updateQuestion(oldQuestion);
    }

    /**
     * Update question reward amount
     *
     * @param id
     * @param price
     * @return
     */
    public Question updateQuestionAmountOfMoney(String id, Float price) throws QiuSuoException {
        Question oldQuestion = questionService.getQuestionById(id);
        if (oldQuestion == null) {
            throw new QiuSuoException(QiuSuoStringUtils.message("question with questionId={} not found", id));
        }
        oldQuestion.setPrice(price);
        return questionService.updateQuestion(oldQuestion);
    }

    /**
     * delete Question by id
     *
     * @param id
     * @return
     */
    public Question deleteQuestion(String id) throws QiuSuoException {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            throw new QiuSuoException(QiuSuoStringUtils.message("question with questionId={} not found", id));
        }
        questionService.deleteQuestion(id);
        return question;
    }

    /**
     * update viewCount by questionId
     *
     * @param id
     * @return
     */
    public Question updateViewCount(String id) throws QiuSuoException {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            throw new QiuSuoException(QiuSuoStringUtils.message("question with questionId={} not found", id));
        }
        questionService.updateViewCount(question);
        return question;
    }

}
