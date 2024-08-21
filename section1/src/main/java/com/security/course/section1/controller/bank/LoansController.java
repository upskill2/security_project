package com.security.course.section1.controller.bank;

import com.security.course.section1.model.Cards;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final CustomerService customerService;

    @GetMapping ("/myLoans")
    public ResponseEntity<List<Cards>> getLoans (@RequestParam Long customerId) {
        return customerService.getCards (customerId).isEmpty ()
                ? ResponseEntity.noContent ().build ()
                : ResponseEntity.ok (customerService.getCards (customerId));
    }

}
