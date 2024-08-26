package com.security.course.section1.repository;

import com.security.course.section1.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Integer> {

    List<Loans> findByCustomerCustomerIdOrderByStartDateDesc (Long customerId);
}
