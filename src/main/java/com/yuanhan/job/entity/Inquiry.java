package com.yuanhan.job.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yuanhan.yuanhan.core.dao.generator.GenerationType;
import com.yuanhan.yuanhan.core.pojo.BaseObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author:		yuanhan
 * @since:		2018-06-14
 * @modified:	2018-06-14
 * @version:	
 */
@Setter
@Getter
@Entity(name = "inq_inquiry")
public class Inquiry extends BaseObject {
	
    private static final long serialVersionUID = 3816461698028239710L;
    @Id
	@GeneratedValue(generator=GenerationType.SnowFlake)
	@GenericGenerator(name=GenerationType.SnowFlake, strategy="com.yuanhan.yuanhan.core.dao.generator.IdGenerator")
    private Long inquiryId;

    private String projectName;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private String address;

    private String contactName;
    
    private String contactTelephone;

    private Integer paymentWay;

    private Integer invoiceReq;

    private Short deliveryDay;

    private Date deadline;

    private Integer inquiryType;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Long buyerId;

    private Integer companyId;

    private Integer status;

    private String inquiryNo;

    private Integer bizCateId;

    private Date publishTime;

    private String systemId;

    private String remark;

    @Transient
    private List<InqItems> items;
    
}