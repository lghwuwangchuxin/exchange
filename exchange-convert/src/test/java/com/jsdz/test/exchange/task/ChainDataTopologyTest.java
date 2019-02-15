package com.jsdz.test.exchange.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.exchange.load.entity.DocumentX;
import com.jsdz.exchange.load.entity.OrganizationX;
import com.jsdz.exchange.load.entity.SiteX;
import com.jsdz.exchange.verify.dao.ChainDataTopologyDao;
import com.jsdz.exchange.verify.dao.NodeDao;
import com.jsdz.exchange.verify.topo.Node;
import com.jsdz.exchange.verify.topo.chain.ChainDataTopology;
import com.jsdz.serializer.Serializer;
import com.jsdz.serializer.SeriallizeException;

/**
 * @类名: ChainDataTopologyTest
 * @说明: 链式数据拓扑测试
 *
 * @author   leehom
 * @Date	 2017年9月7日 上午10:36:44
 * 修改记录：
 *
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations = { 
		"/testApplicationContext-common.xml", 
		"/testApplicationContext-topo.xml",
		"/testApplicationContext-scheduler.xml"})
public class ChainDataTopologyTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private NodeDao nodeDao;
	@Autowired
	private ChainDataTopologyDao topoDao;
    
	/**
	 * @说明：
	 * 
	 * @author leehom
	 * @throws java.lang.Exception
	 * 
	 * @异常：
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @说明：
	 * 
	 * @author leehom
	 * @throws java.lang.Exception
	 * 
	 * @异常：
	 */
	@After
	public void tearDown() throws Exception {
	}

	// 拓扑节点
	@Test
	@Transactional
	public void testCreateNode() {
		
	}
	
	@Autowired
	private Serializer ser;
	// 构建拓扑
	@Test
	public void testCreatTopology() throws SeriallizeException {
		//
		Node n1 = new Node();
		n1.setClazz(OrganizationX.class);
		n1.setName("org");
		//
		Node n2 = new Node();
		n2.setClazz(SiteX.class);
		n2.setName("work station");
		//
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		//
		ChainDataTopology topo = new ChainDataTopology();
		topo.setKey("PIS-CCTC");
		topo.setNodes(nodes);
		//
		byte[] bytes = ser.Marshal(topo);
		String xml = new String(bytes);
		System.out.println(xml);
	}
	
	// 删除节点
	@Test
	@Transactional
	public void testDelNode() {
		//
		Node node = nodeDao.get(4L);
		nodeDao.delete(node);
	}
	
	// 增加节点到拓扑
	@Test
	@Transactional
	public void testAddRuleToTopo() {
		//
		Node node = new Node();
		node.setClazz(DocumentX.class);
		//
		nodeDao.saveOrUpdate(node);
		//
		ChainDataTopology topo = topoDao.get("rule1");
		//
		topo.setNodes(Arrays.asList(node));
		
	}
	
	// 获取拓扑
	@Test
	@Transactional
	public void testGetTopo() {
		//
		ChainDataTopology topo = topoDao.get("PIS");
		//
		List<Node> nodes = topo.getNodes();
		for(Node node : nodes) {
			System.out.println(node.getName());
		}
	}
	
}
