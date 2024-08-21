package com.security.course.section1.service;

import com.security.course.section1.model.*;
import com.security.course.section1.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;
    private final AccTransactionsRepository accTransactionsRepository;
    private final CardsRepository cardsRepository;
    private final NoticeRepository noticeRepository;
    private final ContactRepository contactRepository;

    public Customer saveCustomer (Customer customer) {
        return customerRepository.save (customer);
    }

    public List<Accounts> getAccounts (Long customerId) {
        return accountsRepository.findByCustomerCustomerId (customerId);
    }

    public List<AccountTransactions> getAccountTransactions (Long customerId) {
        return accTransactionsRepository.findByCustomerCustomerIdOrderByTransactionDateDesc (customerId);
    }

    public List<Cards> getCards (final Long customerId) {
        return cardsRepository.findByCustomerCustomerId (customerId);
    }

    public List<NoticeDetails> getNotices () {
        return noticeRepository.findAllActiveNotices ();
    }

    public ContactMessages saveContact (final ContactMessages contact) {
        return contactRepository.save (contact);
    }

    public Optional<Customer> findByEmail (final String name) {
        return customerRepository.findByEmail (name);
    }
}
