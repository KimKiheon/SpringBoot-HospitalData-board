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

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty()) return "articles/error";
        //Optinal.get() -> Article
        model.addAttribute("article", optArticle.get());
        return "articles/show";
    }

    /*@PostMapping("")//articles
    public String articles(ArticleDTO articleDTO) {
        Article savedArticle = articleRepository.save(articleDTO.toEntity());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }*/

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

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public String update(@PathVariable Long id, ArticleDTO articleDTO, Model model) {
        Article article = articleRepository.save(articleDTO.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

}
