/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.marker.impl;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.core.dao.impl.hibernate.GenericEntityDaoHibernateImpl;
import com.jsdz.exchange.marker.Mark;
import com.jsdz.exchange.marker.Marker;
import com.jsdz.exchange.marker.MarkerId;

/**
 * @类名: MakerHibernateImpl
 * @说明: 标记Dao hibernate实现
 * 
 * @author leehom
 * @Date 2012-4-25 下午06:21:21
 * 
 * @see
 *
 * 修改记录：
 * 
 */
public class MarkerHibernateImpl extends GenericEntityDaoHibernateImpl<Marker, MarkerId> 
				implements Mark {

	/* (non-Javadoc)
	 * @see com.sinitech.framework.exchange.load.table.marker.Mark#setMark(com.sinitech.framework.exchange.load.table.marker.Marker)
	 */
	public Marker setMark(Marker marker) {
		this.insert(marker);
		return marker;
	}
	
	@Override
	@Transactional
	public void updateMarker(String owner, String tableName, Object value) {
		Marker m = this.getMarker(owner, tableName);
		// && maxV instanceof Long
		if (value instanceof Long) {
			Long N = (Long)value;
			m.getMarkerField().setNvalue(N);
		}		
		else // && maxV instanceof Date
		if (value instanceof Date) {
			Date D = (Date)value;
			m.getMarkerField().setDvalue(D);
		}
		else if (value instanceof String) {
			String S = (String)value;
			m.getMarkerField().setSvalue(S);
		}
		// 其他，报错
		// ...

	}
	
	@Override
	public void updateMarker(Marker marker, Object value) {		
		// && maxV instanceof Long
		if (value instanceof Long) {
			Long N = (Long)value;
			marker.getMarkerField().setNvalue(N);
		}		
		else // && maxV instanceof Date
		if (value instanceof Date) {
			Date D = (Date)value;
			marker.getMarkerField().setDvalue(D);
		}
		else if (value instanceof String) {
			String S = (String)value;
			marker.getMarkerField().setSvalue(S);
		}
		this.update(marker);
		// 其他，报错
		// ...
		
	}

	/* (non-Javadoc)
	 * @see com.sinitech.framework.exchange.load.table.marker.Mark#updateMarker(java.lang.String, java.lang.String, long)
	 */
	public void updateMarker(final String owner, final String tableName, final Object value, final Object maxV) {
		// 	&& maxV instanceof Long	
		if(value instanceof Long)
			doUpdate(owner, tableName, (Long)value, (Long)maxV);
		else // && maxV instanceof Date
		if(value instanceof Date)
			doUpdate(owner, tableName, (Date)value, (Date)maxV);
		else
		if(value instanceof String)
			doUpdate(owner, tableName, (String)value, (String)maxV);	
		//其他，报错
				
	}
	
	// 整数型标记
	private void doUpdate(final String owner, final String tableName, final Long value, final Long maxV) {
		String hql = "";
		if (maxV != null)
			hql = "update Marker m " 
		        + "set m.markerField.nvalue = :nvalue, m.maxN = :maxV "
				+ "where m.id.owner = :owner and m.id.tableName = :tableName";
		else
			hql = "update Marker m " 
		        + "set m.markerField.nvalue = :nvalue "
				+ "where m.id.owner = :owner and m.id.tableName = :tableName";
		final String hqlx = hql;
		this.getHibernateTemplate().execute((new HibernateCallback<Object>() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query q = session.createQuery(hqlx)
								.setLong("nvalue", value)
								.setString("owner", owner)
								.setString("tableName", tableName);
						if (maxV != null)
							q.setLong("maxV", maxV);
						q.executeUpdate();
						return null;
					}
				}));
	}
	
	// 日期型标记
	private void doUpdate(final String jobName, final String tableName, final Date value, final Date maxV) {
		final String hql = "update Marker " +
					"m set m.markerField.dvalue = :dvalue , m.maxD = :maxV " +
		 			"where m.id.owner = :jobName and m.id.tableName = :tableName";
		this.getHibernateTemplate().execute((new HibernateCallback<Object>() {      	 			  
    	    public Object doInHibernate(Session session)  
    	        			throws HibernateException, SQLException {  
				Query q = session.createQuery(hql)
						.setTimestamp("dvalue", value)
						.setTimestamp("maxV", maxV)
						.setString("owner", jobName)
						.setString("tableName", tableName);
				q.executeUpdate();
    	        return null;
    	    }  
    	}));
	}

	// 字符型标记
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void doUpdate(final String owner, final String tableName, final String value, final String maxV) {
		final String hql = "update Marker m " +
				"set m.markerField.svalue = :nvalue, m.maxS = :maxV " +
				"where m.id.owner = :owner and m.id.tableName = :tableName";
		getHibernateTemplate().execute(new HibernateCallback() {      	 			  
    	    public Object doInHibernate(Session session)  
    	        			throws HibernateException, SQLException {  
				Query q = session.createQuery(hql)
						.setString("nvalue", value)
						.setString("maxV", maxV)
						.setString("owner", owner)
						.setString("tableName", tableName);
				q.executeUpdate();
    	        return null;
    	    }  
    	});
	}
	
	/* (non-Javadoc)
	 * @see com.sinitech.framework.exchange.load.table.marker.Mark#getMarker(java.lang.String, java.lang.String)
	 */
	
	public Marker getMarker(String owner, String tableName) {
		return this.get(new MarkerId(owner, tableName));
	}
	
}
