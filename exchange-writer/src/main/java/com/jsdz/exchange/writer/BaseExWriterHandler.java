package com.jsdz.exchange.writer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jsdz.core.dao.impl.hibernate.GenericEntityDaoHibernateImpl;

/**
 * 
 * @类名: ExWriteDaoImpl
 * @说明: 入库Dao实现
 *
 * @author leehom
 * @Date 2017年4月13日 下午8:09:15 
 * 
 * 修改记录：
 *
 * @see
 */
@SuppressWarnings("rawtypes")
public abstract class BaseExWriterHandler extends GenericEntityDaoHibernateImpl implements ExWriterHandler {
	
	public Object getInEntity(String hql, String[] paramNames, Object[] paramValues) {
		//
		Object data = getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(hql);
				// 设置参数
				for(int i=0;i<paramNames.length;i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], paramValues[i]);
				}
				//
				return queryObject.uniqueResult();
			}
		});
		return data;
	}


}
