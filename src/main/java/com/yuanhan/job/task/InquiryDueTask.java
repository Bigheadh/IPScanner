package com.yuanhan.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.yuanhan.yuanhan.core.exception.BaseException;
import com.yuanhan.job.service.InquiryService;
import com.yuanhan.yuanhan.scheduler.annotation.JobTask;
import com.yuanhan.yuanhan.scheduler.job.AbstractJob;
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
