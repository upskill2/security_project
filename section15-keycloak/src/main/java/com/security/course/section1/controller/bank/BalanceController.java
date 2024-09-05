package com.security.course.section1.controller.bank;

import com.security.course.section1.model.AccountTransactions;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final CustomerService customerService;

    @GetMapping ("/myBalance")
    public ResponseEntity<List<AccountTransactions>> getBalance (@RequestParam(name = "id") Long customerId) {
        return customerService.getAccountTransactions (customerId).isEmpty ()
                ? ResponseEntity.noContent ().build ()
                : ResponseEntity.ok (customerService.getAccountTransactions (customerId));
    }

}
