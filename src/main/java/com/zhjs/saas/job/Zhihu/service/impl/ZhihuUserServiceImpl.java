package com.zhjs.saas.job.Zhihu.service.impl;

import com.zhjs.saas.core.annotation.ServiceComponent;
import com.zhjs.saas.core.logger.Logger;
import com.zhjs.saas.core.logger.LoggerFactory;
import com.zhjs.saas.job.Zhihu.constants.UserTaskStatus;
import com.zhjs.saas.job.Zhihu.crawl.ZhihuCrawlHandle;
import com.zhjs.saas.job.Zhihu.dao.UserDao;
import com.zhjs.saas.job.Zhihu.dao.UserTaskDao;
import com.zhjs.saas.job.Zhihu.entity.User;
import com.zhjs.saas.job.Zhihu.entity.UserTask;
import com.zhjs.saas.job.Zhihu.service.ZhihuUserService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@ServiceComponent
@EnableTransactionManagement
public class ZhihuUserServiceImpl implements ZhihuUserService {
    private Logger log = LoggerFactory.getLogger(getClass());

    private UserDao userDao;

    private UserTaskDao userTaskDao;

    private ZhihuCrawlHandle zhihuCrawlHandle;

    @Override
    public boolean userInfo() {
        //获取需要执行的任务info
        UserTask userTask = userTaskDao.getFirstByStatus(UserTaskStatus.wait);
        //执行任务抓取用户信息
        log.info("开始抓取知乎ID为:{}的信息", userTask.getZhihuId());
        User userInfo = zhihuCrawlHandle.getUserInfo(userTask);
        log.info("完成抓取知乎ID为:{},名称为:{}的信息,并抓取到{}个following,其有{}个follower", userInfo.getZhihuId(),userInfo.getName(),userInfo.getFollowing(),userInfo.getFollower());
        //保存获取的用户信息
        saveUserInfo(userInfo);
        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveUserInfo(User user){
        userDao.saveAndFlush(user);
        userTaskDao.save(user.getFollowingTasks());
    }
}
