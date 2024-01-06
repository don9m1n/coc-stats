package com.dmk.cocstats.domain.article.controller;

import com.dmk.cocstats.base.security.dto.MemberContext;
import com.dmk.cocstats.domain.article.controller.dto.ArticleRequest;
import com.dmk.cocstats.domain.article.controller.dto.ArticleResponse;
import com.dmk.cocstats.domain.article.model.FormStatus;
import com.dmk.cocstats.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String articles(@PageableDefault(page = 0, size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<ArticleResponse> articles = articleService.articles(pageable);
        List<Integer> paginationBar = new ArrayList<>();
        for (int i = 0; i < articles.getTotalPages(); i++) {
            paginationBar.add(i);
        }

        model.addAttribute("articles", articles);
        model.addAttribute("paginationBar", paginationBar);

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model) {
        ArticleResponse article = articleService.article(articleId);
        model.addAttribute("article", article);
        return "articles/detail";
    }

    @GetMapping("/form")
    public String articleForm(Model model) {
        model.addAttribute("formStatus", FormStatus.CREATE);
        return "articles/form";
    }

    @PostMapping("/form")
    public String writeArticle(
            @AuthenticationPrincipal MemberContext memberContext,
            ArticleRequest articleRequest,
            Model model
    ) {

        ArticleResponse article = articleService.write(memberContext.getId(), articleRequest);
        model.addAttribute("article", article);
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable Long articleId, Model model) {
        ArticleResponse article = articleService.article(articleId);

        model.addAttribute("article", article);
        model.addAttribute("formStatus", FormStatus.UPDATE);

        return "articles/form";
    }

    @PostMapping("/{articleId}/form")
    public String updateArticle(@PathVariable Long articleId, ArticleRequest articleRequest) {
        System.out.println("articleRequest = " + articleRequest.getTitle());
        System.out.println("articleRequest = " + articleRequest.getContent());
        articleService.updateArticle(articleId, articleRequest);
        return "redirect:/articles/" + articleId;
    }
}
