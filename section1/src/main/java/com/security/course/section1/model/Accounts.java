package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Accounts {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "account_number")
    private long accountNumber;
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "customer_id")
    private Customer customer;
    @OneToMany
    @PrimaryKeyJoinColumn
    private List<AccountTransactions> accountTransactions;
    private String accountType;
    private String branchAddress;
    @Column (name = "create_dt")
    private LocalDateTime createdDate;


}
