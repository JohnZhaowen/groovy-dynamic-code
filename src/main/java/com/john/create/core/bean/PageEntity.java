package com.john.create.core.bean;

import java.util.List;

public class PageEntity<T> {
	
	private long count;
	private List list;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
}
