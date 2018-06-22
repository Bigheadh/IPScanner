package com.yuanhan.job.service.impl;

import com.yuanhan.job.constants.InquiryStatus;
import com.yuanhan.job.constants.User;
import com.yuanhan.job.dao.BiddingDao;
import com.yuanhan.job.dao.InquiryDao;
import com.yuanhan.job.dao.QuotationDao;
import com.yuanhan.job.entity.Inquiry;
import com.yuanhan.job.entity.Quotation;
import com.yuanhan.job.service.InquiryService;
import com.yuanhan.yuanhan.core.annotation.ServiceComponent;
import com.yuanhan.yuanhan.core.service.AbstractService;
import com.yuanhan.yuanhan.core.util.CollectionUtil;
import com.yuanhan.yuanhan.core.util.MessageUtil;
import com.yuanhan.job.constants.QuotationStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
@ServiceComponent
public class InquiryServiceImpl extends AbstractService implements InquiryService
{
	
	private final static String OverdueForQuotation = "inquiry.quote.overdue";

	private final static String InquiryFailureBid = "inquiry.failure.bid";

	private final static String InquiryOutSelectedTime = "inquiry.outselected.time";

	private final static String InquiryInSelectedTimeButNoValidQuot = "inquiry.inselected.time.but.novalid.quot";

	private BiddingDao biddingDao;

	private InquiryDao inquiryDao;

	private QuotationDao quotationDao;


	@Override
	public boolean stopQuotation()
	{
		List<Map<String,Object>> inquiries = biddingDao.listInquiryToDueQuote();
		if(CollectionUtil.isNotEmpty(inquiries))
		{
			logger.info("发现{}张询价单需要处理", inquiries.size());
			inquiries.forEach(inquiry->{
				Inquiry inq = biddingDao.inquiryDao().get(Long.valueOf(String.valueOf(inquiry.get("inquiryId"))));
				//如果已经有供应商报价，则状态转为  2:待定标
				if(Long.valueOf(String.valueOf(inquiry.get("quotation")))>0)
				{
					//判断是否存在有效报价
					long quotValidNum = biddingDao.isInquiryHavaValidQuotation(Long.valueOf(String.valueOf(inquiry.get("inquiryId"))));
					if(quotValidNum > 0) {
						// 询价单到报价截止时间且存在有效的报价
						inq.setStatus(InquiryStatus.ToBeSelected);
						logger.info("询价单【{}】报价截止时间已到，共有【{}】家供应商报价有效，转为【待选定】状态。", inquiry.get("inquiryId"), quotValidNum);
					}else{
						// 询价单到报价截止时间且不存在有效的报价，只有过期的报价
						inq.setStatus(InquiryStatus.Abandoned);
						inq.setRemark(MessageUtil.getMessage(InquiryFailureBid));
						logger.info("询价单【{}】报价截止时间已到，供应商报价均已过期，转为【废标】状态。", inquiry.get("inquiryId"));
					}
				}
				//无人报价，状态转为  3:流标
				else
				{
					// 如果询价单过期并没有报价记为流标
					inq.setStatus(InquiryStatus.Failed);
					inq.setRemark(MessageUtil.getMessage(OverdueForQuotation));
					logger.info("询价单【{}】报价截止时间已到，没有供应商报价，转为【流标】状态。", inquiry.get("inquiryId"));
				}
				inq.setUpdateBy(User.SystemUserId);
				inq.setUpdateTime(new Date());
				biddingDao.inquiryDao().saveAndFlush(inq);
			});
		}
		
		return true;
	}

	/**
	 * @Author: Yuan Han
	 * @Param: 询价单待选定状态时，需要判断是否存在有效的报价单，如果存在则不改变，如果不存在则变为废标（已完成）
	 * @Return: 
	 * @Description:
	 * @Date 14:30 2018/6/21
	 */
	@Override
	public boolean stopSelection()
	{
		List<Inquiry> inquiries = inquiryDao.getInquiriesByInValidAndStatus(InquiryStatus.ToBeSelected);
		if(CollectionUtil.isNotEmpty(inquiries)){
			inquiries.forEach(inquiry->{
				List<Quotation> quotations = quotationDao.getByInquiryIdAndStatus(inquiry.getInquiryId(), QuotationStatus.WaitChoice);
				if(CollectionUtil.isEmpty(quotations) || quotations.size() == 0){
					logger.info("询价单【{}】处于待选定状态，但是没有任何一个有效的供应商报价，转为【废标】状态。", inquiry.getInquiryId());
					inquiry.setStatus(InquiryStatus.Abandoned);
					inquiry.setRemark(MessageUtil.getMessage(InquiryInSelectedTimeButNoValidQuot));
					inquiry.setUpdateBy(User.SystemUserId);
					inquiry.setUpdateTime(new Date());
					biddingDao.inquiryDao().saveAndFlush(inquiry);
				}
			});
		}
		return true;
	}

	@Transactional
	@Override
	public boolean stopInquiryToFailBidding() {
		List<Inquiry> inquiries = inquiryDao.getInquiriesByNotValidAndStatus(InquiryStatus.ToBeSelected);
		if(CollectionUtil.isNotEmpty(inquiries)){
			logger.info("发现{}张已超过时间且待选定的询价单需要处理", inquiries.size());
			inquiries.forEach(inquiry->{
				List<Quotation> quotations = quotationDao.getByInquiryIdAndStatus(inquiry.getInquiryId(), QuotationStatus.AlreadyChoice);
				if(CollectionUtil.isNotEmpty(quotations)){
					logger.error("询价单【{}】待选定截止时间已到，并且已经选定了一个供应商报价，但是询价单状态任然为待选定状态，请检查。", inquiry.getInquiryId());
				}else{
					inquiry.setStatus(InquiryStatus.Abandoned);
					inquiry.setRemark(MessageUtil.getMessage(InquiryOutSelectedTime));
					logger.info("询价单【{}】待选定截止时间已到，没有选定任何一个供应商报价，转为【废标】状态。", inquiry.getInquiryId());
					inquiry.setUpdateBy(User.SystemUserId);
					inquiry.setUpdateTime(new Date());
					biddingDao.inquiryDao().saveAndFlush(inquiry);

					//标记该询价单的报价单为失效状态
					biddingDao.updateQuotationStatusToNoChoice(inquiry.getInquiryId(), User.SystemUserId);
				}
			});
		}
		return true;
	}

	@Override
	public boolean overQuotation() {
		List<Quotation> quotationList = quotationDao.getListByOverDueAndStatus(QuotationStatus.WaitChoice);
		if(CollectionUtil.isNotEmpty(quotationList)){
			logger.info("发现{}张已超过报价有效期时间的报价单需要处理", quotationList.size());
			quotationList.forEach(quotation -> {
				logger.info("报价单【{}】的报价有效时间已到，该报价单没有被选中，转为【失效】状态。", quotation.getQuotationId());
				quotation.setStatus(QuotationStatus.NoChoice);
				quotation.setUpdateBy(User.SystemUserId);
				quotation.setUpdateTime(new Date());
				quotationDao.saveAndFlush(quotation);
			});
		}
		return true;
	}
}
