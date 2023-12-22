package com.dmk.cocstats.domain.baselink.controller;

import com.dmk.cocstats.domain.baselink.controller.dto.WriteForm;
import com.dmk.cocstats.domain.baselink.model.BaseLinkArticle;
import com.dmk.cocstats.domain.baselink.service.BaseLinkArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bases")
@RequiredArgsConstructor
public class BaseLinkArticleController {

    private final BaseLinkArticleService baseLinkArticleService;

    @GetMapping
    public String baseLinkArticles(@PageableDefault(size = 9) Pageable pageable, Model model) {
        Page<BaseLinkArticle> baseLinkArticles = baseLinkArticleService.baseLinkArticles(pageable);
        model.addAttribute("baseLinkArticles", baseLinkArticles);
        model.addAttribute("totalPages", baseLinkArticles.getTotalPages());
        return "baselink/index";
    }

    @GetMapping("/json")
    @ResponseBody
    public Page<BaseLinkArticle> json(Pageable pageable) {
        return baseLinkArticleService.baseLinkArticles(pageable);
    }

    @GetMapping("/write")
    public String writeForm(@ModelAttribute WriteForm writeForm) {
        return "baselink/writeForm";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute WriteForm writeForm) {
        baseLinkArticleService.saveArticle(writeForm);
        return "redirect:/bases";
    }
}
