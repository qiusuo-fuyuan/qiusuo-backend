package com.qiusuo.qa.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalTime;
import java.util.Collection;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(generator = "question_id_generator")
    @SequenceGenerator(name = "question_id_generator", sequenceName = "question_seq")
    private Long id;


    private String username;
    private String subject;
    private String body;

    private LocalTime createdTs;
    private LocalTime updatedTs;

    private Collection<Answer> answers;
}
