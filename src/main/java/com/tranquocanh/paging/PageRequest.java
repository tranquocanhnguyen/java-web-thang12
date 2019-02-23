package com.tranquocanh.paging;

import com.tranquocanh.sort.Sorter;

public class PageRequest implements Pageble{

	private Integer page;
	private Integer maxpageItem;
	private Sorter sorter;
	public PageRequest(Integer page, Integer maxpageItem, Sorter sorter) {
		this.page = page;
		this.maxpageItem = maxpageItem;
		this.sorter = sorter;
	}
	
	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}

	@Override
	public Integer getOffset() {
		// TODO Auto-generated method stub
		if(this.page != null && this.maxpageItem != null) {
			return   (this.page -1) * this.maxpageItem;	
		}
		return  null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return this.maxpageItem;
	}

	@Override
	public Sorter getSorter() {
		// TODO Auto-generated method stub
		if(this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

	

	
}
