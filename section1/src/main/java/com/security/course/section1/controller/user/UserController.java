package com.security.course.section1.controller.user;

import com.security.course.section1.dto.CreateStatus;
import com.security.course.section1.dto.CustomerRequest;
import com.security.course.section1.dto.CustomerResponse;
import com.security.course.section1.model.Customer;
import com.security.course.section1.service.CustomerService;
import com.security.course.section1.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerService service;
    private final CustomerMapper mapper;

    @PostMapping (value = "/registerUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerResponse> registerUser (@RequestBody CustomerRequest customerRequest) {
        Customer customer = null;
        try {
            customer = service.saveCustomer (toCustomer (customerRequest));
        } catch (Exception e) {
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR)
                    .body (
                            CustomerResponse
                                    .builder ()
                                    .email (customerRequest.getEmail ())
                                    .status (CreateStatus.FAILED)
                                    .build ()
                    );
        }
        return ResponseEntity.ok (mapper.toCustomerResponse (customer));
    }

    private Customer toCustomer (CustomerRequest customerRequest) {
        return mapper.toCustomer (customerRequest);
    }

    @RequestMapping ("/user")
    public Customer getUserDetailsAfterLogin (Authentication authentication) {
        return service.findByEmail (authentication.getName ()).orElse (null);
    }

}
