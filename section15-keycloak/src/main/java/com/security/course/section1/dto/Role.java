package com.security.course.section1.dto;

import lombok.Getter;

@Getter
public enum Role {

    VIEWBALANCE ("VIEWBALANCE"),
    VIEWLOANS ("VIEWLOANS"),
    VIEWCARDS ("VIEWCARDS"),
    VIEWACCOUNT ("VIEWACCOUNT"),
    ADMIN ("ADMIN"),
    USER ("USER"),
    ROLE_USER ("ROLE_USER"),
    ROLE_ADMIN ("ROLE_ADMIN")
    ;

    private String role;

    Role (String role) {
        this.role = role;
    }

}
