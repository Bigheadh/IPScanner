
安装zookeeper服务端，当前阶段只采用单点zookeeper

## 1、安装

#### ** windows简易安装方式 **
    
    https://www.cnblogs.com/xzzq/p/7240554.html

#### ** Linux简易安装方式，注意安装到我们规划的  /app/runtime/ **

    
    https://blog.csdn.net/vtopqx/article/details/79207053


## 2、Job开发
自定义作业Job时，需要继承 AbstractJob

而关于作业cron和切片等可以通过Annotation @JobTask 配置，代码如下：

```java

@JobTask(name="SelectionDueTask", cron="0 0/1 * * * ? ")
public class SelectionDueTask extends AbstractJob
{
	public <T> T doExecute(ShardingContext shardingContext) throws BaseException
	{
		String jobName = shardingContext.getJobName();
		int shardingCount = shardingContext.getShardingTotalCount();
		int shardingItem = shardingContext.getShardingItem();
		logger.info("------Thread ID: {}, {}任务总片数: {}, 当前分片项: {}",
	            Thread.currentThread().getId(), jobName, shardingCount, shardingItem);
		//作业代码
		// inquiryService.stopSelection();
		return null;
	}

}

```


