package com.jsdz.exchange.entity;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.digitalevidence.alarm.alarm110.model.AlarmInfo;
import com.jsdz.digitalevidence.alarm.alarm110.model.EnforceObjectType;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * @类名: AlarmInfo
 * @说明: 警情
 *
 * @author   leehom
 * @Date	 2017年8月29日 上午19:11:05
 * 修改记录：
 *
 * @see 	 
 */
public class AlarmInfoX extends ExchangeEntity {
	
	private Long id;    //ID
    private String alarmCode;    //警情号
    private String alarmName;    //报警人姓名
    private String alarmSum;    //报警摘要
    private String orgCode;    // 处警单位编号
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
		if(!AlarmInfo.class.isAssignableFrom(from.getClass()))
			throw new ObjectTypeNotAcceptableException();
		AlarmInfo alarmInfo = (AlarmInfo)from;
		this.setId(alarmInfo.getId());
		this.setAlarmCode(alarmInfo.getAlarmCode());
		this.setAlarmName(alarmInfo.getAlarmName());
		this.setAlarmSum(alarmInfo.getAlarmContext());		
		this.setOrgCode(alarmInfo.getReveiceOrg().getOrgCode());
		this.setEnforceObjType(alarmInfo.getEnforceObjType());
		this.setEnforceObjName(alarmInfo.getEnforceObjName());
		this.setEnforceObjIdNum(alarmInfo.getEnforceObjIdNum());
		// remark
	}
		
	@Override
	public Object assignTo(Object to) {
		if(!AlarmInfo.class.isAssignableFrom(to.getClass()))
			throw new ObjectTypeNotAcceptableException();
		AlarmInfo alarmInfo = (AlarmInfo)to;		
		alarmInfo.setId(this.getId());
		alarmInfo.setAlarmCode(this.getAlarmCode());
		alarmInfo.setAlarmName(this.getAlarmName());
		alarmInfo.setAlarmContext(this.getAlarmSum());		
		Organization org = new Organization();
		org.setOrgCode(this.getOrgCode());
		alarmInfo.setReveiceOrg(org);
		alarmInfo.setEnforceObjType(this.getEnforceObjType());
		alarmInfo.setEnforceObjName(this.getEnforceObjName());
		alarmInfo.setEnforceObjIdNum(this.getEnforceObjIdNum());
		// remark		
		return alarmInfo;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAlarmCode() {
		return alarmCode;
	}
	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public String getAlarmSum() {
		return alarmSum;
	}
	public void setAlarmSum(String alarmSum) {
		this.alarmSum = alarmSum;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
