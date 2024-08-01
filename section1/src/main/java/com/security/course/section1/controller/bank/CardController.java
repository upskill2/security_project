package com.security.course.section1.controller.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {


    @GetMapping ("/myCards")
    public String getCardDetails () {
        return "Card";
    }

}
