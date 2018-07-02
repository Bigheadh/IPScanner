package com.zhjs.saas.job.Zhihu.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.zhjs.saas.core.exception.BaseException;
import com.zhjs.saas.job.Zhihu.service.ZhihuUserService;
import com.zhjs.saas.scheduler.annotation.JobTask;
import com.zhjs.saas.scheduler.job.AbstractJob;

@JobTask(name="InquiryDueTask", cron="0 0/1 * * * ? ")
public class ZhihuUserInfoBeginTask extends AbstractJob {

    private ZhihuUserService zhihuUserService;

    @Override
    public <T> T doExecute(ShardingContext shardingContext) throws BaseException {
        String jobName = shardingContext.getJobName();
        int shardingCount = shardingContext.getShardingTotalCount();
        int shardingItem = shardingContext.getShardingItem();
        logger.info("------Thread ID: {}, {}任务总片数: {}, 当前分片项: {}",
                Thread.currentThread().getId(), jobName, shardingCount, shardingItem);

        zhihuUserService.userInfo();
        return null;
    }
}
