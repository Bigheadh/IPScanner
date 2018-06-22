package com.yuanhan.job.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-12
 * @modified:	2018-06-12
 * @version:	
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix="yuanhan.scheduler.elastic-job")
public class ElasticJobConfig
{	 
    private String zkNodes;
 
    private String namespace;
 
    @Bean
    public ZookeeperConfiguration zkConfig() {
        return new ZookeeperConfiguration(zkNodes, namespace);
    }
 
    @Bean(initMethod="init")
    public ZookeeperRegistryCenter registryCenter(ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }

    @Resource
    private DataSource dataSource;

    @Bean
    public JobEventConfiguration jobEventConfiguration() {
        return new JobEventRdbConfiguration(dataSource);
    }
    
    
}
