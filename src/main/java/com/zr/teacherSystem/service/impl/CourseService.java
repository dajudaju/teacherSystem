package com.zr.teacherSystem.service.impl;

import java.util.List;

import com.zr.teacherSystem.dao.ICourseDao;
import com.zr.teacherSystem.dao.impl.CourseDao;
import com.zr.teacherSystem.pojo.Course;
import com.zr.teacherSystem.pojo.CtcKey;
import com.zr.teacherSystem.service.ICourseService;

/**
 * 课程服务接口实现类
 * 
 * @author Administrator
 *
 */
public class CourseService implements ICourseService {

	// 获取课程dao层
	private ICourseDao dao = new CourseDao();

	@Override
	public List<Course> getCoursePager(int pageNO, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourseId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCourseCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Course entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Course entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletes(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBatch(List<CtcKey> ctclist) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteCouByIds(int[] ids) {

		return dao.deleteCouByIds(ids);
	}

	@Override
	public boolean updateCou(Course course) {

		return dao.updateCou(course);
	}

	@Override
	public Course getCourseById(Integer id) {

		return dao.getCourseById(id);
	}

	@Override
	public boolean addCou(Course course) {

		return dao.addCou(course);
	}

	@Override
	public List<Course> getAllCou() {

		return dao.getAllCou();
	}

	@Override
	public boolean paiKe(Integer couid, Integer tid, Integer claid) {
		
		return dao.paiKe(couid, tid, claid);
	}

}
