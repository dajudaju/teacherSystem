package com.zr.teacherSystem.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.Course;
import com.zr.teacherSystem.pojo.Teacher;
import com.zr.teacherSystem.service.IClassesService;
import com.zr.teacherSystem.service.ICourseService;
import com.zr.teacherSystem.service.ITeacherService;
import com.zr.teacherSystem.service.impl.ClassesService;
import com.zr.teacherSystem.service.impl.CourseService;
import com.zr.teacherSystem.service.impl.TeacherService;

@SuppressWarnings("serial")
@WebServlet("/couServlet")
public class CourseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取操作指令
		String cmd = req.getParameter("cmd");

		if ("getCouList".equals(cmd)) {
			// 获取课程信息,跳转到课程管理
			getCouList(req, resp);
		} else if ("addCou".equals(cmd)) {
			// 添加课程
			addCou(req, resp);
		} else if ("findCouById".equals(cmd)) {
			// 根据id查找课程,编辑前的准备
			findCouById(req, resp);
		} else if ("updateCou".equals(cmd)) {
			// 编辑课程
			updateCou(req, resp);
		} else if ("deleteCou".equals(cmd)) {
			// 删除一个课程
			Integer id = Integer.parseInt(req.getParameter("id"));
			int[] ids = { id };

			deleteCou(req, resp, ids);
		} else if ("deleteAllCou".equals(cmd)) {
			// 删除一组课程
			String[] strings = req.getParameterValues("id");
			int[] ids = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				ids[i] = Integer.parseInt(strings[i]);
			}

			deleteCou(req, resp, ids);
		} else if ("prePaiKe".equals(cmd)) {
			// 排课前的准备
			prePaiKe(req, resp);
		} else if ("paiKe".equals(cmd)) {
			// 排课
			paiKe(req, resp);
		}
	}

	/**
	 * 排课
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void paiKe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取排课信息
		Integer couid = Integer.parseInt(req.getParameter("couid"));
		Integer tid = Integer.parseInt(req.getParameter("tid"));
		Integer claid = Integer.parseInt(req.getParameter("claid"));

		// 获取课程服务
		ICourseService service = new CourseService();
		boolean flag = service.paiKe(couid, tid, claid);

		if (flag) {
			// 排课成功
			System.out.println("排课成功!");
			getCouList(req, resp);
		} else {
			// 排课失败
			System.out.println("排课失败!");
			req.setAttribute("message", "排课失败!");
			req.getRequestDispatcher("course/setct.jsp").forward(req, resp);
		}
	}

	/**
	 * 排课前的准备工作
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void prePaiKe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取课程id
		Integer id = Integer.parseInt(req.getParameter("id"));

		// 获取课程服务
		ICourseService couService = new CourseService();
		Course course = couService.getCourseById(id);
		// 得到课程名称和课程类别
		String cou_name = course.getName();
		String cou_type = course.getType();

		// 获取老师服务
		ITeacherService teaService = new TeacherService();
		// 获取所有老师信息
		List<Teacher> tea_list = teaService.getAllTeacher();

		// 获取班级服务
		IClassesService claService = new ClassesService();
		// 获取所有班级信息
		List<Classes> cla_list = claService.getAllClasses();

		// 将所有信息传递到排课
		req.setAttribute("couid", id);
		req.setAttribute("couname", cou_name);
		req.setAttribute("coutype", cou_type);
		req.setAttribute("tealist", tea_list);
		req.setAttribute("clalist", cla_list);
		req.getRequestDispatcher("course/setct.jsp").forward(req, resp);

	}

	/**
	 * 根据老师id删除课程
	 * 
	 * @param req
	 * @param resp
	 * @param ids
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteCou(HttpServletRequest req, HttpServletResponse resp, int[] ids)
			throws ServletException, IOException {

		// 获取课程服务
		ICourseService service = new CourseService();

		boolean flag = service.deleteCouByIds(ids);

		if (flag) {
			// 删除成功
			System.out.println("删除成功!");
		} else {
			// 删除失败
			System.out.println("删除失败!");
		}

		getCouList(req, resp);
	}

	/**
	 * 编辑课程信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateCou(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String type = req.getParameter("type");

		// 封装到course对象
		Course course = new Course(id, name, type, null, null);

		// 获取课程服务
		ICourseService service = new CourseService();
		boolean flag = service.updateCou(course);

		if (flag) {
			// 编辑成功
			System.out.println("编辑成功!");
			getCouList(req, resp);
		} else {
			// 编辑失败
			String message = "编辑失败!";
			req.setAttribute("message", message);
			req.getRequestDispatcher("course/edit.jsp").forward(req, resp);
		}

	}

	/**
	 * 根据id查找课程,编辑前的准备
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findCouById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取课程id
		Integer id = Integer.parseInt(req.getParameter("id"));
//		System.out.println("id: " + id);

		// 获取课程服务
		ICourseService service = new CourseService();

		Course course = service.getCourseById(id);

		if (course != null) {
			// 查询成功
			req.setAttribute("course", course);
			req.getRequestDispatcher("course/edit.jsp").forward(req, resp);
		} else {
			System.out.println("该课程id不存在!");
		}

	}

	/**
	 * 添加课程
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addCou(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取参数
		String name = req.getParameter("name");
		String type = req.getParameter("type");

		// 封装进course对象
		Course course = new Course(null, name, type, null, null);

		// 获取课程服务
		ICourseService service = new CourseService();

		boolean flag = service.addCou(course);

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
		req.getRequestDispatcher("course/add.jsp").forward(req, resp);

	}

	/**
	 * 获取所有课程信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void getCouList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取课程服务
		ICourseService service = new CourseService();

		List<Course> coulist = service.getAllCou();

		req.setAttribute("coulist", coulist);
		req.getRequestDispatcher("course/list.jsp").forward(req, resp);

	}

}
