package com.zhjs.saas.job;

import com.zhjs.saas.core.BootApplication;
import com.zhjs.saas.core.annotation.AutoBootApplication;

/**
 * 
 * @author:		Jackie Wang 
 * @since:		2018-06-11
 * @modified:	2018-06-11
 * @version:	
 */
//@EnableScheduler
@AutoBootApplication
public class JobApplication
{

	public static void main(String[] args)
	{
		BootApplication.run(JobApplication.class, args);
	}

}
