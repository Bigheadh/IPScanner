package com.yuanhan.job.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
public interface BiddingDao
{
	public InquiryDao inquiryDao();
	

	public List<Map<String,Object>> listInquiryToDueQuote();

	public long isInquiryHavaValidQuotation(long inquiryId);

	public void updateQuotationStatusToNoChoice(long inquiryId, long userId);
}
