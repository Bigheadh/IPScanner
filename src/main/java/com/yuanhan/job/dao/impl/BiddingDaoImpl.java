package com.yuanhan.job.dao.impl;

import com.yuanhan.job.dao.InquiryDao;
import com.yuanhan.yuanhan.core.annotation.DaoComponent;
import com.yuanhan.yuanhan.core.dao.AbstractDao;
import com.yuanhan.job.constants.InquiryStatus;
import com.yuanhan.job.constants.QuotationStatus;
import com.yuanhan.job.dao.BiddingDao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
@DaoComponent
public class BiddingDaoImpl extends AbstractDao implements BiddingDao
{
	private InquiryDao inquiryDao;
	
	@Override
	public InquiryDao inquiryDao()
	{
		return inquiryDao;
	}

	@Override
	public List<Map<String,Object>> listInquiryToDueQuote()
	{
		String sql = "select i.inquiry_id inquiryId, count(q.quotation_id) quotation from inq_inquiry i"
						+ " left join quot_quotation q on q.inquiry_id=i.inquiry_id"
						+ " where i.status=? and i.deadline<=(now()-interval '5 S')"
						+ "	and i.publish_time>(now()-interval '10 D')"
						+ " group by i.inquiry_id";

		return this.getJdbcTemplate().queryForList(sql, InquiryStatus.Quotation);
	}

	@Override
	public long isInquiryHavaValidQuotation(long inquiryId)
	{
		String sql = "select count(q.quotation_id) quotation from inq_inquiry i"
				+ " left join quot_quotation q on q.inquiry_id=i.inquiry_id"
				+ " where i.inquiry_id=? "
                + "	and q.quote_date>(now()- q.expire_day*interval '1 day')";

        Map<String, Object> map = this.getJdbcTemplate().queryForMap(sql, inquiryId);
        if(map.get("quotation") != null && Long.valueOf(String.valueOf(map.get("quotation"))) > 0){
            return Long.valueOf(String.valueOf(map.get("quotation")));
        }else{
            return 0;
        }
    }

    @Override
    public void updateQuotationStatusToNoChoice(long inquiryId, long userId) {
        String sql = "update quot_quotation set status = ?, update_by = ?, update_time = now() where inquiry_id = ?";
        this.getJdbcTemplate().update(sql, QuotationStatus.NoChoice, userId, inquiryId);
    }
}
