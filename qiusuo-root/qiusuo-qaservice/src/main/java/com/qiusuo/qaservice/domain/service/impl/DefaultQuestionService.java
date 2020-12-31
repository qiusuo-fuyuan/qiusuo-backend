package com.qiusuo.qaservice.domain.service.impl;

import com.qiusuo.qaservice.domain.model.Question;
import com.qiusuo.qaservice.domain.service.QuestionService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultQuestionService implements QuestionService {

    private MongoTemplate mongoTemplate;

    public DefaultQuestionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Question addQuestion(Question question) {
        // mongoTemplate save
        Question newQuestion = mongoTemplate.save(question);
        return newQuestion;
    }

    @Override
    public Question updateQuestion(Question question) {
        // set update options
        Query query = Query.query(Criteria.where("id").is(question.getId()));

        // set fields and fields value
        Update update = Update.update("title", question.getTitle()).set("content", question.getContent()).set("price", question.getPrice());

        // Update a piece of data that meets the criteria
        mongoTemplate.updateFirst(query, update, Question.class);

        // Update all data that meets the criteria
        // mongoTemplate.update()

        // If yes, update it; if not, insert it
        // mongoTemplate.upsert(query, update, Article.class);
        return question;
    }

    @Override
    public void deleteQuestion(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Question.class);
    }

    @Override
    public Question getQuestionById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        Question question = mongoTemplate.findOne(query, Question.class);
        return question;
    }

    @Override
    public List<Question> getQuestionsForCommunity(String communityId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("communityId").is(communityId);
        query.addCriteria(criteria);
        // page query
        // query.skip((pageNum - 1) * pageSize);
        // query.limit(pageSize);
        query.with(Sort.by(
                // Sort.Order.asc("id"),
                Sort.Order.desc("createTime")
        ));

        List<Question> questionList = mongoTemplate.find(query, Question.class);
        return questionList;
    }

    @Override
    public void updateViewCount(Question question) {
        Query query = Query.query(Criteria.where("id").is(question.getId()));

        Update update = new Update();
        update.set("viewCount", question.getViewCount() + 1);
        mongoTemplate.updateFirst(query, update, Question.class);
    }
}
