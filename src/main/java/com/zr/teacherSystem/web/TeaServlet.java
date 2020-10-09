package com.zr.teacherSystem.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.teacherSystem.pojo.Course_Class;
import com.zr.teacherSystem.pojo.Grade;
import com.zr.teacherSystem.pojo.StudentView;
import com.zr.teacherSystem.pojo.Teacher;
import com.zr.teacherSystem.pojo.User;
import com.zr.teacherSystem.service.ITeacherService;
import com.zr.teacherSystem.service.impl.TeacherService;

/**
 * 教师服务
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/teaServlet")
public class TeaServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取操作指令
		String cmd = req.getParameter("cmd");
//		System.out.println("tea_cmd: " + cmd);

		if ("getTeaList".equals(cmd)) {
			// 获取教师信息,跳转到教师管理
			getTeaList(req, resp);
		} else if ("addTea".equals(cmd)) {
			// 添加教师
			addTea(req, resp);
		} else if ("findTeaById".equals(cmd)) {
			// 根据id查找老师,编辑前的准备
			findTeaById(req, resp);
		} else if ("updateTea".equals(cmd)) {
			// 编辑老师
			updateTea(req, resp);
		} else if ("deleteTea".equals(cmd)) {
			// 删除一个老师
			Integer id = Integer.parseInt(req.getParameter("id"));
			int[] ids = { id };

			deleteTea(req, resp, ids);
		} else if ("deleteAllTea".equals(cmd)) {
			// 删除一组老师

			String[] strings = req.getParameterValues("id");
			int[] ids = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				ids[i] = Integer.parseInt(strings[i]);
			}

			deleteTea(req, resp, ids);
		} else if ("findTeaClassTable".equals(cmd)) {
			// 查询老师课表
			findTeaClassTable(req, resp);
		} else if ("findStus".equals(cmd)) {
			// 查询本班所有学生信息
			findStus(req, resp);
		} else if ("setGrade".equals(cmd)) {
			// 录入成绩
			setGrade(req, resp);
		}

	}

	/**
	 * 录入成绩
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		Integer sid = Integer.parseInt(req.getParameter("sid"));
		Integer cid = Integer.parseInt(req.getParameter("cid"));
		Integer tid = Integer.parseInt(req.getParameter("tid"));
		Double pgrade = Double.parseDouble(req.getParameter("pgrade"));
		Double kgrade = Double.parseDouble(req.getParameter("kgrade"));
		Double zgrade = pgrade * 0.4 + kgrade * 0.6;

		// 封装到grade对象
		Grade grade = new Grade(null, sid, cid, tid, pgrade, kgrade, zgrade);

		// 获取老师服务
		ITeacherService service = new TeacherService();

		boolean flag = service.setGrade(grade);

		if (flag) {
			// 录入成功
			System.out.println("录入成功!");
		} else {
			// 录入失败
			System.out.println("录入失败!");
		}
		findStus(req, resp);

	}

	/**
	 * 根据老师,班级查询学生信息,根据登录老师的用户名
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findStus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取登录用户老师的信息
		User user = (User) req.getSession().getAttribute("user");

		// 获取老师服务
		ITeacherService service = new TeacherService();

		// 获取学生集合
		List<StudentView> stulist = service.findStus(user.getName());
		// 获取老师个人信息
		Teacher teacher = service.getTeacherByLoginname(user.getName());

		req.setAttribute("stulist", stulist);
		req.setAttribute("tid", teacher.getId());
		req.getRequestDispatcher("teacher/couOftea/stulist.jsp").forward(req, resp);
	}

	/**
	 * 查询老师课表
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findTeaClassTable(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 获取当前登录用户老师的loginname
		User user = (User) req.getSession().getAttribute("user");

		// 获取老师服务
		ITeacherService service = new TeacherService();

		List<Course_Class> cclist = service.findTeaClassTable(user.getName());

		req.setAttribute("cclist", cclist);
		req.getRequestDispatcher("teacher/couOftea/list.jsp").forward(req, resp);

	}

	/**
	 * 根据老师id删除老师
	 * 
	 * @param req
	 * @param resp
	 * @param ids
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteTea(HttpServletRequest req, HttpServletResponse resp, int[] ids)
			throws ServletException, IOException {

		// 获取老师服务
		ITeacherService service = new TeacherService();

		boolean flag = service.deleteTeaByIds(ids);

		if (flag) {
			// 删除成功
			System.out.println("删除成功!");
		} else {
			// 删除失败
			System.out.println("删除失败!");
		}

		getTeaList(req, resp);
	}

	/**
	 * 编辑老师信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateTea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Integer usertype = Integer.parseInt(req.getParameter("usertype"));
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");

		// 封装到老师对象
		Teacher teacher = new Teacher(id, name, usertype, loginname, password, null);

		// 获取老师服务
		ITeacherService service = new TeacherService();
		boolean flag = service.updateTea(teacher);

		if (flag) {
			// 编辑成功
			System.out.println("编辑成功!");
			getTeaList(req, resp);
		} else {
			// 编辑失败
			String message = "编辑失败!";
			req.setAttribute("message", message);
			req.getRequestDispatcher("teacher/edit.jsp").forward(req, resp);
		}

	}

	/**
	 * 根据id查找老师,编辑前的准备
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findTeaById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取老师id
		Integer id = Integer.parseInt(req.getParameter("id"));
//		System.out.println("id: " + id);

		// 获取老师服务
		ITeacherService service = new TeacherService();

		Teacher teacher = service.getTeacherById(id);

		if (teacher != null) {
			// 查询成功
			req.setAttribute("teacher", teacher);
			req.getRequestDispatcher("teacher/edit.jsp").forward(req, resp);
		} else {
			System.out.println("该老师id不存在!");
		}

	}

	/**
	 * 添加教师
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addTea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取参数
		String name = req.getParameter("name");
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");

		// 封装进teacher对象
		Teacher teacher = new Teacher(null, name, 3, loginname, password, null);

		// 获取教师服务
		ITeacherService service = new TeacherService();

		boolean flag = service.addTea(teacher);

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
		req.getRequestDispatcher("teacher/add.jsp").forward(req, resp);

	}

	/**
	 * 获取所有教师信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void getTeaList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取教师服务
		ITeacherService service = new TeacherService();

		List<Teacher> tealist = service.getAllTeacher();

		req.setAttribute("tealist", tealist);
		req.getRequestDispatcher("teacher/list.jsp").forward(req, resp);

	}

}
