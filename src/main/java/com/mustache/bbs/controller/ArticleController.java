package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {
    @GetMapping("")
    public String home() {
        return "index";
    }
    @GetMapping(value="/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/posts")
    public String createArticle(ArticleDTO form){
        log.info(form.toString());
        return "redirect";
    }
}