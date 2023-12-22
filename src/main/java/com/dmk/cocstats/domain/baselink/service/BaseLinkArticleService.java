package com.dmk.cocstats.domain.baselink.service;

import com.dmk.cocstats.domain.baselink.controller.dto.WriteForm;
import com.dmk.cocstats.domain.baselink.model.BaseLinkArticle;
import com.dmk.cocstats.domain.baselink.repository.BaseLinkArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseLinkArticleService {

    private final BaseLinkArticleRepository baseLinkArticleRepository;

    public Page<BaseLinkArticle> baseLinkArticles(Pageable pageable) {
        return baseLinkArticleRepository.findAll(pageable);
    }

    public void saveArticle(WriteForm writeForm) {
        BaseLinkArticle baseLinkArticle = WriteForm.of(writeForm);
        baseLinkArticleRepository.save(baseLinkArticle);
    }
}
