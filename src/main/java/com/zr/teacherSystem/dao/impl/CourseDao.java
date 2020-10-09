package com.zr.teacherSystem.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zr.teacherSystem.dao.ICourseDao;
import com.zr.teacherSystem.pojo.Course;
import com.zr.teacherSystem.utils.TxDBUtils;

/**
 * 课程dao层接口实现类
 * 
 * @author Administrator
 *
 */
public class CourseDao implements ICourseDao {

	@Override
	public boolean deleteCouByIds(int[] ids) {

		String sql = "delete from course where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			for (int i = 0; i < ids.length; i++) {
				int line = runner.update(sql, ids[i]);
				if (line != 1) {
					System.out.println("删除id为 " + ids[i] + "的课程信息失败!");
					return false;
				}
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateCou(Course course) {

		String sql = "update course set name=?,type=? where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, course.getName(), course.getType(), course.getId());
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Course getCourseById(Integer id) {

		Course course = null;
		String sql = "select * from course where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			course = runner.query(sql, new BeanHandler<Course>(Course.class), id);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return course;
	}

	@Override
	public boolean addCou(Course course) {

		String sql = "insert into course values(null,?,?)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, course.getName(), course.getType());
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Course> getAllCou() {

		List<Course> list = null;
		String sql = "select * from course";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			list = runner.query(sql, new BeanListHandler<Course>(Course.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean paiKe(Integer couid, Integer tid, Integer claid) {

		String sql = "insert into ctc values(?,?,?)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, couid, tid, claid);
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
