package com.cafejun.springboard.service;

import com.cafejun.springboard.domain.ArticleComment;
import com.cafejun.springboard.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleComment> searchArticleComment(long l) {
        return List.of();
    }
}
