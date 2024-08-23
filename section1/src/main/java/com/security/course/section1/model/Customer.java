package com.security.course.section1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.security.course.section1.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraph (name = "loans-entity-graph", attributeNodes = {
        @NamedAttributeNode ("customerId"),
        @NamedAttributeNode ("name"),
        @NamedAttributeNode ("email"),
        @NamedAttributeNode ("mobileNumber"),
        @NamedAttributeNode ("createdDate"),
        @NamedAttributeNode ("role")})
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long customerId;

    private String name;
    @Column (unique = true)
    private String email;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;
    private String mobileNumber;

    @CreationTimestamp
    @Column (name = "create_dt")
    @JsonFormat (pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    private Role role;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Accounts> accounts;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AccountTransactions> accountTransactions;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cards> cards;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "customer")
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Loans> loans;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<Authorities> authorities;
}
