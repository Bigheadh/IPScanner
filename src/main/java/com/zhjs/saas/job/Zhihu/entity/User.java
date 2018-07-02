package com.zhjs.saas.job.Zhihu.entity;

import com.zhjs.saas.core.pojo.BaseObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity(name = "user")
public class User extends BaseObject {

    private static final long serialVersionUID = -1634970733870595172L;

    @Id
    @GeneratedValue(generator= com.zhjs.saas.core.dao.generator.GenerationType.SnowFlake)
    @GenericGenerator(name= com.zhjs.saas.core.dao.generator.GenerationType.SnowFlake, strategy="com.zhjs.saas.core.dao.generator.IdGenerator")
    private Long userId;

    private String name;

    private String zhihuId;

    private String address;
    //行业
    private String industry;
    //职业经历
    private String professional;

    private String sex;
    //教育
    private String education;

    private String interduce;

    private int following;

    private int follower;

    @Transient
    private List<UserTask> followingTasks;
}
