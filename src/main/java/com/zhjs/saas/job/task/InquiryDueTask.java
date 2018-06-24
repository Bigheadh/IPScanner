package com.zhjs.saas.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.zhjs.saas.core.exception.BaseException;
import com.zhjs.saas.job.service.InquiryService;
import com.zhjs.saas.scheduler.annotation.JobTask;
import com.zhjs.saas.scheduler.job.AbstractJob;
/**
 * @Author: Yuan Han
 * @Description:
 * @Date:  9:29 2018/6/15
 * @Version: v1.0.0
 */
@JobTask(name="InquiryDueTask", cron="0 0/1 * * * ? ")
public class InquiryDueTask extends AbstractJob {

    private InquiryService inquiryService;

    @Override
    public <T> T doExecute(ShardingContext shardingContext) throws BaseException {
        String jobName = shardingContext.getJobName();
        int shardingCount = shardingContext.getShardingTotalCount();
        int shardingItem = shardingContext.getShardingItem();
        logger.info("------Thread ID: {}, {}任务总片数: {}, 当前分片项: {}",
                Thread.currentThread().getId(), jobName, shardingCount, shardingItem);

        inquiryService.stopInquiryToFailBidding();
        return null;
    }
}
