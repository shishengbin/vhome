package com.sy.modules.common;

import java.util.List;


/**
 *通用接口
 * @author sss 2013-8-9
 */
public interface ParentDao<T extends Object,Pk> 
{
	//insert an object into db
	public void create(T obj);

	//update an object from db
	public void update(T obj);
    
	//delete an object by  id from db
	public void deleteById(Pk id);
	
	//find an object by  id  from db
	public T findById(Pk id);
	
	//find all objects from db
	public List<T> getAll();
	
	//find total number from db
	public long count();
}
