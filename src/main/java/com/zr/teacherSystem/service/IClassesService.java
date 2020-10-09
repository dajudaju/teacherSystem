package com.zr.teacherSystem.service;

import java.util.List;

import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.StudentExt;

/**
 * 班级服务接口
 * 
 * @author Administrator
 *
 */
public interface IClassesService {

	List<Classes> getClassPager(int pageNO, int size);

	Classes getCLassId(int id);

	int getClassesCount();

	int insert(Classes entity);

	int delete(int id);

	int deletes(int[] ids);

	int update(Classes entity);

	Classes getStuByid(int id);

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
