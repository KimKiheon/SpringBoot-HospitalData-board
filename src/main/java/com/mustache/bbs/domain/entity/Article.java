package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "article2")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public Article(String title, String contents) {
        this.title = title;
        this.content = contents;
    }
    public static ArticleResponse of(Article article){
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }
}