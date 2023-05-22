package com.cafejun.springboard.domain;

import java.time.LocalDate;

public class ArticleComment {
    private Long id;
    private Article article;
    private String content;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate modifiedAt;
    private String modifiedBy;
}
