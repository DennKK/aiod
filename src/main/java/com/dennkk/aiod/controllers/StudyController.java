package com.dennkk.aiod.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {

    @GetMapping("/ai/study")
    public String getStudyPage() {
        return "study";
    }
}