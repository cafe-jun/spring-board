package com.cafejun.springboard.service;

import com.cafejun.springboard.repository.ArticleRepository;
import com.mysema.commons.lang.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.mysema.commons.lang.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("비즈니스 로직 - 게시글")
class ArticleCommentServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;


    @DisplayName("게시글을 조회하면 게시글을 반환 한다")
    @Test
    void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        //given
        ArticleDto articles = sut.searchArticle(1L);
        //when
        assertThat(articles).isNotNull();
        //then
    }
}