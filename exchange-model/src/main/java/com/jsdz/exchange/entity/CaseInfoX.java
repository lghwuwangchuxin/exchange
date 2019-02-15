package com.jsdz.exchange.entity;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.digitalevidence.alarm.alarm110.model.AlarmInfo;
import com.jsdz.digitalevidence.alarm.alarm110.model.Case;
import com.jsdz.digitalevidence.alarm.alarm110.model.EnforceObjectType;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * @类名: CaseInfo
 * @说明: 案件信息
 *
 * @author   leehom
 * @Date	 2017年4月21日 下午5:02:41
 * 修改记录：
 *
 * @see 	 
 */
public class CaseInfoX extends ExchangeEntity {
	
	/** id*/
	private Long id;
	/** 案件编号*/
	private String caseCode;
	/** 办案单位*/
	private String orgCode;
	/** 案件名称*/
	private String caseName;
	/** 案件摘要*/
	private String caseSum;
	/** 关联警情编号*/
	private String alarmCode;
    /** 执法对象类型*/
    private EnforceObjectType enforceObjType;
    /** 执法对象名称*/
    private String enforceObjName;
    /** 执法对象身份证件号*/
    private String enforceObjIdNum;
    /** 备注*/
    private String remark;
    
    @Override
	public void assignFrom(Object from) {
		if(!from.getClass().isAssignableFrom(Case.class))
			throw new ObjectTypeNotAcceptableException();
		Case caseInfo = (Case)from;
		this.setId(caseInfo.getId());
		this.setCaseCode(caseInfo.getCaseCode());
		this.setOrgCode(caseInfo.getHandleOrg().getOrgCode());
		this.setCaseName(caseInfo.getCaseName());
		this.setCaseSum(caseInfo.getCaseSum());
		this.setAlarmCode(caseInfo.getAlarmInfo().getAlarmCode());
		this.setEnforceObjType(caseInfo.getEnforceObjType());
		this.setEnforceObjName(caseInfo.getEnforceObjName());
		this.setEnforceObjIdNum(caseInfo.getEnforceObjIdNum());
		// remark
	}
	
	@Override
	public Object assignTo(Object to) {
		if(!to.getClass().isAssignableFrom(Case.class))
			throw new ObjectTypeNotAcceptableException();
		Case caseInfo = (Case)to;
		caseInfo.setId(this.getId());
		caseInfo.setCaseCode(this.getCaseCode());
		Organization org = new Organization();
		org.setOrgCode(this.getOrgCode());
		caseInfo.setHandleOrg(org);
		caseInfo.setCaseName(this.getCaseName());
		caseInfo.setCaseSum(this.getCaseSum());
		AlarmInfo alarmInfo = new AlarmInfo();
		alarmInfo.setAlarmCode(this.getAlarmCode());
		caseInfo.setAlarmInfo(alarmInfo);
		caseInfo.setEnforceObjType(this.getEnforceObjType());
		caseInfo.setEnforceObjName(this.getEnforceObjName());
		caseInfo.setEnforceObjIdNum(this.getEnforceObjIdNum());
		// remark		
		return caseInfo;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseSum() {
		return caseSum;
	}
	public void setCaseSum(String caseSum) {
		this.caseSum = caseSum;
	}
	public String getAlarmCode() {
		return alarmCode;
	}
	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	public String getEnforceObjName() {
		return enforceObjName;
	}
	public void setEnforceObjName(String enforceObjName) {
		this.enforceObjName = enforceObjName;
	}
	public String getEnforceObjIdNum() {
		return enforceObjIdNum;
	}
	public void setEnforceObjIdNum(String enforceObjIdNum) {
		this.enforceObjIdNum = enforceObjIdNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public EnforceObjectType getEnforceObjType() {
		return enforceObjType;
	}

	public void setEnforceObjType(EnforceObjectType enforceObjType) {
		this.enforceObjType = enforceObjType;
	}
	
}
