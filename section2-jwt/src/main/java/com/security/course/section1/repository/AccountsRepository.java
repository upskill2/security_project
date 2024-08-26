package com.security.course.section1.repository;

import com.security.course.section1.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    List<Accounts> findByCustomerCustomerId (Long customerId);
}
