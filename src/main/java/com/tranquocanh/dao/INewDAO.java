package com.tranquocanh.dao;

import java.util.List;

import com.tranquocanh.builder.NewBuilder;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{
	
	List<NewModel> findByCategoryId(Long categoryId) ;
	Long save(NewModel newModel);
	void delete(Long id);
	NewModel findOne(Long id);
	void update(NewModel updateNews);
	List<NewModel> findAll(NewBuilder builder, Pageble pageble);
	Integer getTotalItem(NewBuilder builder);
}
