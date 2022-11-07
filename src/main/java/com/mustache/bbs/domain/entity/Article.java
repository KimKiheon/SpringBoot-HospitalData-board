package com.mustache.bbs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {
    @Id // Entity 선언하려면 @Id 반드시 선언해주어야함 Primary Key 의미
    @GeneratedValue // = auto increased, id를 직접 생성하지 않고 자동으로 생성하는 경우
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }
    public Article() {

    }
}
