package com.security.course.section1.controller.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping ("/myLoans")
    public String getLoans () {
        return "Loans";
    }

}
