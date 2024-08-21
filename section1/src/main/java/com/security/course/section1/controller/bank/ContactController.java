package com.security.course.section1.controller.bank;

import com.security.course.section1.model.ContactMessages;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final CustomerService customerService;

    @GetMapping ("/contacts")
    public ContactMessages getContactDetails (@RequestBody ContactMessages contact) {
        return customerService.saveContact(contact);
    }

}
