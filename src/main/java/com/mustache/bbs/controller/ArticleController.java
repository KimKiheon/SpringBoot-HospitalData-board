package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDTO;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createPage() {
        return "articles/new";
    }
    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }
    @GetMapping("/{id}")
    public String selectArticle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty()) return "articles/error";
        //Optinal.get() -> Article
        model.addAttribute("article", optArticle.get());
        return "articles/show";
    }
    @PostMapping("/posts")
    public String createArticle(ArticleDTO form) {
        log.info(form.toString());      // 로그 남기기
        Article article = form.toEntity();

        articleRepository.save(article);
        return "redirect:/articles/"+article.getId();
    }


    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            model.addAttribute("message", String.format("%d가 없습니다 ", id));
            return "articles/error";
        }
        model.addAttribute("article", optionalArticle.get());
        return "articles/edit";
    }


    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDTO articleDTO, Model model) {
        Article article = articleRepository.save(articleDTO.toEntity());
        System.out.println("id="+id+"savedArticle.getId()="+article.getId());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }
}
