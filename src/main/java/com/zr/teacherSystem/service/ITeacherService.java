package com.zr.teacherSystem.service;

import java.util.List;

import com.zr.teacherSystem.pojo.Course_Class;
import com.zr.teacherSystem.pojo.Grade;
import com.zr.teacherSystem.pojo.StudentView;
import com.zr.teacherSystem.pojo.Teacher;

/**
 * 教师服务接口
 * 
 * @author Administrator
 *
 */
public interface ITeacherService {

	List<Teacher> getTeacherPager(int pageNO, int size);

	Teacher getTeacherId(int id);

	int getTeacherCount();

	int insert(Teacher entity);

	int delete(int id);

	int update(Teacher entity);

	int deletes(int[] ids);

	/**
	 * 获取所有教师信息
	 * 
	 * @return 一个教师对象的集合
	 */
	List<Teacher> getAllTeacher();

	public int getMyStuCount(int id);

	public Teacher loginTea(Teacher tea);

	public List<StudentView> getMystus(int tid);

	int insertGrade(Grade grade);

	/**
	 * 添加老师
	 * 
	 * @param teacher
	 * @return
	 */
	boolean addTea(Teacher teacher);

	/**
	 * 根据老师id查找老师信息
	 * 
	 * @param id
	 * @return 一个teacher对象
	 */
	Teacher getTeacherById(Integer id);

	/**
	 * 编辑老师信息
	 * 
	 * @param teacher
	 * @return boolean值,true为成功,false为失败
	 */
	boolean updateTea(Teacher teacher);

	/**
	 * 根据老师id删除老师信息
	 * 
	 * @param ids
	 * @return boolean值,true为成功,false为失败
	 */
	boolean deleteTeaByIds(int[] ids);

	/**
	 * 根据用户名查询老师课表
	 * 
	 * @param name
	 * @return
	 */
	List<Course_Class> findTeaClassTable(String name);

	/**
	 * 根据用户名查询班级学生
	 * 
	 * @param name
	 * @return
	 */
	List<StudentView> findStus(String name);

	/**
	 * 根据用户名查找老师信息
	 * 
	 * @param name
	 * @return
	 */
	Teacher getTeacherByLoginname(String name);

	/**
	 * 录入成绩
	 * 
	 * @param grade
	 * @return
	 */
	boolean setGrade(Grade grade);

}
