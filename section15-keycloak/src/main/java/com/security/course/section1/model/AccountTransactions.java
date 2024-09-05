package com.security.course.section1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AccountTransactions {

    @Id
    @GeneratedValue (generator = "UUID")
    @Column (name = "transaction_id", columnDefinition = "char(36)")
    @JdbcTypeCode (SqlTypes.CHAR)
    private UUID transactionId;
    @ManyToOne
    @JoinColumn (name = "account_number", nullable = false)
    @JsonIgnore
    private Accounts accounts;
    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
    @Column (name = "transaction_dt")
    private LocalDateTime transactionDate;
    private String transactionSummary;
    private String transactionType;
    @Column (name = "transaction_amt")
    private double transactionAmount;
    private double closingBalance;
    @Column (name = "create_dt")
    @CreationTimestamp
    @JsonFormat (pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

}
