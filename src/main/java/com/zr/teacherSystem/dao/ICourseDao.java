package com.zr.teacherSystem.dao;

import java.util.List;

import com.zr.teacherSystem.pojo.Course;

/**
 * 课程dao层接口
 * 
 * @author Administrator
 *
 */
public interface ICourseDao {

	/**
	 * 根据id删除课程信息
	 * 
	 * @param ids
	 * @return
	 */
	boolean deleteCouByIds(int[] ids);

	/**
	 * 编辑课程信息
	 * 
	 * @param course
	 * @return
	 */
	boolean updateCou(Course course);

	/**
	 * 根据id查询课程信息
	 * 
	 * @param id
	 * @return
	 */
	Course getCourseById(Integer id);

	/**
	 * 添加课程
	 * 
	 * @param course
	 * @return
	 */
	boolean addCou(Course course);

	/**
	 * 查询所有课程信息
	 * 
	 * @return
	 */
	List<Course> getAllCou();

	/**
	 * 排课
	 * 
	 * @param couid
	 * @param tid
	 * @param claid
	 * @return
	 */
	boolean paiKe(Integer couid, Integer tid, Integer claid);
}
