package com.tranquocanh.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tranquocanh.builder.NewBuilder;
import com.tranquocanh.constant.SystemConstant;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.paging.PageRequest;
import com.tranquocanh.paging.Pageble;
import com.tranquocanh.service.ICategoryService;
import com.tranquocanh.service.INewService;
import com.tranquocanh.sort.Sorter;
import com.tranquocanh.utils.FormUtil;
import com.tranquocanh.utils.message.MessageUtil;


@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet{
	private static final long serialVersionUID = 2686801510274002166L;
	
	@Inject
	private INewService newService;

	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
		NewModel newModel = new NewModel();
		newModel = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if(newModel.getType().equals(SystemConstant.LIST)) {
            if(newModel.getMessage() != null && newModel.getAlert() != null) {
                MessageUtil.of(newModel.getMessage(),newModel.getAlert()).buildMessage(request);
            }
			NewBuilder builder = new NewBuilder.Builder()
			        .setTitle(newModel.getTitle())
					.setCode(newModel.getCategoryCode())
					.build();
			Pageble pageble = new PageRequest(newModel.getPage(), newModel.getMaxPageItem(), new Sorter(newModel.getSortName(), newModel.getSortBy()));
			newModel.setTotalItem(newService.getTotalItem(builder));
			newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem())) ;
			newModel.setListResults(newService.findAll(builder,pageble));
			view = "/views/admin/news/list.jsp";

		} else if(newModel.getType().equals(SystemConstant.EDIT)) {
			if(newModel.getId() != null) {
				newModel = newService.findOne(newModel.getId());
			}
			view = "/views/admin/news/edit.jsp";
		}
		request.setAttribute("categories",categoryService.findAll());
		request.setAttribute(SystemConstant.MODEL, newModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
