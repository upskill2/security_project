package com.security.course.section14.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

    @GetMapping ("/secure")
    public String securePage (Authentication authentication) {
        return "secure.html";
    }

}
