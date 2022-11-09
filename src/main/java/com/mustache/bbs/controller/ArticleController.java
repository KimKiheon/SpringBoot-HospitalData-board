package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDTO;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String createPage(){
        return "articles/new";
    }
    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if(optArticle.isEmpty())return "articles/error";
        //Optinal.get() -> Article
        model.addAttribute("article",optArticle.get());
        return "articles/show";
    }
    @PostMapping("")//articles
    public String articles(ArticleDTO articleDTO){
        log.info(articleDTO.getTitle());
        log.info(articleDTO.getContent());
        Article savedArticle = articleRepository.save(articleDTO.toEntity());
        log.info("generatedId:{}",savedArticle.getId());
        return String.format("redirect:/articles/%d",savedArticle.getId());
    }
    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles",articles);
        return "articles/list";
    }
    @GetMapping("")
    public String index(){
        return "redirect:/articles/list";
    }
}
