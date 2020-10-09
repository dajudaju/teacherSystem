package com.zr.teacherSystem.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zr.teacherSystem.dao.IStudentDao;
import com.zr.teacherSystem.pojo.Course;
import com.zr.teacherSystem.pojo.CourseExt;
import com.zr.teacherSystem.pojo.CtcKey;
import com.zr.teacherSystem.pojo.Grade;
import com.zr.teacherSystem.pojo.GradeExt;
import com.zr.teacherSystem.pojo.Student;
import com.zr.teacherSystem.utils.TxDBUtils;

/**
 * 学生dao层接口实现类
 * 
 * @author Administrator
 *
 */
public class StudentDao implements IStudentDao {

	@Override
	public List<Student> getAllStudent() {

		List<Student> list = null;
		String sql = "select * from student";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			list = runner.query(sql, new BeanListHandler<Student>(Student.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean addStu(Student student) {

		String sql = "insert into student values(null,?,?,?,?,?,?,?,?,null)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, student.getName(), student.getSex(), student.getAddress(), student.getTel(),
					student.getClassid(), student.getUsertype(), student.getPassword(), student.getLoginname());
			if (line == 1) {
				// 更新user表
				String sql2 = "insert into user values(null,?,?,?)";
				int line2 = runner.update(sql2, student.getLoginname(), student.getPassword(), 2);
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
	public Student getStudentById(Integer id) {

		Student student = null;
		String sql = "select * from student where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			student = runner.query(sql, new BeanHandler<Student>(Student.class), id);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return student;
	}

	@Override
	public boolean updateStu(Student student) {

		String sql = "update student set name=?,loginname=?,password=?,address=?,tel=?,sex=? where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			int line = runner.update(sql, student.getName(), student.getLoginname(), student.getPassword(),
					student.getAddress(), student.getTel(), student.getSex(), student.getId());
			if (line == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteStuByIds(int[] ids) {

		String sql = "delete from student where id=?";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			for (int i = 0; i < ids.length; i++) {
				int line = runner.update(sql, ids[i]);
				if (line != 1) {
					System.out.println("删除id为 " + ids[i] + "的学生信息失败!");
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
	public List<CourseExt> findStuClassTable(String name) {

		List<CourseExt> list = new ArrayList<CourseExt>();
		String sql = "select * from ctc where claid=(select classid from student where loginname=?)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			List<CtcKey> ctclist = runner.query(sql, new BeanListHandler<CtcKey>(CtcKey.class), name);

			for (int i = 0; i < ctclist.size(); i++) {
				String sql2 = "select * from course where id=?";
				Course course = runner.query(sql2, new BeanHandler<Course>(Course.class), ctclist.get(i).getCouid());

				String sql3 = "select name from teacher where id=?";
				String tname = runner.query(sql3, new ScalarHandler<String>(), ctclist.get(i).getTid());

				// 封装为courseext对象
				CourseExt ext = new CourseExt(course, tname);
				System.out.println(ext);
				list.add(ext);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<GradeExt> findGrade(String name) {

		List<GradeExt> list = new ArrayList<GradeExt>();
		String sql = "select * from grade where sid=(select id from student where loginname=?)";

		QueryRunner runner = new QueryRunner(TxDBUtils.getSource());

		try {

			List<Grade> list2 = runner.query(sql, new BeanListHandler<Grade>(Grade.class), name);
			for (int i = 0; i < list2.size(); i++) {
				String sql2 = "select name from course where id=?";
				String sql3 = "select name from teacher where id=?";

				String cname = runner.query(sql2, new ScalarHandler<String>(), list2.get(i).getCid());
				String tname = runner.query(sql3, new ScalarHandler<String>(), list2.get(i).getTid());
				
				// 封装到CourseExt对象
				GradeExt ext = new GradeExt(list2.get(i), cname, tname);
				list.add(ext);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
