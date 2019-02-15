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
import com.jsdz.digitalevidence.site.model.Site;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;
import com.jsdz.exchange.entity.ModelConsts;
import com.jsdz.exchange.entity.SiteX;
import com.jsdz.exchange.topo.Node;
import com.jsdz.exchange.writer.BaseExWriterHandler;

/**
 * 
 * @类名: SiteExWriteHandler.java
 * @说明: 采集站入库处理器
 *
 * @author: leehom
 * @Date	2017年12月19日 下午2:31:59
 * 修改记录：
 *
 * @see
 */
@Service
public class SiteExWriterHandler extends BaseExWriterHandler {

	@Override
	public Object getInEntity(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(SiteX.class))
			throw new ObjectTypeNotAcceptableException();
		SiteX siteX = (SiteX)entity;
		//
		String hql = "from com.jsdz.digitalevidence.site.model.Site o where o.siteNo = :siteCode";
		return this.getInEntity(hql, new String[] {"siteCode"}, new Object[]{siteX.getOrgCode()});

	}

	@Override
	@Transactional
	public Object writeInEntity(Object entity, Map<String, Object> refs, Node node) {
		if(!entity.getClass().isAssignableFrom(SiteX.class))
			throw new ObjectTypeNotAcceptableException();
		SiteX siteX = (SiteX)entity;
		Site site = new Site();
		siteX.assignTo(site);
		//
		Organization org = (Organization)refs.get(ModelConsts.ASSOCIATION_SITE_ORG);
		site.setOrganization(org);
		//
		this.getHibernateTemplate().save(site);
		return site;
	}

	@Override
	public Map<String, Object> getAssociation(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(SiteX.class))
			throw new ObjectTypeNotAcceptableException();
		//
		SiteX siteX = (SiteX)entity;
		//
		String hql = "from com.jsdz.admin.org.model.Organization o where o.orgCode = :orgCode";
		Object refEntity = this.getInEntity(hql, new String[] {"orgCode"}, new Object[]{siteX.getOrgCode()});
		if(refEntity!=null) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put(ModelConsts.ASSOCIATION_SITE_ORG, refEntity);
			return m;
		} 
		return null;
			
	}

	@Override
	public Object updateInEntity(Object inEntity, Object entity, Map<String, Object> refs, Node node)
			throws Exception {
		if(!entity.getClass().isAssignableFrom(SiteX.class))
			throw new ObjectTypeNotAcceptableException();
		SiteX siteX = (SiteX)entity;
		Site site = new Site();
		siteX.assignTo(site);
		//
		Organization org = (Organization)refs.get(ModelConsts.ASSOCIATION_SITE_ORG);
		site.setOrganization(org);
		//
		site.setId(((SiteX)inEntity).getId());
		//
		this.getHibernateTemplate().update(site);
		return site;
	}
	
}
