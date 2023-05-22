package com.cafejun.springboard.domain;

import java.time.LocalDate;

public class Article {
    private Long id;
    private String title;
    private String content;

    private LocalDate createdAt;
    private String createdBy;
    private LocalDate modifiedAt;
    private String modifiedBy;

}
