package com.zr.teacherSystem.service.impl;

import java.util.List;

import com.zr.teacherSystem.dao.ITeacherDao;
import com.zr.teacherSystem.dao.impl.TeacherDao;
import com.zr.teacherSystem.pojo.Course_Class;
import com.zr.teacherSystem.pojo.Grade;
import com.zr.teacherSystem.pojo.StudentView;
import com.zr.teacherSystem.pojo.Teacher;
import com.zr.teacherSystem.service.ITeacherService;

/**
 * 教师服务的实现类
 * 
 * @author Administrator
 *
 */
public class TeacherService implements ITeacherService {

	// 获取教师的dao层
	private ITeacherDao dao = new TeacherDao();

	@Override
	public List<Teacher> getTeacherPager(int pageNO, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher getTeacherId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTeacherCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Teacher entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Teacher entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletes(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Teacher> getAllTeacher() {

		return dao.getAllTeacher();
	}

	@Override
	public int getMyStuCount(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Teacher loginTea(Teacher tea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentView> getMystus(int tid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertGrade(Grade grade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addTea(Teacher teacher) {

		return dao.addTea(teacher);
	}

	@Override
	public Teacher getTeacherById(Integer id) {

		return dao.getTeacherById(id);
	}

	@Override
	public boolean updateTea(Teacher teacher) {
		
		return dao.updateTea(teacher);
	}

	@Override
	public boolean deleteTeaByIds(int[] ids) {

		return dao.deleteTeaByIds(ids);
	}

	@Override
	public List<Course_Class> findTeaClassTable(String name) {
	
		return dao.findTeaClassTable(name);
	}

	@Override
	public List<StudentView> findStus(String name) {

		return dao.findStus(name);
	}

	@Override
	public Teacher getTeacherByLoginname(String name) {
		
		return dao.getTeacherByLoginname(name);
	}

	@Override
	public boolean setGrade(Grade grade) {

		return dao.setGrade(grade);
	}

}
