package com.tranquocanh.controller.web;

import com.tranquocanh.model.UserModel;
import com.tranquocanh.security.AuthenticationFilter;
import com.tranquocanh.service.ICategoryService;
import com.tranquocanh.service.impl.NewService;
import com.tranquocanh.service.impl.UserService;
import com.tranquocanh.utils.FormUtil;
import com.tranquocanh.utils.SessionUtil;
import com.tranquocanh.utils.message.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = { "/trang-chu", "/tin-tuc", "/dang-nhap","/thoat" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private ICategoryService categoryService;

	@Inject
	private NewService newService;

	@Inject
	private UserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundle bundle = ResourceBundle.getBundle("message");
		UserModel model = FormUtil.toModel(UserModel.class,request);
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			if(model.getMessage() !=null && model.getAlert() != null) {
				MessageUtil.of(bundle.getString(model.getMessage()),model.getAlert()).buildMessage(request);
			}
				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			// do something
			SessionUtil.getInstance().removeValue(request,"USER_MODEL");
            response.sendRedirect("trang-chu");
		} else {
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			String url =AuthenticationFilter.of(model.getUserName(), model.getPassword()).urlRedirect(request);
			response.sendRedirect(url);
		} else if (action != null && action.equals("logout")) {

		} else {
			UserModel userModel = FormUtil.toModel(UserModel.class, request);
			if (userModel.getUserName().equals("admin") && userModel.getPassword().equals("admin")) {
				response.sendRedirect("admin-home");
			} else {
				response.sendRedirect("trang-chu");
			}

		}

	}
}
