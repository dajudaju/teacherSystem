package com.zr.teacherSystem.service;

import com.zr.teacherSystem.pojo.User;

/**
 * 用户服务接口
 * 
 * @author Administrator
 *
 */
public interface IUserService {
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return 一个user对象,为null表示登录失败,密码错误
	 */
	User userlogin(User user);

	/**
	 * 修改密码
	 * 
	 * @param newPassword
	 * @param usertype
	 * @param name
	 * @return
	 */
	boolean changePwd(String newPassword, Integer usertype, String name);

}
