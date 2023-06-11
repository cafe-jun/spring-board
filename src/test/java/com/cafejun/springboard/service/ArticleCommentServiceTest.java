package com.cafejun.springboard.service;

import com.cafejun.springboard.domain.Article;
import com.cafejun.springboard.domain.ArticleComment;
import com.cafejun.springboard.domain.type.SearchType;
import com.cafejun.springboard.dto.ArticleCommentDto;
import com.cafejun.springboard.dto.ArticleDto;
import com.cafejun.springboard.repository.ArticleCommentRepository;
import com.cafejun.springboard.repository.ArticleRepository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    //sut 테스트 대상
    @InjectMocks
    private ArticleCommentService sut;
    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;
    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {

        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title", "content", "#java")));

        //when
        List<ArticleComment> articleComments =  sut.searchArticleComment(1L);
        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {

        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title", "content", "#java")));

        //when
        List<ArticleComment> articleComments =  sut.searchArticleComment(1L);
        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

}