package com.zr.teacherSystem.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zr.teacherSystem.dao.IClassesDao;
import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.Student;
import com.zr.teacherSystem.pojo.StudentExt;
import com.zr.teacherSystem.utils.TxDBUtils;

/**
 * 班级dao层接口实现类
 * 
 * @author Administrator
 *
 */
public class ClassesDao implements IClassesDao {

	@Override
	public List<Classes> getAllClasses() {

		List<Classes> list = null;
		String sql = "select * from classes";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			list = runner.query(sql, new BeanListHandler<Classes>(Classes.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean addCla(Classes classes) {

		String sql = "insert into classes values(null,?,?,0)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, classes.getName(), classes.getType());
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Classes getClassesById(Integer id) {

		Classes classes = null;
		String sql = "select * from classes where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			classes = runner.query(sql, new BeanHandler<Classes>(Classes.class), id);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return classes;
	}

	@Override
	public boolean updateCla(Classes classes) {

		String sql = "update classes set name=?,type=? where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, classes.getName(), classes.getType(), classes.getId());
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteClaByIds(int[] ids) {

		String sql = "delete from classes where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			for (int i = 0; i < ids.length; i++) {
				int line = runner.update(sql, ids[i]);
				if (line != 1) {
					System.out.println("删除id为 " + ids[i] + "的班级信息失败!");
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
	public List<StudentExt> findStuById(Integer id) {

		List<StudentExt> list = new ArrayList<StudentExt>();

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			String sql = "select name from classes where id=?";
			String classname = runner.query(sql, new ScalarHandler<String>(), id);

			String sql1 = "select * from student where classid=?";
			List<Student> list2 = runner.query(sql1, new BeanListHandler<Student>(Student.class), id);

			for (int i = 0; i < list2.size(); i++) {
				StudentExt sExt = new StudentExt(list2.get(i), classname);
				list.add(sExt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
