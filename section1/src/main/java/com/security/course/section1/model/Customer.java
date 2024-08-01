package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String pwd;

    private Role role;
}
