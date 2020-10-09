package com.zr.teacherSystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理前端校验
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/checkServlet")
public class CheckServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取请求操作指令
		String cmd = req.getParameter("cmd");
		System.out.println("cmd: " + cmd);

	}
}
