package com.tranquocanh.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.tranquocanh.builder.NewBuilder;
import com.tranquocanh.dao.ICategoryDAO;
import com.tranquocanh.dao.INewDAO;
import com.tranquocanh.model.CategoryModel;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.service.INewService;
import com.tranquocanh.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

public class NewService implements INewService{
	
	@Inject
	private INewDAO newDao;

	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewModel> findbyCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		CategoryModel categoryModel = categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(categoryModel.getId());
	    newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = newDao.save(newModel);
		return newDao.findOne(id);
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			newDao.delete(id);	
		}
	}

	@Override
	public NewModel update(NewModel updateNews) {
		NewModel oldNews = newDao.findOne(updateNews.getId());
		CategoryModel category = categoryDAO.findOneByCode(updateNews.getCategoryCode());
		updateNews.setCategoryId(category.getId());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		newDao.update(updateNews);
		return newDao.findOne(updateNews.getId());
	}

	@Override
	public List<NewModel> findAll(NewBuilder builder,Pageble pageble) {
		return newDao.findAll( builder,pageble);
	}

	@Override
	public Integer getTotalItem(NewBuilder builder) {
		// TODO Auto-generated method stub
		return newDao.getTotalItem(builder);
	}

	@Override
	public NewModel findOne(Long id) {
		NewModel model = newDao.findOne(id);
		model.setCategoryCode(categoryDAO.findOne(model.getCategoryId()).getCode());
		return model;
	}


}
