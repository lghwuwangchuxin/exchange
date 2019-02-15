/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.writer.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.admin.security.model.Employees;
import com.jsdz.digitalevidence.document.model.ContentTypeDocClassMapper;
import com.jsdz.digitalevidence.document.model.Document;
import com.jsdz.digitalevidence.document.model.EnforceType;
import com.jsdz.digitalevidence.site.model.Recorder;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;
import com.jsdz.exchange.entity.DocumentX;
import com.jsdz.exchange.entity.ModelConsts;
import com.jsdz.exchange.topo.Node;
import com.jsdz.exchange.writer.BaseExWriterHandler;

/**
 * 
 * @类名: DocumentExWriterHandler.java
 * @说明: 文档入库处理器
 *
 * @author: leehom
 * @Date	2017年12月19日 下午2:31:59
 * 修改记录：
 *
 * @see
 */
public class DocumentExWriterHandler extends BaseExWriterHandler {

	@Autowired
	private ContentTypeDocClassMapper docTypeMapper; 
	
	@Override
	public Object getInEntity(Object entity, Node node) {
		//
		if(!entity.getClass().isAssignableFrom(DocumentX.class))
			throw new ObjectTypeNotAcceptableException();
		DocumentX docX = (DocumentX)entity;
		//
		String hql = "from com.jsdz.digitalevidence.document.model.Document o where o.originalDocId = :originalDocId ";
		return this.getInEntity(hql, new String[] {"originalDocId"}, new Object[]{docX.getId()});

	}

	@Override
	@Transactional
	public Object writeInEntity(Object entity, Map<String, Object> refs, Node node) throws Exception {
		if(!entity.getClass().isAssignableFrom(DocumentX.class))
			throw new ObjectTypeNotAcceptableException();
		DocumentX docX = (DocumentX)entity;
		
		Class<Document> clazz = docTypeMapper.getClazzOfContentType(docX.getType().toLowerCase());
		Document doc;
		doc = clazz.newInstance();
		docX.assignTo(doc);
		// 关联
		// 警员
		Employees emp = (Employees)refs.get(ModelConsts.ASSOCIATION_DOC_POLICE);		
		this.getHibernateTemplate().merge(emp);
		doc.setPolice(emp);
		// 记录仪
		Recorder rec = (Recorder)refs.get(ModelConsts.ASSOCIATION_DOC_RECORDER);	
		if(rec!=null)
			this.getHibernateTemplate().merge(rec);
		doc.setRec(rec);
		// 执法类型, 不是必需
		EnforceType enforceType = (EnforceType)refs.get(ModelConsts.ASSOCIATION_DOC_ENFORCE_TYPE);	
		if(enforceType!=null) {
			this.getHibernateTemplate().merge(enforceType);
		}
		doc.setEnforceType(enforceType);
		//
		this.getHibernateTemplate().save(doc);
		return doc;
	}

	@Override
	public Map<String, Object> getAssociation(Object entity, Node node) {
		// 
		if(!entity.getClass().isAssignableFrom(DocumentX.class))
			throw new ObjectTypeNotAcceptableException();
		DocumentX docX = (DocumentX)entity;
		// 警员
		String hql1 = "from com.jsdz.admin.security.model.Employees o where o.workNumber = :policeCode ";
		Object refPolice = this.getInEntity(hql1, new String[] {"policeCode"}, new Object[]{docX.getPoliceCode()});
		// 记录仪
		String hql2 = "from com.jsdz.digitalevidence.site.model.Recorder o where o.code = :recCode ";
		Object refRec = this.getInEntity(hql2, new String[] {"recCode"}, new Object[]{docX.getRecCode()});
		//
		if(refPolice!=null) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put(ModelConsts.ASSOCIATION_DOC_POLICE, refPolice);
			m.put(ModelConsts.ASSOCIATION_DOC_RECORDER, refRec);
			return m;
		} 
		// 执法类型
		// TODO 需数据字典支持，转换
		/*String hql3 = "from com.jsdz.digitalevidence.document.model.EnforceType o where o.code = :enforceTypeCode ";
		Object enforceType = this.getInEntity(hql2, new String[] {"enforceTypeCode"}, new Object[]{docX.getEnforceType()});*/		
		return null;
			
	}

	@Override
	public Object updateInEntity(Object inEntity, Object entity, Map<String, Object> refs, Node node)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
