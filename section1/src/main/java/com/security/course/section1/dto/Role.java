package com.security.course.section1.dto;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("admin"),
    READ ("read");

    private String role;

    Role(String role) {
        this.role = role;
    }

}
