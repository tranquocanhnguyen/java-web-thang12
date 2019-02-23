package com.tranquocanh.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.model.UserModel;
import com.tranquocanh.service.INewService;
import com.tranquocanh.utils.HttpUtil;
import com.tranquocanh.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;

	// insert
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newModel.setCreatedBy(((UserModel)SessionUtil.getInstance().getValue(request,"USER_MODEL")).getUserName());
		newModel = newService.save(newModel);
		objectMapper.writeValue(response.getOutputStream(), newModel);

	}

	// update
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel updateNews = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		updateNews.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(request,"USER_MODEL")).getUserName());
		updateNews = newService.update(updateNews);
		objectMapper.writeValue(response.getOutputStream(), updateNews);

	}

	// delete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel deleteNews = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		//newService.delete(deleteNews.getIds());
		objectMapper.writeValue(response.getOutputStream(), "{}");

	}
}
