package com.security.course.section1.controller.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {


    @GetMapping ("/myBalance")
    public String getBalance () {
        return "Your balance is $1000";
    }


}
