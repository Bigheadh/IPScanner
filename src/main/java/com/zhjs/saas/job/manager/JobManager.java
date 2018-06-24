package com.zhjs.saas.job.manager;

import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.zhjs.saas.core.annotation.BeanComponent;
import com.zhjs.saas.core.event.ApplicationBootedEvent;
import com.zhjs.saas.core.logger.Logger;
import com.zhjs.saas.core.logger.LoggerFactory;
import com.zhjs.saas.core.util.AnnotationUtil;
import com.zhjs.saas.core.util.ApplicationContextUtil;
import com.zhjs.saas.scheduler.annotation.JobTask;
import com.zhjs.saas.scheduler.job.AbstractJob;

/**
 * 
 * @author: 	Jackie Wang
 * @since: 		2018-06-12
 * @modified: 	2018-06-12
 * @version:
 */
@BeanComponent
public class JobManager
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ZookeeperRegistryCenter registryCenter;

	@Async
	@EventListener(ApplicationBootedEvent.class)
	public void init()
	{
		Map<String,AbstractJob> tasks = ApplicationContextUtil.getApplicationContext().getBeansOfType(AbstractJob.class);
		tasks.values().forEach(task -> {
			JobTask annotation = AnnotationUtil.findAnnotation(task.getClass(), JobTask.class);
			if(annotation!=null)
			{
				JobCoreConfiguration conf = JobCoreConfiguration.newBuilder(annotation.name(), annotation.cron(), annotation.shardingTotalCount()).build();
				SimpleJobConfiguration jobConf = new SimpleJobConfiguration(conf, task.getClass().getName());

				SpringJobScheduler jobScheduler = new SpringJobScheduler(task, registryCenter, LiteJobConfiguration.newBuilder(jobConf).overwrite(true).build());
				try {
		            jobScheduler.init();
		            logger.info("定时任务{}创建成功！", annotation.name());
		        } catch (Exception e) {
		            logger.error("定时任务"+annotation.name()+"创建失败！", e);	        
		        }
			}			
		});
		/*
		Class<QuotationDueTask> task = QuotationDueTask.class;
		String jobName = task.getSimpleName();
		String cron = "0 0/3 * * * ? ";
		int shardingCount = 1;
		JobCoreConfiguration conf = JobCoreConfiguration.newBuilder(jobName, cron, shardingCount).build();
		SimpleJobConfiguration jobConf = new SimpleJobConfiguration(conf, task.getCanonicalName());

		SpringJobScheduler jobScheduler = new SpringJobScheduler(ApplicationContextUtil.getBeanOfType(task),
								registryCenter, LiteJobConfiguration.newBuilder(jobConf).overwrite(true).build());
		try {
            jobScheduler.init();
            logger.info("定时任务{}创建成功！", jobName);
        } catch (Exception e) {
            logger.error("定时任务"+jobName+"创建失败！", e);
        }*/
	}

}
