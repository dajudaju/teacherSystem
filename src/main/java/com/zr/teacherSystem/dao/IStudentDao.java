package com.zr.teacherSystem.dao;

import java.util.List;

import com.zr.teacherSystem.pojo.CourseExt;
import com.zr.teacherSystem.pojo.GradeExt;
import com.zr.teacherSystem.pojo.Student;

/**
 * 学生dao层接口
 * 
 * @author Administrator
 *
 */
public interface IStudentDao {

	/**
	 * 获取所有学生信息
	 * 
	 * @return 一个包含所有学生对象的集合
	 */
	List<Student> getAllStudent();

	/**
	 * 添加学生信息
	 * 
	 * @param student
	 * @return 一个boolean值,true为成功,false表示失败
	 */
	boolean addStu(Student student);

	/**
	 * 根据id查询学生信息
	 * 
	 * @param id
	 * @return
	 */
	Student getStudentById(Integer id);

	/**
	 * 编辑学生信息
	 * 
	 * @param student
	 * @return
	 */
	boolean updateStu(Student student);

	/**
	 * 根据学生id删除学生信息
	 * 
	 * @param ids
	 * @return
	 */
	boolean deleteStuByIds(int[] ids);

	/**
	 * 根据用户名查询学生课程信息
	 * 
	 * @param name
	 * @return
	 */
	List<CourseExt> findStuClassTable(String name);

	/**
	 * 根据用户名查询学生成绩
	 * 
	 * @param name
	 * @return
	 */
	List<GradeExt> findGrade(String name);
}
