package com.security.course.section1.repository;

import com.security.course.section1.model.NoticeDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<NoticeDetails, Integer> {

    @Query (value = "select o from NoticeDetails o where CURDATE() between o.noticeBeginDate and o.noticeEndDate")
    List<NoticeDetails> findAllActiveNotices();
}
