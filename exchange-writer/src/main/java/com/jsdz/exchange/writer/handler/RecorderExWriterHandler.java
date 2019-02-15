/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.writer.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.jsdz.admin.security.model.Employees;
import com.jsdz.digitalevidence.site.model.Recorder;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;
import com.jsdz.exchange.entity.ModelConsts;
import com.jsdz.exchange.entity.RecorderX;
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
public class RecorderExWriterHandler extends BaseExWriterHandler {

	@Override
	public Object getInEntity(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(RecorderX.class))
			throw new ObjectTypeNotAcceptableException();
		RecorderX recorderX = (RecorderX)entity;
		//
		String hql = "from com.jsdz.digitalevidence.site.model.Recorder o where o.code = :recCode";
		return this.getInEntity(hql, new String[] {"recCode"}, new Object[]{recorderX.getRecCode()});

	}

	@Override
	@Transactional
	public Object writeInEntity(Object entity, Map<String, Object> refs, Node node) {
		if(!entity.getClass().isAssignableFrom(RecorderX.class))
			throw new ObjectTypeNotAcceptableException();
		RecorderX recorderX = (RecorderX)entity;
		Recorder recorder = new Recorder();
		recorderX.assignTo(recorder);
		//
		Employees police = (Employees)refs.get(ModelConsts.ASSOCIATION_REC_POLICE);
		recorder.setPolice(police);
		//
		this.getHibernateTemplate().save(recorder);
		return recorder;
	}

	@Override
	public Map<String, Object> getAssociation(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(RecorderX.class))
			throw new ObjectTypeNotAcceptableException();
		//
		RecorderX recorderX = (RecorderX)entity;
		// 警员
		String hql = "from com.jsdz.admin.security.model.Employees o where o.workNumber = :policeCode";
		Object refEntity = this.getInEntity(hql, new String[] {"policeCode"}, new Object[]{recorderX.getPoliceCode()});
		// 采集站
		String hql2 = "from com.jsdz.digitalevidence.site.model.Site o where o.siteNo = :siteCode";
		Object refEntity2 = this.getInEntity(hql2, new String[] {"siteCode"}, new Object[]{recorderX.getSiteCode()});
		if(refEntity!=null) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put(ModelConsts.ASSOCIATION_REC_POLICE, refEntity);
			m.put(ModelConsts.ASSOCIATION_REC_SITE, refEntity2);
			return m;
		} 
		return null;
			
	}

	@Override
	public Object updateInEntity(Object inEntity, Object entity, Map<String, Object> refs, Node node)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
