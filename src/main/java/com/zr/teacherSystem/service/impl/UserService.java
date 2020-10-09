package com.zr.teacherSystem.service.impl;

import com.zr.teacherSystem.dao.IUserDao;
import com.zr.teacherSystem.dao.impl.UserDao;
import com.zr.teacherSystem.pojo.User;
import com.zr.teacherSystem.service.IUserService;

/**
 * 用户服务接口实现类
 * 
 * @author Administrator
 *
 */
public class UserService implements IUserService {

	// 获取dao层
	private IUserDao dao = new UserDao();

	@Override
	public User userlogin(User user) {

		return dao.userlogin(user);
	}

	@Override
	public boolean changePwd(String newPassword, Integer usertype, String name) {

		return dao.changePwd(newPassword, usertype, name);
	}

}
