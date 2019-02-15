/**
 * %%
 * %%
 */
package com.jsdz.exchange.topo.chain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jsdz.exchange.topo.EntityTopology;
import com.jsdz.exchange.topo.Node;

/**
 * @类名: ChainDataTopology
 * @说明: 链式数据拓扑
 *
 * @author   leehom
 * @Date	 2017年9月5日 下午6:43:10
 * 修改记录：
 *
 * @see 	 
 */
public class ChainEntityTopology implements EntityTopology {
	
	/** 拓扑标识，唯一*/
	private String key;
	/** 实体链*/
	private List<Node> nodes = new ArrayList<Node>();	
	
	public ChainEntityTopology() {
		super();
	}

	public ChainEntityTopology(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Node getHeaderNode() {
		return nodes.get(0);
	}
	
	@Override
	public Node getNode(String nodeName) {
		for(Node node : nodes) {
			if(node.getName().equals(nodeName))
				return node;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getNextNodes(Node node) {
		int i = nodes.indexOf(node);
		if(nodes.size()<i+1+1)
			return Collections.EMPTY_LIST;
		Node next = nodes.get(i+1);
		return Arrays.asList(next);
	}
	
	@Override
	public List<Node> getNodeLinks(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

}
