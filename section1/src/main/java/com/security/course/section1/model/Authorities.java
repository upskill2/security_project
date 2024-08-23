package com.security.course.section1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.security.course.section1.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authorities {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
    @Column (name = "name")
    @Enumerated (EnumType.STRING)
    private Role role;

}
