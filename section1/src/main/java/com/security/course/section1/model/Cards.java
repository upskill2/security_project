package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Cards {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int cardId;

    private String cardNumber;
    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false)
    private Customer customer;
    private String cardType;
    private double totalLimit;
    private double amountUsed;
    private double availableAmount;
    @CreationTimestamp
    @Column (name = "create_dt")
    private LocalDateTime createdDate;

}
