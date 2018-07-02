package com.zhjs.saas.job.Zhihu.constants;

public abstract class UserTaskStatus {

    /**
     * 待抓取
     */
    public final static Integer wait = 0;

    /**
     * 正在抓取
     */
    public final static Integer handling = 1;

    /**
     * 完成抓取
     */
    public final static Integer over = 2;
}
