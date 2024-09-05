package com.security.course.section1.repository;

import com.security.course.section1.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccTransactionsRepository extends CrudRepository<AccountTransactions, UUID> {

    List<AccountTransactions> findByCustomerCustomerIdOrderByTransactionDateDesc (long customerId);

}
