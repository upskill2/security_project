package com.security.course.section1.utils;

import com.security.course.section1.dto.CustomerRequest;
import com.security.course.section1.dto.CustomerResponse;
import com.security.course.section1.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper (componentModel = "spring")
public abstract class CustomerMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Mapping (target = "pwd", source = "pwd", qualifiedByName = "encodePassword")
    public abstract Customer toCustomer (CustomerRequest customerRequest);

    @Mapping (target = "status", expression = "java(com.security.course.section1.dto.CreateStatus.SUCCESS)")
    public abstract CustomerResponse toCustomerResponse (Customer customer);

    @Named ("encodePassword")
    public String encodePassword (String password) {
        return passwordEncoder.encode (password);
    }
}
