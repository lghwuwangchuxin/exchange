package com.jsdz.exchange.entity;

import com.jsdz.digitalevidence.alarm.alarm110.model.AlarmInfo;
import com.jsdz.digitalevidence.alarm.alarm110.model.AlarmRelation;
import com.jsdz.digitalevidence.document.model.Document;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * @类名: AlarmDocument.java
 * @说明: 警情档案
 *
 * @author: leehom
 * @Date	2017年12月15日 上午11:39:11
 * 修改记录：
 *
 * @see
 */
public class AlarmDocumentX extends ExchangeEntity {
	
	private Long id;
	private Long docId;
	private String alarmCode;
    private String remark;
    
    @Override
	public void assignFrom(Object from) {
		if(!AlarmRelation.class.isAssignableFrom(from.getClass()))
			throw new ObjectTypeNotAcceptableException();
		AlarmRelation alarmDoc = (AlarmRelation)from;
		this.setId(docId);
		this.setDocId(alarmDoc.getMediaInfo().getId());
		this.setAlarmCode(alarmDoc.getAlarmInfo().getAlarmCode());
		// remark
	}
		
	@Override
	public Object assignTo(Object to) {
		if(!AlarmRelation.class.isAssignableFrom(to.getClass()))
			throw new ObjectTypeNotAcceptableException();
		AlarmRelation alarmDoc = (AlarmRelation)to;
		//
		alarmDoc.setId(this.getId());
		Document doc = new Document();
		doc.setOriginalDocId(this.getDocId());
		alarmDoc.setMediaInfo(doc);
		AlarmInfo alarmInfo = new AlarmInfo();
		alarmInfo.setAlarmCode(this.getAlarmCode());
		alarmDoc.setAlarmInfo(alarmInfo);
		// remark		
		return alarmDoc;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public String getAlarmCode() {
		return alarmCode;
	}
	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
