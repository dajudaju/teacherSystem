package com.zr.teacherSystem.service;

import java.util.List;

import com.zr.teacherSystem.pojo.Course;
import com.zr.teacherSystem.pojo.CtcKey;

/**
 * 课程服务接口
 * 
 * @author Administrator
 *
 */
public interface ICourseService {

	List<Course> getCoursePager(int pageNO, int size);

	Course getCourseId(int id);

	int getCourseCount();

	int insert(Course entity);

	int delete(int id);

	int update(Course entity);

	int deletes(int[] ids);

	void insertBatch(List<CtcKey> ctclist);

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
