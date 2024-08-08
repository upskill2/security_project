package com.security.course.section1.controller.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final ObjectMapper objectMapper;

    @GetMapping ("/notices")
    public String getNotices () throws JsonProcessingException {
        return objectMapper.writeValueAsString ("Notices");
    }

}
