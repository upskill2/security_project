package com.security.course.section1.controller.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping ("/myAccount")
    public String getAccountDetails () {
        return "Account";
    }

}
