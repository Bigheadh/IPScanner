package com.zhjs.saas.job.dao;

import com.zhjs.saas.core.annotation.DaoComponent;
import com.zhjs.saas.core.dao.CommonRepository;
import com.zhjs.saas.job.entity.Inquiry;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 
 * @author:		Jackie Wang 
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
