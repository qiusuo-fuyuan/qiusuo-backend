package com.qiusuo.qaservice.domain.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Answer {
    @Id
    @GeneratedValue(generator = "answer_id_generator")
    @SequenceGenerator(name = "answer_id_generator", sequenceName = "answer_seq")
    private Long id;


    private String username;
}
