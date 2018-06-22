package com.yuanhan.job.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.yuanhan.yuanhan.core.dao.generator.GenerationType;
import com.yuanhan.yuanhan.core.pojo.BaseObject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "inq_items")
public class InqItems extends BaseObject 
{
    private static final long serialVersionUID = 5428235769029934121L;
    @Id
	@GeneratedValue(generator=GenerationType.SnowFlake)
	@GenericGenerator(name=GenerationType.SnowFlake, strategy="com.yuanhan.yuanhan.core.dao.generator.IdGenerator")
    private Long itemId;

    private Long inquiryId;

    private String productName;

    private String productCode;

    private Integer mainCateId;

    private Integer subCateId;

    private Integer cateId;

    private String specification;

    private String brand;
    
    private Integer[] brandIds;

    private String unit;

    private BigDecimal quantity;

    private String remark;

    private Long fileId;

    private Integer specId;

    private String attributes;

    private Integer productId;
}