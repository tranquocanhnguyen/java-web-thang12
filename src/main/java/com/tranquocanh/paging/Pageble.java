package com.tranquocanh.paging;

import com.tranquocanh.sort.Sorter;

public interface Pageble {

	Integer getPage() ;
	Integer getOffset();
	Integer getLimit();
	Sorter  getSorter();
}
