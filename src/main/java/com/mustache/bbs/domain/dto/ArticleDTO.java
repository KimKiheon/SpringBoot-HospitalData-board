package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;


    public Article toEntity() {
        return new Article(this.title, this.content);
    }
}
