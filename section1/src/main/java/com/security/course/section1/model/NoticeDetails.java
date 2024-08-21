package com.security.course.section1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class NoticeDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int noticeId;
    private String noticeSummary;
    private String noticeDetails;
    @Column (name = "notic_beg_dt")
    private LocalDateTime noticeBeginDate;
    @Column (name = "notic_end_dt")
    private LocalDateTime noticeEndDate;
    @CreationTimestamp
    @Column (name = "create_dt")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column (name = "update_dt")
    private LocalDateTime updatedDate;
}
