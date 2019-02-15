/**
 * %%
 * %%
 */
package com.jsdz.exchange.topo;

import java.util.List;

/**
 * @类名: DataTopology
 * @说明: 数据拓扑(依赖)
 *
 * @author   leehom
 * @Date	 2017年9月5日 下午6:41:37
 * 修改记录：
 *
 * @see 	 
 */
public interface EntityTopology {
	
	/**
	 * @说明：获取规则拓扑唯一标识
	 *
	 * @author leehom
	 * @return
	 * 
	 */
	public String getKey();
	
	/**
	 * @说明：获取拓扑起始节点/后续节点
	 *
	 * @author leehom
	 * @return
	 * 
	 */
	public Node getHeaderNode();
	public Node getNode(String nodeName);
	public List<Node> getNextNodes(Node node);
	// 
	public List<Node> getNodeLinks(Node node);

}
