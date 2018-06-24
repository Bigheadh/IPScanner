package com.zhjs.saas.job.constants;

public abstract class QuotationStatus {


    /**
     * 待选定状态 1 (WaitChoice)
     */
    public static final int WaitChoice = 1;

    /**
     * 已选中 2(AlreadyChoice)
     */
    public static final int AlreadyChoice = 2;

    /**
     * 未选中(过期失效) 3(NoChoice)
     */
    public static final int NoChoice = 3;
}
