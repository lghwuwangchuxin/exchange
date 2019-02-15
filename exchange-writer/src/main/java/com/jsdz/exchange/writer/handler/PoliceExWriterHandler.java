/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.writer.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.admin.security.model.Employees;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;
import com.jsdz.exchange.entity.ModelConsts;
import com.jsdz.exchange.entity.PoliceX;
import com.jsdz.exchange.entity.SiteX;
import com.jsdz.exchange.topo.Node;
import com.jsdz.exchange.writer.BaseExWriterHandler;

/**
 * 
 * @类名: PoliceExWriterHandler.java
 * @说明: 警员入库处理器
 *
 * @author: leehom
 * @Date	2017年12月19日 下午2:31:59
 * 修改记录：
 *
 * @see
 */
public class PoliceExWriterHandler extends BaseExWriterHandler {

	@Override
	public Object getInEntity(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(PoliceX.class))
			throw new ObjectTypeNotAcceptableException();
		PoliceX policeX = (PoliceX)entity;
		//
		String hql = "from com.jsdz.admin.security.model.Employees o where o.workNumber = :policeCode";
		return this.getInEntity(hql, new String[] {"policeCode"}, new Object[]{policeX.getPoliceCode()});

	}

	@Override
	@Transactional
	public Object writeInEntity(Object entity, Map<String, Object> refs, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(PoliceX.class))
			throw new ObjectTypeNotAcceptableException();
		PoliceX policeX = (PoliceX)entity;
		Employees police = new Employees();
		policeX.assignTo(police);
		//
		Organization org = (Organization)refs.get(ModelConsts.ASSOCIATION_POLICE_ORG);		
		this.getHibernateTemplate().merge(org);
		police.setOrganization(org);
		//
		this.getHibernateTemplate().save(police);
		return police;
	}

	@Override
	public Map<String, Object> getAssociation(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(PoliceX.class))
			throw new ObjectTypeNotAcceptableException();
		PoliceX policeX = (PoliceX)entity;
		//
		String hql = "from com.jsdz.admin.org.model.Organization o where o.orgCode = :orgCode";
		Object refEntity = this.getInEntity(hql, new String[] {"orgCode"}, new Object[]{policeX.getOrgCode()});
		if(refEntity!=null) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put(ModelConsts.ASSOCIATION_POLICE_ORG, refEntity);
			return m;
		} 
		return null;
			
	}

	@Override
	public Object updateInEntity(Object inEntity, Object entity, Map<String, Object> refs, Node node)
			throws Exception {
		PoliceX policeX = (PoliceX)entity;
		Employees police = new Employees();
		policeX.assignTo(police);
		//
		Organization org = (Organization)refs.get(ModelConsts.ASSOCIATION_POLICE_ORG);		
		this.getHibernateTemplate().merge(org);
		police.setOrganization(org);
		//
		police.setId(((Employees)inEntity).getId());
		//
		this.getHibernateTemplate().update(police);
		return police;
	}
	
}
