package com.security.course.section1.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private long id;
    private String email;
    private CreateStatus status;

}
