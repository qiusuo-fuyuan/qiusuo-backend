package com.qiusuo.communityservice.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Profile {
    @Id
    private String id;
}
