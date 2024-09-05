package com.security.course.section1.controller.bank;

import com.security.course.section1.model.ContactMessages;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final CustomerService customerService;

    @RequestMapping (value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    @PreFilter ("filterObject.contactName != 'test'")
    @PostFilter ("filterObject.message != 'message'")
    public List<ContactMessages> getContactDetails (@RequestBody List<ContactMessages> contacts) {
        ContactMessages contact = contacts.getFirst ();
        final ContactMessages e1 = customerService.saveContact (contact);
        List<ContactMessages> list = new ArrayList<> ();
        list.add (e1);
        return list;
    }

}
