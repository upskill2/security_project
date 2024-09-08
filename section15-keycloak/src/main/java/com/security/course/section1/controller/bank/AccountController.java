package com.security.course.section1.controller.bank;

import com.security.course.section1.model.Accounts;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final CustomerService customerService;

    @GetMapping ("/myAccount")
    public ResponseEntity<List<Accounts>> getAccountDetails (@RequestParam (name = "email") String email) {
        return customerService.getAccountsByEmail (email).isEmpty ()
                ? ResponseEntity.noContent ().build ()
                : ResponseEntity.ok (customerService.getAccountsByEmail (email));
    }

}
