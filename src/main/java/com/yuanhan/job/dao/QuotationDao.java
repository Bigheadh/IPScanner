package com.yuanhan.job.dao;

import com.yuanhan.yuanhan.core.annotation.DaoComponent;
import com.yuanhan.yuanhan.core.dao.CommonRepository;
import com.yuanhan.job.entity.Quotation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: Yuan Han
 * @Param:
 * @Return: 
 * @Description:
 * @Date 11:53 2018/6/15
 */
@DaoComponent
public interface QuotationDao extends CommonRepository<Quotation, Long> {

    List<Quotation> getByInquiryIdAndStatus(long inquiryId, int status);

    @Query(value = "select * from quot_quotation  where quote_date <= ( now( ) - expire_day * INTERVAL '1 day' ) AND STATUS = ?1", nativeQuery = true)
    List<Quotation> getListByOverDueAndStatus(int status);
}
