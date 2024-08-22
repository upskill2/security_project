package com.security.course.section1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ContactMessages {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID contactId;
    private String contactName;
    private String contactEmail;
    private String subject;
    private String message;
    @Column (name = "create_dt")
    @CreationTimestamp
    @JsonFormat (pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
}
