package com.security.course.section1.controller.bank;

import com.security.course.section1.model.ContactMessages;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final CustomerService customerService;

    @RequestMapping (value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public ContactMessages getContactDetails (@RequestBody ContactMessages contact) {
        return customerService.saveContact (contact);
    }

}
