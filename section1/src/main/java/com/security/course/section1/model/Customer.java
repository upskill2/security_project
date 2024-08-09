package com.security.course.section1.model;

import com.security.course.section1.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long customerId;

    private String name;
    private String email;
    private String pwd;
    private String mobileNumber;

    @Column (name = "create_dt")
    private LocalDateTime createdDate;

    private Role role;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Accounts> accounts;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AccountTransactions> accountTransactions;
}
