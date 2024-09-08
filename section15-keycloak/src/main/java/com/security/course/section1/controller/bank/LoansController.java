package com.security.course.section1.controller.bank;

import com.security.course.section1.model.Cards;
import com.security.course.section1.model.Loans;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final CustomerService customerService;

    @GetMapping ("/myLoans")
    @PostAuthorize ("hasRole('ADMIN')")
    public ResponseEntity<List<Loans>> getLoans (@RequestParam (name = "email") String email) {
        Long customerId = customerService.findCustomerByEmail (email).getCustomerId ();
        final ResponseEntity<List<Loans>> listResponseEntity = customerService.getLoans (customerId).isEmpty ()
                ? ResponseEntity.noContent ().build ()
                : ResponseEntity.ok (customerService.getLoans (customerId));
        return listResponseEntity;
    }

}
