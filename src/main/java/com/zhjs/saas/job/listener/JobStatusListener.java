package com.zhjs.saas.job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.zhjs.saas.core.annotation.BeanComponent;
import com.zhjs.saas.core.logger.Logger;
import com.zhjs.saas.core.logger.LoggerFactory;

/**
 * 
 * @author: 	Jackie Wang
 * @since: 		2018-06-11
 * @modified: 	2018-06-11
 * @version:
 */
@BeanComponent
public class JobStatusListener extends JobExecutionListenerSupport implements ElasticJobListener
{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void afterJob(JobExecution jobExecution)
	{
		switch(jobExecution.getStatus())
		{
			case COMPLETED:				
				logger.info("作业 {}[{}] 成功执行完成！", jobExecution.getJobInstance().getJobName(), jobExecution.getJobId());
				break;
				
			case FAILED:			
				logger.info("作业 {}[{}] 执行失败！", jobExecution.getJobInstance().getJobName(), jobExecution.getJobId());
				break;
				
			default:
				break;				
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution)
	{
		
	}

	@Override
	public void beforeJobExecuted(ShardingContexts shardingContexts)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterJobExecuted(ShardingContexts context)
	{	
		logger.info("作业 {}[{}] 成功执行完成！", context.getJobName(), context.getTaskId());		
	}

}
