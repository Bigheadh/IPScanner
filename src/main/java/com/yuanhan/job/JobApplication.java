package com.yuanhan.job;

import com.yuanhan.yuanhan.core.BootApplication;
import com.yuanhan.yuanhan.core.annotation.AutoBootApplication;

/**
 * 
 * @author:		yuanhan
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
