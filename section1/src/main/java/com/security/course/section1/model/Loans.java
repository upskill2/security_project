package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Loans {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int loanNumber;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false)
    private Customer customer;
    @Column (name = "start_dt")
    private LocalDateTime startDate;
    private String loanType;
    private double totalLoan;
    private double amountPaid;
    private double outstandingAmount;
    @CreationTimestamp
    @Column (name = "create_dt")
    private LocalDateTime createdDate;

}
