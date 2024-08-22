package com.security.course.section1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @CreationTimestamp
    @JsonFormat (pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;


}
