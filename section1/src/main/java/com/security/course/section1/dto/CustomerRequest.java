package com.security.course.section1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private String name;
    private String email;
    private String pwd;
    private String mobileNumber;
    private Role role;
}
