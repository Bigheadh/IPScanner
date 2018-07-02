package com.zhjs.saas.job.Zhihu.crawl;

import com.zhjs.saas.core.annotation.ServiceComponent;
import com.zhjs.saas.core.logger.Logger;
import com.zhjs.saas.core.logger.LoggerFactory;
import com.zhjs.saas.job.Zhihu.entity.User;
import com.zhjs.saas.job.Zhihu.entity.UserTask;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@ServiceComponent
public class ZhihuCrawlHandle implements PageProcessor {
    private Logger log = LoggerFactory.getLogger(getClass());

    private Site site = Site.me();

    private User user;

    public ZhihuCrawlHandle(User user){
        this.user = user;
    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }

    public User getUserInfo(UserTask userTask){
        User user = new User();
        ZhihuCrawlHandle zhihuCrawlHandle = new ZhihuCrawlHandle(user);
        Spider spider = Spider.create(zhihuCrawlHandle);

        spider.run();
        return user;
    }
}
