package com.cafejun.springboard.service;

import com.cafejun.springboard.domain.Article;
import com.cafejun.springboard.domain.UserAccount;
import com.cafejun.springboard.domain.type.SearchType;
import com.cafejun.springboard.dto.ArticleDto;
import com.cafejun.springboard.dto.ArticleUpdateDto;
import com.cafejun.springboard.dto.ArticleWithCommentsDto;
import com.cafejun.springboard.repository.ArticleRepository;
import com.cafejun.springboard.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    //reading
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword,pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword,pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword,pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword,pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag(searchKeyword,pageable).map(ArticleDto::from);

        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }
    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: {}"+ articleId));
    }
    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        articleRepository.save(dto.toEntity(userAccount));
    }

    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
            if(article.getUserAccount().equals(userAccount)) {
                if(dto.title() != null) { article.setTitle(dto.title());}
                if(dto.content() != null) { article.setContent(dto.content());}
                article.setHashtag(dto.hashtag());
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 수정하는데 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void deleteArticle(Long articleId,String userId) {
        articleRepository.deleteByIdAndUserAccount_UserId(articleId,userId);
    }

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticlesViaHashtag(String hashtag,Pageable pageable) {
        if(hashtag == null || hashtag.isBlank()) {
            return Page.empty(pageable);
        }
        return articleRepository.findByHashtag(hashtag,pageable).map(ArticleDto::from);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }
    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
    }
}
