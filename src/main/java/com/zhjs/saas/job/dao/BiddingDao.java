package com.zhjs.saas.job.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author:		Jackie Wang 
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
