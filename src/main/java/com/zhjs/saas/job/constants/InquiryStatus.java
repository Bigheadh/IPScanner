package com.zhjs.saas.job.constants;

public abstract class InquiryStatus {
    /**
     * 草稿状态 0 (Draft)
     */
    public static final int Draft = 0;

    /**
     * 报价中状态 1 (Quotation)
     */
    public static final int Quotation = 1;

    /**
     * 待定标状态 2 (ToBeSelected)
     */
    public static final int ToBeSelected = 2;

    /**
     * 流标 3 (Failed)
     */
    public static final int Failed = 3;

    /**
     * 废标 4 (Abandoned)
     */
    public static final int Abandoned = 4;

    /**
     * 完成 5 (Done)
     */
    public static final int Done = 5;

    /**
     * 审核中 6 (Inspecting)
     */
    public static final int Inspecting = 6;

    /**
     * 拒绝 7 (Refused)
     */
    public static final int Refused = 7;

    /**
     * 通过 8 (Passed)
     */
    public static final int Passed = 8;
}
