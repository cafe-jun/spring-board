package com.cafejun.springboard.repository;

import com.cafejun.springboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}