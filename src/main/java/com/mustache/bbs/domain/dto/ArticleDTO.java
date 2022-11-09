package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDTO {
    private String title;
    private String content;

    public ArticleDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(this.title, this.content);
    }
}
