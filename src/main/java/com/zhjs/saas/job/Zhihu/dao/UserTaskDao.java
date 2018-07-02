package com.zhjs.saas.job.Zhihu.dao;

import com.zhjs.saas.core.annotation.DaoComponent;
import com.zhjs.saas.core.dao.CommonRepository;
import com.zhjs.saas.job.Zhihu.entity.User;
import com.zhjs.saas.job.Zhihu.entity.UserTask;

@DaoComponent
public interface UserTaskDao extends CommonRepository<UserTask,Long> {

    UserTask getFirstByStatus(int status);
}
