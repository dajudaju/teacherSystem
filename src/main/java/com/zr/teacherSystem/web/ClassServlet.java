package com.zr.teacherSystem.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.teacherSystem.pojo.Classes;
import com.zr.teacherSystem.pojo.StudentExt;
import com.zr.teacherSystem.service.IClassesService;
import com.zr.teacherSystem.service.impl.ClassesService;

@SuppressWarnings("serial")
@WebServlet("/claServlet")
public class ClassServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取操作指令
		String cmd = req.getParameter("cmd");

		if ("getClaList".equals(cmd)) {
			// 获取班级信息,跳转到班级管理
			getClaList(req, resp);
		} else if ("addCla".equals(cmd)) {
			// 添加班级
			addCla(req, resp);
		} else if ("findClaById".equals(cmd)) {
			// 根据id查找班级,编辑前的准备
			findClaById(req, resp);
		} else if ("updateCla".equals(cmd)) {
			// 编辑班级
			updateCla(req, resp);
		} else if ("deleteCla".equals(cmd)) {
			// 删除一个班级
			Integer id = Integer.parseInt(req.getParameter("id"));
			int[] ids = { id };

			deleteCla(req, resp, ids);
		} else if ("deleteAllCla".equals(cmd)) {
			// 删除一组班级

			String[] strings = req.getParameterValues("id");
			int[] ids = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				ids[i] = Integer.parseInt(strings[i]);
			}

			deleteCla(req, resp, ids);
		} else if ("findStuById".equals(cmd)) {
			// 查找本班级学生信息
			findStuById(req, resp);
		}
	}

	/**
	 * 根据班级id查找本班级所有学生信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findStuById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取班级id
		Integer id = Integer.parseInt(req.getParameter("id"));

		// 获取班级服务
		IClassesService service = new ClassesService();

		List<StudentExt> slist = service.findStuById(id);

		req.setAttribute("slist", slist);
		req.getRequestDispatcher("class/stulist.jsp").forward(req, resp);

	}

	/**
	 * 根据老师id删除班级
	 * 
	 * @param req
	 * @param resp
	 * @param ids
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteCla(HttpServletRequest req, HttpServletResponse resp, int[] ids)
			throws ServletException, IOException {

		// 获取班级服务
		IClassesService service = new ClassesService();

		boolean flag = service.deleteClaByIds(ids);

		if (flag) {
			// 删除成功
			System.out.println("删除成功!");
		} else {
			// 删除失败
			System.out.println("删除失败!");
		}

		getClaList(req, resp);
	}

	/**
	 * 编辑班级信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateCla(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String type = req.getParameter("type");

		// 封装到班级对象
		Classes classes = new Classes(id, name, type, null, null);

		// 获取班级服务
		IClassesService service = new ClassesService();
		boolean flag = service.updateCla(classes);

		if (flag) {
			// 编辑成功
			System.out.println("编辑成功!");
			getClaList(req, resp);
		} else {
			// 编辑失败
			String message = "编辑失败!";
			req.setAttribute("message", message);
			req.getRequestDispatcher("class/edit.jsp").forward(req, resp);
		}

	}

	/**
	 * 根据id查找班级,编辑前的准备
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findClaById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取班级id
		Integer id = Integer.parseInt(req.getParameter("id"));
//		System.out.println("id: " + id);

		// 获取班级服务
		IClassesService service = new ClassesService();

		Classes classes = service.getClassesById(id);

		if (classes != null) {
			// 查询成功
			req.setAttribute("cla", classes);
			req.getRequestDispatcher("class/edit.jsp").forward(req, resp);
		} else {
			System.out.println("该班级id不存在!");
		}

	}

	/**
	 * 添加班级
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addCla(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取参数
		String name = req.getParameter("name");
		String type = req.getParameter("type");

		// 封装进classes对象
		Classes classes = new Classes(null, name, type, null, null);

		// 获取班级服务
		IClassesService service = new ClassesService();

		boolean flag = service.addCla(classes);

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
		req.getRequestDispatcher("class/add.jsp").forward(req, resp);

	}

	/**
	 * 获取所有班级信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void getClaList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取班级服务
		IClassesService service = new ClassesService();

		List<Classes> clalist = service.getAllClasses();

		req.setAttribute("clalist", clalist);
		req.getRequestDispatcher("class/list.jsp").forward(req, resp);

	}

}
