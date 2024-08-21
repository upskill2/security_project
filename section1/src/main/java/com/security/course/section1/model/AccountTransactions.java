package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AccountTransactions {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID transactionId;
    @ManyToOne
    @JoinColumn (name = "account_number", nullable = false)
    private Accounts accounts;
    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false)
    private Customer customer;
    @Column (name = "transaction_dt")
    private LocalDateTime transactionDate;
    private String transactionSummary;
    private String transactionType;
    @Column (name = "transaction_amt")
    private double transactionAmount;
    private double closingBalance;
    @Column (name = "create_dt")
    private LocalDateTime createdDate;

}
