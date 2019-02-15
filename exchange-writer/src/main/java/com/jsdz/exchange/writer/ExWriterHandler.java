/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.writer;

import java.util.Map;

import com.jsdz.exchange.topo.Node;

/**
 * 
 * @类名: ExWriterHandler.java
 * @说明: 交换入库
 *
 * @author: leehom
 * @Date	2017年12月15日 下午4:49:38
 * 修改记录：
 *
 * @see
 * 
 *   
 */
public interface ExWriterHandler {
	
	/**
	 * @说明：获取实体
	 * @Author leehom
	 * @param entity 中间库实体
	 * @param node
	 * @return  入库实体
	 */
	public Object getInEntity(Object entity, Node node);
	
	/**
	 * @说明：获取关联实体
	 * @Author leehom
	 * @param entity 中间库实体
	 * @param node
	 * @return 入库对应实体关联实体
	 */
	public Map<String, Object> getAssociation(Object entity, Node node);
			
	/**
	 * @说明：入库
	 * @Author leehom
	 * @param entity
	 * @param refs 关联实体
	 * @param node
	 * @return
	 * @throws Exception 
	 */
	public Object writeInEntity(Object entity, Map<String, Object> refs, Node node) throws Exception;
	public Object updateInEntity(Object inEntity, Object entity, Map<String, Object> refs, Node node) throws Exception;
	
}
