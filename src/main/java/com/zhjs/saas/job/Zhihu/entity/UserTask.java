package com.zhjs.saas.job.Zhihu.entity;

import com.zhjs.saas.core.pojo.BaseObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "user_task")
public class UserTask extends BaseObject {

    private static final long serialVersionUID = -1634970733870595175L;

    @Id
    @GeneratedValue(generator= com.zhjs.saas.core.dao.generator.GenerationType.SnowFlake)
    @GenericGenerator(name= com.zhjs.saas.core.dao.generator.GenerationType.SnowFlake, strategy="com.zhjs.saas.core.dao.generator.IdGenerator")
    private Long taskId;

    private String zhihuId;

    //0,未抓取,1正在抓取,2抓取完成;
    private int status;
}
