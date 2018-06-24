package com.zhjs.saas.job.entity;

import com.zhjs.saas.core.pojo.BaseObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "quot_quotation")
public class Quotation extends BaseObject {
    private static final long serialVersionUID = -1634970733870595172L;

    @Id
    @GeneratedValue(generator= com.zhjs.saas.core.dao.generator.GenerationType.SnowFlake)
    @GenericGenerator(name= com.zhjs.saas.core.dao.generator.GenerationType.SnowFlake, strategy="com.zhjs.saas.core.dao.generator.IdGenerator")
    private Long quotationId;

    private Integer paymentWay;

    private Short expireDay;

    private BigDecimal totalAmount;

    private Integer status;

    private Long inquiryId;

    private Long supplierId;

    private Integer companyId;

    private Date quoteDate;

    private String quotationNo;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String contactName;

    private String contactTelephone;

    private Integer bizCateId;

    private String address;

    private String systemId;

}

