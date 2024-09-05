package com.security.course.section1.repository;

import com.security.course.section1.model.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends CrudRepository<ContactMessages, UUID> {
}
