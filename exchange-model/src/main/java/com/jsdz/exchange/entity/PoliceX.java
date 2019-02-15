package com.jsdz.exchange.entity;

import java.util.Date;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.admin.security.model.Employees;
import com.jsdz.digitalevidence.site.model.Site;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * 
 * @类名: Police
 * @说明: 警员
 *
 * @author: leehom
 * @Date	2017年4月26日上午11:47:10
 * 修改记录：
 *
 * @see
 * 
 * TODO
 *   *. 类型对照
 *   
 */
public class PoliceX extends ExchangeEntity {
	
	private Long id;
	/** 警号*/
	private String policeCode;
	/** 姓名*/
	private String policeName;
	/** 所属机构编号*/
	private String orgCode;
	/** 性别*/
	private Sex sex;
	/** 生日*/
	private Date birthDate;
	/** 证件证*/
	private String idNum;
	/** 联系电话*/
	private String tel;    
	/** */
	private String department;
	/** 职位*/
	private String position;
	/** 备注*/
	private String remark;
	
	@Override
	public void assignFrom(Object from) {
		if(!from.getClass().isAssignableFrom(Employees.class))
			throw new ObjectTypeNotAcceptableException();
		Employees emp = (Employees)from;
		this.setId(emp.getId());
		this.setPoliceCode(emp.getWorkNumber());
		this.setPoliceName(emp.getName());
		this.setOrgCode(emp.getOrganization().getOrgCode());
		String sexStr = emp.getMale();
		if(sexStr!=null) {
			if(ModelConsts.SEX_MALE.equals(sexStr))
				this.setSex(Sex.MALE);
			if(ModelConsts.SEX_FEMALE.equals(sexStr))
				this.setSex(Sex.FEMALE);
		}
		this.setBirthDate(emp.getBirthDate());
		this.setIdNum(emp.getIdNum());
		this.setTel(emp.getMobile());
		
	}
	
	@Override
	public Object assignTo(Object to) {
		if(!to.getClass().isAssignableFrom(Employees.class))
			throw new ObjectTypeNotAcceptableException();
		Employees emp = (Employees)to;
		emp.setId(this.getId());
		emp.setWorkNumber(this.getPoliceCode());
		emp.setName(this.getPoliceName());
		Organization org = new Organization();
		org.setOrgCode(this.getOrgCode());
		emp.setOrganization(org);
		Sex sex = this.getSex();
		if(sex!=null) {
			emp.setMale(sex==Sex.MALE ? ModelConsts.SEX_MALE : ModelConsts.SEX_FEMALE);
	
		}
		emp.setBirthDate(this.getBirthDate());
		emp.setIdNum(this.getIdNum());
		emp.setMobile(this.getTel());
		// state
		// remark		
		return emp;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPoliceCode() {
		return policeCode;
	}
	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}
	public String getPoliceName() {
		return policeName;
	}
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
