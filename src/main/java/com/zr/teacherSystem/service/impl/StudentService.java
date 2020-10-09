package com.zr.teacherSystem.service.impl;

import java.util.List;

import com.zr.teacherSystem.dao.IStudentDao;
import com.zr.teacherSystem.dao.impl.StudentDao;
import com.zr.teacherSystem.pojo.CourseExt;
import com.zr.teacherSystem.pojo.GradeExt;
import com.zr.teacherSystem.pojo.Sc;
import com.zr.teacherSystem.pojo.Student;
import com.zr.teacherSystem.pojo.StudentExt;
import com.zr.teacherSystem.service.IStudentService;

/**
 * 学生服务接口实现类
 * 
 * @author Administrator
 *
 */
public class StudentService implements IStudentService {

	// 学生dao层对象
	private IStudentDao dao = new StudentDao();

	@Override
	public List<Student> getStudentPager(int pageNO, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multiDelete(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentExt> getStuByCid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student stulogin(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseExt> getXuxiu(int classid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inserBatch(List<Sc> sclist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CourseExt> getMycourses(int classid, int stuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudent() {

		return dao.getAllStudent();
	}

	@Override
	public boolean addStu(Student student) {

		return dao.addStu(student);
	}

	@Override
	public Student getStudentById(Integer id) {

		return dao.getStudentById(id);
	}

	@Override
	public boolean updateStu(Student student) {

		return dao.updateStu(student);
	}

	@Override
	public boolean deleteStuByIds(int[] ids) {

		return dao.deleteStuByIds(ids);
	}

	@Override
	public List<CourseExt> findStuClassTable(String name) {

		return dao.findStuClassTable(name);
	}

	@Override
	public List<GradeExt> findGrade(String name) {

		return dao.findGrade(name);
	}

}
