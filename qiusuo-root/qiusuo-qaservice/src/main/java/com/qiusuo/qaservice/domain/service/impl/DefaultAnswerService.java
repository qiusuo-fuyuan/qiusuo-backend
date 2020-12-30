package com.qiusuo.qaservice.domain.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.qiusuo.qaservice.domain.model.Answer;
import com.qiusuo.qaservice.domain.model.Question;
import com.qiusuo.qaservice.domain.service.AnswerService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultAnswerService implements AnswerService {
    private MongoTemplate mongoTemplate;

    public DefaultAnswerService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Answer addAnswer(String questionId, String userId, String content) {
        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserId(userId);
        answer.setContent(content);

        //TODO
        answer.setAdopted(false);
        answer.setCreationTime(LocalDateTime.now());

        Query query = Query.query(Criteria.where("id").is(answer.getQuestionId()));
        answer.setId(UUID.randomUUID().toString());
        Update update = new Update();
        // addToSet: If the data already exists, nothing is done, and push inserts the same data
        // update.push("Students", student);
        update.addToSet("answers", answer);
        mongoTemplate.upsert(query, update, Question.class);
        return answer;
    }

    @Override
    public void deleteAnswer(String questionId, String answerId) {
        Query query = Query.query(Criteria.where("id").is(questionId)
                .and("answers._id").is(answerId));
        Update update = new Update();
        //TODO: What does this mean?
        update.unset("answers" + ".$");
        UpdateResult result = mongoTemplate.updateFirst(query, update, Question.class, "question");
        System.out.println(result);
    }

    @Override
    public List<Answer> getAnswersForQuestion(String questionId) {
        return null;
    }

    @Override
    public Answer getAnswer(String questionId, String answerId) {
        Criteria findQuestionCriteria = Criteria.where("id").is(questionId);
        Criteria findAnswerCriteria = Criteria.where("answers").elemMatch(Criteria.where("_id").is(answerId));

        BasicQuery basicQuery = new BasicQuery(findQuestionCriteria.getCriteriaObject(), findAnswerCriteria.getCriteriaObject());

        Question question = mongoTemplate.findOne(basicQuery, Question.class);
        if (question != null) {
            List<Answer> answerList = question.getAnswers();
            if (!CollectionUtils.isEmpty(answerList)) {
                return answerList.stream()
                        .filter(answer -> answer.getId().equals(answerId))
                        .findFirst().orElse(null);
            }
        }
        return null;
    }

    @Override
    public void acceptAnswer(Answer answer) {
        Query query = Query.query(Criteria.where("id").is(answer.getQuestionId())
                .and("answers._id").is(answer.getId()));

        Update update = new Update();
        update.set("answers.$.adopted", true);
        mongoTemplate.updateFirst(query, update, Question.class);
    }

    @Override
    public void updateAnswer(Answer answer) {
        Query query = Query.query(Criteria.where("id").is(answer.getQuestionId())
                .and("answers._id").is(answer.getId()));

        Update update = new Update();
        update.set("answers.$.content", answer.getContent());
        mongoTemplate.updateFirst(query, update, Question.class);
    }
}
