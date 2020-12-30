package com.qiusuo.qaservice.graphql.mutation;

import com.qiusuo.qaservice.domain.model.Answer;
import com.qiusuo.qaservice.domain.service.AnswerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import qiusuo.platform.exception.QiuSuoException;
import qiusuo.platform.utils.string.QiuSuoStringUtils;

@Component
public class AnswerMutation implements GraphQLMutationResolver {
    private AnswerService answerService;

    public AnswerMutation(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * add answer
     *
     * @param questionId
     * @param userId
     * @param content
     * @return
     */
    public Answer addAnswer(String questionId, String userId, String content) {
        return answerService.addAnswer(questionId, userId, content);
    }

    /**
     * update answer
     *
     * @param questionId
     * @param answerId
     * @param content
     * @return
     */
    public Answer updateAnswer(String questionId, String answerId, String content) {
        Answer answer = answerService.getAnswer(questionId, answerId);
        if (answer == null) {
            throw new RuntimeException(String.format("answer with answerId=%s not found", answerId));
        }
        answer.setContent(content);
        answerService.updateAnswer(answer);
        return answer;
    }

    /**
     * deleteAnswer
     *
     * @param questionId
     * @param id
     * @return
     */
    public Answer deleteAnswer(String questionId, String id) {
        Answer answer = answerService.getAnswer(questionId, id);
        if (answer == null) {
            throw new RuntimeException(String.format("answer with answerId=%s not found", id));
        }
        answerService.deleteAnswer(questionId, id);
        return new Answer();
    }

    /**
     * set the answer is acceptAnswer
     *
     * @param questionId
     * @param answerId
     * @return
     */
    public Answer acceptAnswer(String questionId, String answerId) throws QiuSuoException {
        Answer answer = answerService.getAnswer(questionId, answerId);
        if (answer == null) {
            throw new QiuSuoException(QiuSuoStringUtils.message("answer with answerId=%s not found", answerId));
        }
        answer.setAdopted(true);
        answerService.acceptAnswer(answer);
        return answer;
    }

}
