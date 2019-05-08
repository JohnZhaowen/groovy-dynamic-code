package com.john.create.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.john.create.entity.Articles;

public class BaseDao {
	
	public void save(Object obj) {}
	public void delete(Object obj) {}
	public void update(Object obj) {}
	
	public List query(String hql, Map params, int page, int pageSize) { return new ArrayList();}
	public long count(String hql, Map params) {return 0;}
	
	public Object get(Class clazz, long id) {return new Object();};
	
	

}
