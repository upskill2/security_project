package com.security.course.section1.repository;

import com.security.course.section1.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Integer> {

    List<Cards> findByCustomerCustomerId (Long customerId);
}
