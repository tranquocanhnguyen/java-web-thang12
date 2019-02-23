package com.tranquocanh.service;

import java.util.List;

import com.tranquocanh.builder.NewBuilder;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.paging.Pageble;

public interface INewService {

	List<NewModel> findbyCategoryId(Long categoryId);
	NewModel save(NewModel newModel);
	void delete(Long[] ids);
	NewModel update(NewModel updateNews);
	List<NewModel> findAll(NewBuilder builder, Pageble pageble);
	Integer getTotalItem();
	NewModel findOne(Long id);
}				
