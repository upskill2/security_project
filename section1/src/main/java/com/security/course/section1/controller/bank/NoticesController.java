package com.security.course.section1.controller.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.course.section1.model.NoticeDetails;
import com.security.course.section1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final ObjectMapper objectMapper;
    private final CustomerService customerService;

    @GetMapping ("/notices")
    public ResponseEntity<List<NoticeDetails>> getNotices () throws JsonProcessingException {
        return customerService.getNotices ().isEmpty ()
                ? ResponseEntity.noContent ().build ()
                : ResponseEntity.ok ()
                .cacheControl (CacheControl.maxAge (60, TimeUnit.SECONDS))
                .body (customerService.getNotices ());
    }

}
