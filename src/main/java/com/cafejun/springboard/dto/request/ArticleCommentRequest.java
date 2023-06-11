package com.cafejun.springboard.dto.request;

import com.cafejun.springboard.domain.ArticleComment;
import com.cafejun.springboard.dto.ArticleCommentDto;
import com.cafejun.springboard.dto.UserAccountDto;

public record ArticleCommentRequest(
        Long articleId,
        String content
) {
    public static ArticleCommentRequest of(Long articleId,String content) {
        return new ArticleCommentRequest(articleId, content);
    }
    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(articleId,userAccountDto,content);
    }
}
