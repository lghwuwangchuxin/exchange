/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.writer.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.digitalevidence.site.model.Platform;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;
import com.jsdz.exchange.entity.ModelConsts;
import com.jsdz.exchange.entity.OrganizationX;
import com.jsdz.exchange.topo.Node;
import com.jsdz.exchange.writer.BaseExWriterHandler;

/**
 * 
 * @类名: DocumentExWriteHandler.java
 * @说明: 执法档案入库处理器
 *
 * @author: leehom
 * @Date	2017年12月19日 下午2:31:59
 * 修改记录：
 *
 * @see
 */
public class OrgExWriterHandler extends BaseExWriterHandler {
	
	// TODO
	//   参数化
	@Autowired
	private Platform platform;

	@Override
	public Object getInEntity(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(OrganizationX.class))
			throw new ObjectTypeNotAcceptableException();
		OrganizationX orgx = (OrganizationX)entity;
		//
		String hql = "from com.jsdz.admin.org.model.Organization o where o.orgCode = :orgCode";
		return this.getInEntity(hql, new String[] {"orgCode"}, new Object[]{orgx.getOrgCode()});

	}

	@Override
	@Transactional
	public Object writeInEntity(Object entity, Map<String, Object> refs, Node node) {
		if(!entity.getClass().isAssignableFrom(OrganizationX.class))
			throw new ObjectTypeNotAcceptableException();
		OrganizationX orgx = (OrganizationX)entity;
		Organization org = new Organization();
		orgx.assignTo(org);		
		// 关联
		// 上级组织
		Organization pOrg = (Organization)refs.get(ModelConsts.ASSOCIATION_ORG_ORG);
		if(pOrg!=null) {
			this.getHibernateTemplate().merge(pOrg);
		}
		org.setParentOrg(pOrg);
		this.getHibernateTemplate().save(org);
		// 路径
		String path = null;
		if (pOrg == null){
			path = org.getId().toString() + "/";
		}else{			
			path = pOrg.getPath() + org.getId().toString() + "/";
		}
		org.setPath(path);
		//
		this.getHibernateTemplate().save(org);
		// 关联平台
		platform = this.getHibernateTemplate().merge(platform);
		// TODO 多线程/分布式保护
		platform.getOrgs().add(org);
		this.getHibernateTemplate().update(platform);
		return org;
	}

	@Override
	public Map<String, Object> getAssociation(Object entity, Node node) {
		if(!entity.getClass().isAssignableFrom(OrganizationX.class))
			throw new ObjectTypeNotAcceptableException();
		OrganizationX orgx = (OrganizationX)entity;		
		// pOrgCode不为空
		if(orgx.getpOrgCode()!=null) {
			Map<String, Object> m = new HashMap<String, Object>();
			String hql = "from com.jsdz.admin.org.model.Organization o where o.orgCode = :orgCode";
			Object refEntity = this.getInEntity(hql, new String[] {"orgCode"}, new Object[]{orgx.getpOrgCode()});
			if(refEntity!=null) {			
				m.put(ModelConsts.ASSOCIATION_ORG_ORG, refEntity);
				return m;
			} 
			return null;
		}
		return new HashMap<String, Object>();
	}

	@Override
	public Object updateInEntity(Object inEntity, Object entity, Map<String, Object> refs, Node node)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
