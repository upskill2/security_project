package com.security.course.section1.controller.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping ("/contacts")
    public ResponseEntity<String> getContactDetails () {
        return ResponseEntity.ok ("Contact");
    }

}
