package com.zr.teacherSystem.service.impl;

import java.util.List;

import com.zr.teacherSystem.dao.IClassesDao;
import com.zr.teacherSystem.dao.impl.ClassesDao;
import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.StudentExt;
import com.zr.teacherSystem.service.IClassesService;

/**
 * 班级服务接口实现类
 * 
 * @author Administrator
 *
 */
public class ClassesService implements IClassesService {

	// 获取班级dao层
	private IClassesDao dao = new ClassesDao();

	@Override
	public List<Classes> getClassPager(int pageNO, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classes getCLassId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClassesCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Classes entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletes(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Classes entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Classes getStuByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCla(Classes classes) {

		return dao.addCla(classes);
	}

	@Override
	public Classes getClassesById(Integer id) {

		return dao.getClassesById(id);
	}

	@Override
	public boolean updateCla(Classes classes) {

		return dao.updateCla(classes);
	}

	@Override
	public boolean deleteClaByIds(int[] ids) {

		return dao.deleteClaByIds(ids);
	}

	@Override
	public List<Classes> getAllClasses() {

		return dao.getAllClasses();
	}

	@Override
	public List<StudentExt> findStuById(Integer id) {
		
		return dao.findStuById(id);
	}
}
