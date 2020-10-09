package com.zr.teacherSystem.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zr.teacherSystem.dao.ITeacherDao;
import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.Course;
import com.zr.teacherSystem.pojo.Course_Class;
import com.zr.teacherSystem.pojo.Grade;
import com.zr.teacherSystem.pojo.Student;
import com.zr.teacherSystem.pojo.StudentView;
import com.zr.teacherSystem.pojo.Teacher;
import com.zr.teacherSystem.utils.TxDBUtils;

/**
 * 教师dao层实现类
 * 
 * @author Administrator
 *
 */
public class TeacherDao implements ITeacherDao {

	@Override
	public List<Teacher> getAllTeacher() {

		List<Teacher> list = null;
		String sql = "select * from teacher";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			list = runner.query(sql, new BeanListHandler<Teacher>(Teacher.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean addTea(Teacher teacher) {

		String sql = "insert into teacher values(null,?,?,?,?,null)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, teacher.getName(), teacher.getUsertype(), teacher.getLoginname(),
					teacher.getPassword());
			if (line == 1) {
				// 更新user表
				String sql2 = "insert into user values(null,?,?,?)";
				int line2 = runner.update(sql2, teacher.getLoginname(), teacher.getPassword(), 3);
				if (line2 == 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Teacher getTeacherById(Integer id) {

		Teacher teacher = null;
		String sql = "select * from teacher where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			teacher = runner.query(sql, new BeanHandler<Teacher>(Teacher.class), id);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return teacher;
	}

	@Override
	public boolean updateTea(Teacher teacher) {

		String sql = "update teacher set name=?,loginname=?,password=? where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, teacher.getName(), teacher.getLoginname(), teacher.getPassword(),
					teacher.getId());
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteTeaByIds(int[] ids) {

		String sql = "delete from teacher where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			for (int i = 0; i < ids.length; i++) {
				int line = runner.update(sql, ids[i]);
				if (line != 1) {
					System.out.println("删除id为 " + ids[i] + "的老师信息失败!");
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
	public List<Course_Class> findTeaClassTable(String name) {

		List<Course_Class> list = new ArrayList<Course_Class>();
		String sql = "select * from classes where id in(select claid from ctc where tid=(select id from teacher where loginname=?))";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			List<Classes> list1 = runner.query(sql, new BeanListHandler<Classes>(Classes.class), name);

			String sql2 = "select * from course where id in(select couid from ctc where tid=(select id from teacher where loginname=?) and claid=?)";
			for (int i = 0; i < list1.size(); i++) {

				Course course = runner.query(sql2, new BeanHandler<Course>(Course.class), name, list1.get(i).getId());
				Course_Class cc = new Course_Class(list1.get(i), course);
				list.add(cc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<StudentView> findStus(String name) {

		List<StudentView> list = new ArrayList<StudentView>();
		List<Course_Class> cclist = findTeaClassTable(name);

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			String sql = "select * from student where classid=?";
			for (int i = 0; i < cclist.size(); i++) {
				List<Student> stulist = runner.query(sql, new BeanListHandler<Student>(Student.class),
						cclist.get(i).getClasses().getId());

				for (int j = 0; j < stulist.size(); j++) {
					StudentView sv = new StudentView(stulist.get(j), cclist.get(i).getCourse().getName(),
							cclist.get(i).getCourse().getId());
					list.add(sv);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Teacher getTeacherByLoginname(String name) {

		Teacher teacher = null;
		String sql = "select * from teacher where loginname=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			teacher = runner.query(sql, new BeanHandler<Teacher>(Teacher.class), name);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return teacher;
	}

	@Override
	public boolean setGrade(Grade grade) {

		String sql = "insert into grade values(null,?,?,?,?,?,?)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, grade.getSid(), grade.getCid(), grade.getTid(), grade.getPgrade(),
					grade.getKgrade(), grade.getZgrade());
			if (line == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
