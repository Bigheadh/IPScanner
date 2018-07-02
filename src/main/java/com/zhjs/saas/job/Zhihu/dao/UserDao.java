package com.zhjs.saas.job.Zhihu.dao;

import com.zhjs.saas.core.annotation.DaoComponent;
import com.zhjs.saas.core.dao.CommonRepository;
import com.zhjs.saas.job.Zhihu.entity.User;

@DaoComponent
public interface UserDao extends CommonRepository<User,Long> {
}
