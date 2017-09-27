package com.sy.modules.common;
import java.util.List;


import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T, Pk, D extends ParentDao<T, Pk>> {
	
	protected abstract D getDao();

	@Transactional(readOnly = true)
	public T findById(Pk id) {
		return getDao().findById(id);
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		return getDao().getAll();
	}

	@Transactional(rollbackFor = { java.lang.RuntimeException.class,
			java.lang.Exception.class })
	public void create(T obj) {
		getDao().create(obj);
	}

	@Transactional(rollbackFor = { java.lang.RuntimeException.class,
			java.lang.Exception.class })
	public void update(T obj) {
		getDao().update(obj);
	}

	@Transactional(rollbackFor = { java.lang.RuntimeException.class,
			java.lang.Exception.class })
	public void delete(Pk id) {
		getDao().deleteById(id);
	}

	@Transactional(readOnly = true)
	public long count() {
		return getDao().count();
	}

}
