package com.yuanhan.job.service;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
public interface InquiryService
{
	
	public boolean stopQuotation();

	public boolean overQuotation();

	public boolean stopSelection();

	public boolean stopInquiryToFailBidding();

}
