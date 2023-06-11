package com.cafejun.springboard.service;

import com.cafejun.springboard.domain.Article;
import com.cafejun.springboard.domain.type.SearchType;
import com.cafejun.springboard.dto.ArticleDto;
import com.cafejun.springboard.dto.ArticleUpdateDto;
import com.cafejun.springboard.repository.ArticleRepository;
import com.cafejun.springboard.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;
    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("게시글을 검색하면 게시글 리스트를 반환 한다")
    @Test
    void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        //given
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());


        //when
        Page<ArticleDto> articles = sut.searchArticles(null,null,pageable);

        //then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);

    }

    @DisplayName("게시글을 조회하면 게시글을 반환 한다")
    @Test
    void givenArticleId_whenSearchingArticleWithComments_thenReturnsArticleWithComments() {
        //given
//        SearchParameters param = SearchParameters.of(type,"search keyword");


        //when
        ArticleDto articles = sut.searchArticles(1L);

        //then
        assertThat(articles).isNotNull();

    }
    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenExtractsHashtagsFromContentAndSavesArticleWithExtractedHashtags() {
        //given

        given(articleRepository.save(any(Article.class))).willReturn(null); // 해당 코드는 크게 의미가 없다 (호출을 하였는가 ? )  실제 DB 테스트 X -> Unit 테스트가 아님
        //when
        sut.saveArticle(ArticleDto.of("title","content","#java","jssin", LocalDateTime.now()));
        //then
        //
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID 와 수정정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenArticleInfo_whenUpdatingArticle_thenExtractsHashtagsFromContentAndSavesArticleWithExtractedHashtags() {
        //given

        given(articleRepository.save(any(Article.class))).willReturn(null); // 해당 코드는 크게 의미가 없다 (호출을 하였는가 ? )  실제 DB 테스트 X -> Unit 테스트가 아님
        //when
        sut.updateArticle(1L,ArticleUpdateDto.of("title","content","#java"));
        //then

        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleInfo_whenDeletingArticle_thenDeleteArticle() {
        //given
        willDoNothing().given(articleRepository).delete(any(Article.class));
        //when
        sut.deleteArticle(1L);
        //then
        then(articleRepository).should().delete(any(Article.class));
    }
}