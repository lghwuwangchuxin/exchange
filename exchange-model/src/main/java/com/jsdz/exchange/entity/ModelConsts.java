/**
 * %%
 * %%
 */
package com.jsdz.exchange.entity;

/**
 * @类名: ModelConsts
 * @说明: 常量定义
 *
 * @author   leehom
 * @Date	 2017年4月27日 上午10:36:31
 * 修改记录：
 *
 * @see 	 
 */
public interface ModelConsts {
	
	public static final String SEX_MALE = "男";
	public static final String SEX_FEMALE = "女";
	
	/** 关联名称*/
	// 组织-组织
	public static final String ASSOCIATION_ORG_ORG = "Org-Org";
	// 采集站-组织
	public static final String ASSOCIATION_SITE_ORG = "WorkStation-Org";
	// 警员-组织
	public static final String ASSOCIATION_POLICE_ORG = "Police-Org";
	// 档案-警员
	public static final String ASSOCIATION_DOC_POLICE = "Documnent-Police";
	// 记录仪-警员
	public static final String ASSOCIATION_REC_POLICE = "Recorder-Police";
	// 记录仪-采集站
	public static final String ASSOCIATION_REC_SITE = "Recorder-SITE";
	// 档案-记录仪
	public static final String ASSOCIATION_DOC_RECORDER = "Documnent-Recorder";
	// 档案-执法类型
	public static final String ASSOCIATION_DOC_ENFORCE_TYPE = "Documnent-EnforceType";
}
