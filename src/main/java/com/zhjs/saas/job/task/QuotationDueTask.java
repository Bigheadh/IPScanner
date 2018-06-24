package com.zhjs.saas.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.zhjs.saas.core.exception.BaseException;
import com.zhjs.saas.job.service.InquiryService;
import com.zhjs.saas.scheduler.annotation.JobTask;
import com.zhjs.saas.scheduler.job.AbstractJob;

/**
 * 
 * @author:		Jackie Wang 
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
@JobTask(name="QuotationDueTask", cron="0 0/1 * * * ? ")
public class QuotationDueTask extends AbstractJob
{
	
	private InquiryService inquiryService;

	@Override
	public <T> T doExecute(ShardingContext shardingContext) throws BaseException
	{
		String jobName = shardingContext.getJobName();
		int shardingCount = shardingContext.getShardingTotalCount();
		int shardingItem = shardingContext.getShardingItem();
		logger.info("------Thread ID: {}, {}任务总片数: {}, 当前分片项: {}",
	            Thread.currentThread().getId(), jobName, shardingCount, shardingItem);
		
		inquiryService.stopQuotation();
		inquiryService.overQuotation();
		return null;
	}

}
