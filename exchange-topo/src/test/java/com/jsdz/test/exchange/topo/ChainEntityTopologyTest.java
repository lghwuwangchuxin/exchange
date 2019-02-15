package com.jsdz.test.exchange.topo;

import java.util.ArrayList;
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

import com.jsdz.exchange.entity.DocumentX;
import com.jsdz.exchange.entity.OrganizationX;
import com.jsdz.exchange.entity.PoliceX;
import com.jsdz.exchange.entity.RecorderX;
import com.jsdz.exchange.entity.SiteX;
import com.jsdz.exchange.topo.EntityTopology;
import com.jsdz.exchange.topo.Link;
import com.jsdz.exchange.topo.Node;
import com.jsdz.exchange.topo.chain.ChainEntityTopology;
import com.jsdz.exchange.topo.service.EntityTopologyService;
import com.jsdz.serializer.Serializer;
import com.jsdz.serializer.SeriallizeException;

/**
 * @类名: ChainEntityTopologyTest
 * @说明: 链式实体拓扑测试
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
		"/testApplicationContext-topo.xml"})
public class ChainEntityTopologyTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EntityTopologyService entityTopoService;

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
		// 组织*****
		Node n1 = new Node();
		n1.setClazz(OrganizationX.class);
		n1.setName("Organization");
		// 采集站*****
		Node n2 = new Node();
		n2.setClazz(SiteX.class);
		n2.setName("WorkStation");
		// 关联实体
		List<Link> sitelinks = new ArrayList<Link>();
		Link site_org = new Link("WorkStation-Org", "Organization", true);
		sitelinks.add(site_org);
		n2.setLinks(sitelinks);
		// 警员*****
		Node n3 = new Node();
		n3.setClazz(PoliceX.class);
		n3.setName("Police");
		// 关联实体
		// 警员-组织
		List<Link> policelinks = new ArrayList<Link>();
		Link police_org = new Link("Police-Org", "Organization", true);
		policelinks.add(police_org);		
		n3.setLinks(policelinks);
		// 记录仪******
		Node n4 = new Node();
		n4.setClazz(RecorderX.class);
		n4.setName("Recorder");
		// 关联实体
		List<Link> recorderLink = new ArrayList<Link>();
		// 记录仪-警员		
		Link rec_police = new Link("Recorder-Police", "Police", true);
		// 记录仪-采集站
		Link rec_site = new Link("Recorder-Site", "WorkStation", true);
		recorderLink.add(rec_police);
		recorderLink.add(rec_site);
		n4.setLinks(recorderLink);
		// 执法档案****
		Node n5 = new Node();
		n5.setClazz(DocumentX.class);
		n5.setName("Document");
		// 关联实体
		List<Link> documentLink = new ArrayList<Link>();
		// 档案-警员
		Link doc_police = new Link("Document-Police", "Police", true);		
		// 档案-记录仪
		Link doc_rec = new Link("Document-Recorder", "Recorder", true);
		documentLink.add(doc_police);
		documentLink.add(doc_rec);
		n5.setLinks(documentLink);
		// 警情&案件******
		// 。。。
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);		
		//
		ChainEntityTopology topo = new ChainEntityTopology();
		topo.setKey("PIS-CCTC");
		topo.setNodes(nodes);
		//
		byte[] bytes = ser.Marshal(topo);
		String xml = new String(bytes);
		System.out.println(xml);
	}
	
	// 获取实体拓扑测试
	@Test
	public void testGetTopo() {
		EntityTopology entityTopo = this.entityTopoService.getEntityTopology("PIS-CCTC");
		System.out.println(entityTopo.getKey());
	}
	
	
}
