package com.jsdz.exchange.entity;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * 
 * @类名: Organization
 * @说明: 组织机构
 *
 * @author: leehom
 * @Date	2017年4月24日
 * 修改记录：
 *
 * @see
 */
public class OrganizationX extends ExchangeEntity {
	
	/** ID*/
	private Long id;
	/** 机构编号*/
	private String orgCode;
	/** 上级机构编号*/
	private String pOrgCode; 
	/** 机构名称*/
	private String orgName;
	/** 备注*/
	private String remark;

	@Override
	public void assignFrom(Object from) {
		if(!from.getClass().isAssignableFrom(Organization.class))
			throw new ObjectTypeNotAcceptableException();
		Organization org = (Organization)from;
		this.setId(org.getId());
		this.setOrgCode(org.getOrgCode());
		this.setOrgName(org.getOrgName());
		Organization pOrg = org.getParentOrg();
		if(pOrg!=null)
			this.setpOrgCode(pOrg.getOrgCode());
		// this.setRemark();
	}
	
	@Override
	public Object assignTo(Object to) {
		if(!to.getClass().isAssignableFrom(Organization.class))
			throw new ObjectTypeNotAcceptableException();
		Organization org = (Organization)to;
		org.setId(this.getId());
		org.setOrgCode(this.getOrgCode());
		org.setOrgName(this.getOrgName());
		// org.setOrgType(orgType);
		Organization pOrg = new Organization();
		pOrg.setOrgCode(this.getpOrgCode());
		org.setParentOrg(pOrg);
		return org;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getpOrgCode() {
		return pOrgCode;
	}
	public void setpOrgCode(String pOrgCode) {
		this.pOrgCode = pOrgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
