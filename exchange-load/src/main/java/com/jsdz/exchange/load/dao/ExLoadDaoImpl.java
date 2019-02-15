package com.jsdz.exchange.load.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jsdz.core.dao.impl.hibernate.GenericEntityDaoHibernateImpl;
import com.jsdz.exchange.ExLoadConsts;
import com.jsdz.exchange.marker.Marker;

/**
 * 
 * @类名: ExLoadDaoImpl
 * @说明: ExLoadDao实现类
 *
 * @author leehom
 * @Date 2017年4月13日 下午8:09:15 
 * 
 * 修改记录：
 *
 * @see
 */
public class ExLoadDaoImpl extends GenericEntityDaoHibernateImpl implements ExLoadDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> loadInDataByMarker(String hql, Class clazz, Marker marker) {
		//
		List<Object> data = getHibernateTemplate().execute(new HibernateCallback<List<Object>>() {
			public List<Object> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(hql);
				// 设置参数
				// 标记当前值，批大少
				applyNamedParameterToQuery(queryObject, ExLoadConsts.PARAM_CUR_VALUE, marker.getCurValue());
				// 分页
				queryObject.setFirstResult(0);
				queryObject.setMaxResults(marker.getBatchSize());
				//
				return queryObject.list();
			}
		});
		return data;
	}
	
	@Override
	public Object loadOutData(Object entity, Class clazz) {
		this.getHibernateTemplate().save(clazz.getName(), entity);
		return entity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> loadOutDataBatch(List entities, Class clazz) {
		getHibernateTemplate().execute(
	    		new HibernateCallback() {  
		            @Override  
		            public Integer doInHibernate(Session session) throws HibernateException, SQLException {                  
				        for (int i=0;i<entities.size();i++) {  
				        	session.save(entities.get(i));         				          		          
				        }      
				        session.flush();  
			            session.clear();  
				        return entities.size();
			        }  
			    });  
		return entities;
	}

}
