package com.zr.teacherSystem.dao;

import java.util.List;

import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.StudentExt;

/**
 * 班级dao层接口
 * 
 * @author Administrator
 *
 */
public interface IClassesDao {
	
	/**
	 * 获取所有班级信息
	 * 
	 * @return
	 */
	List<Classes> getAllClasses();

	/**
	 * 添加班级信息
	 * 
	 * @param classes
	 * @return
	 */
	boolean addCla(Classes classes);

	/**
	 * 根据id查询班级信息
	 * 
	 * @param id
	 * @return
	 */
	Classes getClassesById(Integer id);

	/**
	 * 编辑班级信息
	 * 
	 * @param classes
	 * @return
	 */
	boolean updateCla(Classes classes);

	/**
	 * 根据id删除班级信息
	 * 
	 * @param ids
	 * @return
	 */
	boolean deleteClaByIds(int[] ids);
	
	/**
	 * 根据班级id查询本班所有学生
	 * 
	 * @param id
	 * @return
	 */
	List<StudentExt> findStuById(Integer id);

}
