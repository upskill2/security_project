package com.security.course.section1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.security.course.section1.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;
    private String mobileNumber;

    @CreationTimestamp
    @Column (name = "create_dt")
    private LocalDateTime createdDate;

    private Role role;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Accounts> accounts;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AccountTransactions> accountTransactions;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cards> cards;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loans> loans;
}
