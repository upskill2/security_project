package com.security.course.section1.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.SequencedCollection;

@Slf4j
@Component
public class AuthorizartionEvents {

    @EventListener
    public <T> void onFailure ( AuthorizationDeniedEvent<T> success) {
        log.info ("Logging successful for user: {}", success.getAuthentication ().get ().getCredentials ());
        SequencedCollection<T> sequencedCollection = new LinkedHashSet<> ();
    }
}
