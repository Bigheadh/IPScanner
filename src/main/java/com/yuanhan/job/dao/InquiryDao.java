package com.yuanhan.job.dao;

import com.yuanhan.yuanhan.core.annotation.DaoComponent;
import com.yuanhan.yuanhan.core.dao.CommonRepository;
import com.yuanhan.job.entity.Inquiry;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
@DaoComponent
public interface InquiryDao extends CommonRepository<Inquiry, Long>
{
    @Query(value = "select * from inq_inquiry where status = ?1 and deadline <= (now() - interval '3 D')", nativeQuery = true)
    List<Inquiry> getInquiriesByNotValidAndStatus(int status);

    @Query(value = "select * from inq_inquiry where status = ?1 and deadline > (now() - interval '3 D')", nativeQuery = true)
    List<Inquiry> getInquiriesByInValidAndStatus(int status);
}
