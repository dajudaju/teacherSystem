package com.zr.teacherSystem.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zr.teacherSystem.dao.IUserDao;
import com.zr.teacherSystem.pojo.User;
import com.zr.teacherSystem.utils.TxDBUtils;

/**
 * 用户的dao层接口实现类
 * 
 * @author Administrator
 *
 */
public class UserDao implements IUserDao {

	@Override
	public User userlogin(User user) {

		String sql = "select * from user where name=? and password=? and usertype=?";
		User u = null;

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {
			u = runner.query(sql, new BeanHandler<User>(User.class), user.getName(), user.getPassword(),
					user.getUsertype());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public boolean changePwd(String newPassword, Integer usertype, String name) {

		String sql = "update user set password=? where name=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, newPassword, name);
			if (line == 1) {
				if (usertype == 2) {
					// 学生用户
					String sql2 = "update student set password=? where loginname=?";
					int line2 = runner.update(sql2, newPassword, name);
					if (line2 == 1) {
						return true;
					}
				} else if (usertype == 3) {
					// 老师用户
					String sql2 = "update teacher set password=? where loginname=?";
					int line2 = runner.update(sql2, newPassword, name);
					if (line2 == 1) {
						return true;
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
