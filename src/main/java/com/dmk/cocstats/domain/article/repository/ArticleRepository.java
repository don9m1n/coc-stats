package com.dmk.cocstats.domain.article.repository;

import com.dmk.cocstats.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    void deleteByIdAndMember_Id(Long articleId, Long memberId);
}
