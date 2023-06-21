package com.cafejun.springboard.controller;

import com.cafejun.springboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

}
