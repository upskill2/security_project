package com.security.course.section1.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvents {

    @EventListener
    public void onSuccess (AuthenticationSuccessEvent success) {
        log.info ("Logging successful for user: {}", success.getAuthentication ().getCredentials ());
    }

    @EventListener
    public void onFailure (AbstractAuthenticationFailureEvent failure) {
        log.error ("Logging failed for user: {}, due to: {}",
                failure.getAuthentication ().getCredentials (), failure.getException ().getMessage ());
    }


}
