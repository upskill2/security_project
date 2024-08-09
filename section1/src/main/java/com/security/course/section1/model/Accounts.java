package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Accounts {

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "customer_id")
    private Customer customer;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int accountNumber;
    private String accountType;
    private String branchAddress;
    @Column (name = "create_dt")
    private LocalDateTime createdDate;


}
