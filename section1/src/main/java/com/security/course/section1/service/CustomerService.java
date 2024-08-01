package com.security.course.section1.service;

import com.security.course.section1.model.Customer;
import com.security.course.section1.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService (final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer (Customer customer) {
        return customerRepository.save (customer);
    }

}
