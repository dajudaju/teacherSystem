package com.zr.teacherSystem.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.CourseExt;
import com.zr.teacherSystem.pojo.GradeExt;
import com.zr.teacherSystem.pojo.Student;
import com.zr.teacherSystem.pojo.User;
import com.zr.teacherSystem.service.IClassesService;
import com.zr.teacherSystem.service.IStudentService;
import com.zr.teacherSystem.service.impl.ClassesService;
import com.zr.teacherSystem.service.impl.StudentService;

@SuppressWarnings("serial")
@WebServlet("/stuServlet")
public class StudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取操作指令
		String cmd = req.getParameter("cmd");

		if ("getStuList".equals(cmd)) {
			// 获取学生信息,跳转到学生管理
			getStuList(req, resp);
		} else if ("preAddStu".equals(cmd)) {
			// 添加学生前的准备
			preAddStu(req, resp);
		} else if ("addStu".equals(cmd)) {
			// 添加学生
			addStu(req, resp);
		} else if ("findStuById".equals(cmd)) {
			// 根据id查找学生,编辑前的准备
			findStuById(req, resp);
		} else if ("updateStu".equals(cmd)) {
			// 编辑学生
			updateStu(req, resp);
		} else if ("deleteStu".equals(cmd)) {
			// 删除一个学生
			Integer id = Integer.parseInt(req.getParameter("id"));
			int[] ids = { id };

			deleteStu(req, resp, ids);
		} else if ("deleteAllStu".equals(cmd)) {
			// 删除一组学生

			String[] strings = req.getParameterValues("id");
			int[] ids = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				ids[i] = Integer.parseInt(strings[i]);
			}

			deleteStu(req, resp, ids);
		} else if ("findStuClassTable".equals(cmd)) {
			// 查询学生课表
			findStuClassTable(req, resp);
		} else if ("findGrade".equals(cmd)) {
			// 查询成绩
			findGrade(req, resp);
		}

	}

	/**
	 * 查询学生成绩
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取用户信息
		User user = (User) req.getSession().getAttribute("user");

		// 获取学生服务
		IStudentService service = new StudentService();

		List<GradeExt> glist = service.findGrade(user.getName());

		req.setAttribute("glist", glist);
		req.getRequestDispatcher("student/grade.jsp").forward(req, resp);

	}

	/**
	 * 查询学生课表
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findStuClassTable(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 获取用户信息
		User user = (User) req.getSession().getAttribute("user");

		// 获取学生服务
		IStudentService service = new StudentService();

		List<CourseExt> ctlist = service.findStuClassTable(user.getName());

		req.setAttribute("ctlist", ctlist);
		req.getRequestDispatcher("student/cslist.jsp").forward(req, resp);

	}

	/**
	 * 添加学生前的准备,即获取所有班级信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void preAddStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取班级服务
		IClassesService service = new ClassesService();
		List<Classes> clalist = service.getAllClasses();

		req.setAttribute("clalist", clalist);
		req.getRequestDispatcher("student/add.jsp").forward(req, resp);

	}

	/**
	 * 根据id删除学生
	 * 
	 * @param req
	 * @param resp
	 * @param ids
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteStu(HttpServletRequest req, HttpServletResponse resp, int[] ids)
			throws ServletException, IOException {

		// 获取学生服务
		IStudentService service = new StudentService();

		boolean flag = service.deleteStuByIds(ids);

		if (flag) {
			// 删除成功
			System.out.println("删除成功!");
		} else {
			// 删除失败
			System.out.println("删除失败!");
		}

		getStuList(req, resp);

	}

	/**
	 * 编辑学生
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");
		Integer classid = null;
		if (req.getParameter("classid") != null) {
			classid = Integer.parseInt(req.getParameter("classid"));
		}

		// 封装到学生对象
		Student student = new Student(id, name, sex, address, tel, classid, 2, password, loginname);

		// 获取学生服务
		IStudentService service = new StudentService();
		boolean flag = service.updateStu(student);

		if (flag) {
			// 编辑成功
			System.out.println("编辑成功!");
			getStuList(req, resp);
		} else {
			// 编辑失败
			String message = "编辑失败!";
			req.setAttribute("message", message);
			req.getRequestDispatcher("student/edit.jsp").forward(req, resp);
		}

	}

	/**
	 * 根据id查找学生,编辑前的准备
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findStuById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取学生id
		Integer id = Integer.parseInt(req.getParameter("id"));

		// 获取老师服务
		IStudentService service = new StudentService();

		Student student = service.getStudentById(id);

		if (student != null) {
			// 查询成功
			req.setAttribute("student", student);
			req.getRequestDispatcher("student/edit.jsp").forward(req, resp);
		} else {
			System.out.println("该学生id不存在!");
		}

	}

	/**
	 * 添加学生
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		String name = req.getParameter("name");
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");
		Integer classid = null;
		if (req.getParameter("classid") != null) {
			classid = Integer.parseInt(req.getParameter("classid"));
		}

		// 封装到学生对象
		Student student = new Student(null, name, sex, address, tel, classid, 2, password, loginname);

		// 获取学生服务
		IStudentService service = new StudentService();

		boolean flag = service.addStu(student);

		// 提示信息
		String message = "";

		if (flag) {
			// 添加成功
			message = "添加成功!";
		} else {
			// 添加失败
			message = "添加失败!";
		}
		req.setAttribute("message", message);
		preAddStu(req, resp);
	}

	/**
	 * 获取所有学生信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void getStuList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取学生服务
		IStudentService service = new StudentService();

		List<Student> stulist = service.getAllStudent();

		req.setAttribute("stulist", stulist);
		req.getRequestDispatcher("student/list.jsp").forward(req, resp);

	}

}
