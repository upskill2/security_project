package com.security.course.section1.config;

import com.security.course.section1.model.Customer;
import com.security.course.section1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername (final String username) throws UsernameNotFoundException {
        final Customer customer = customerRepository.findByEmail (username).orElseThrow (() -> new UsernameNotFoundException (username));
        return User.builder ()
                .username (customer.getEmail ())
                .password (customer.getPwd ())
                .authorities (List.of ((new SimpleGrantedAuthority (customer.getRole ().toString ()))))
                .build ();
    }

}
