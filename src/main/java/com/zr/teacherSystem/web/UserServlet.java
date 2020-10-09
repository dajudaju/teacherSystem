package com.zr.teacherSystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.teacherSystem.pojo.User;
import com.zr.teacherSystem.service.IUserService;
import com.zr.teacherSystem.service.impl.UserService;

/**
 * 所有角色的共同服务处理
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取请求操作指令
		String cmd = req.getParameter("cmd");
		System.out.println("cmd: " + cmd);

		if ("login".equals(cmd)) {
			// 登录操作
			login(req, resp);
		} else if ("changePwd".equals(cmd)) {
			// 修改密码
			changePwd(req, resp);
		} else if ("logout".equals(cmd)) {
			// 退出
			logout(req, resp);
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// 销毁session
		req.getSession().invalidate();
//		resp.getWriter().write("<script>window.location.href('/teacherSystem/');</script>");
		resp.sendRedirect("/teacherSystem/");

	}

	/**
	 * 修改密码
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void changePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取信息
		User user = (User) req.getSession().getAttribute("user");

		String password = req.getParameter("password");
		if (!password.equals(user.getPassword())) {
			// 原密码不正确
			req.setAttribute("message", "原密码输入不正确!");
			req.getRequestDispatcher("homepage/test.jsp").forward(req, resp);
			return;
		}
		String newPassword = req.getParameter("newPassword");

		// 获取用户服务
		IUserService service = new UserService();

		boolean flag = service.changePwd(newPassword, user.getUsertype(), user.getName());

		if (flag) {
			// 修改成功
			System.out.println("修改成功!");
			// 退出系统,需要重新登录
			logout(req, resp);
		} else {
			// 修改失败
			System.out.println("修改失败!");
			req.setAttribute("message", "修改失败!");
			req.getRequestDispatcher("homepage/test.jsp").forward(req, resp);
		}

	}

	/**
	 * 登录处理
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取前端参数
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		Integer usertype = Integer.parseInt(req.getParameter("usertype"));
		System.out.println("登录: " + name + "--" + password + "--" + usertype);

		// 封装到用户对象
		User user = new User(null, name, password, usertype);

		// 获取登录服务
		IUserService service = new UserService();

		User u = service.userlogin(user);

		if (u != null) {
			// 登录成功,创建user的session
			System.out.println("u: " + u.toString());
			req.getSession().setAttribute("user", u);
			req.getRequestDispatcher("homepage/index.jsp").forward(req, resp);

		} else {
			// 登录失败
			System.out.println("登录失败");
		}

	}

}
