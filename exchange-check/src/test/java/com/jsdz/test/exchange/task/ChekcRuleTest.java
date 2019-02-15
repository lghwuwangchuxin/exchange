package com.jsdz.test.exchange.task;

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

import com.jsdz.exchange.check.rule.EntityAssociationRule;
import com.jsdz.exchange.check.rule.EntityExsitRule;
import com.jsdz.exchange.load.dao.ExLoadDao;
import com.jsdz.ruleengine.MapWrapper;
import com.jsdz.ruleengine.NameValueReferableMap;
import com.jsdz.ruleengine.Result;
import com.jsdz.ruleengine.dao.ChainRuleTopologyDao;
import com.jsdz.ruleengine.dao.NodeDao;
import com.jsdz.ruleengine.dao.ParamDao;
import com.jsdz.ruleengine.model.RuleBook;
import com.jsdz.ruleengine.model.param.RuleParam;
import com.jsdz.ruleengine.model.topo.Node;
import com.jsdz.ruleengine.model.topo.chain.ChainRuleTopology;
import com.jsdz.ruleengine.service.RuleService;
import com.jsdz.utils.BeanUtils;

/**
 * @类名: ChekcRuleTest
 * @说明: 检验规则测试
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
									
									})
public class ChekcRuleTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private NodeDao nodeDao;
	@Autowired
	private ParamDao paramDao;
	@Autowired
	private ChainRuleTopologyDao topoDao;
	@Autowired
	private RuleService ruleService;
	/** */
	@Autowired
	private ExLoadDao exLoadDao;
    
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
	
	// 创建考核预警规则拓扑
	@Test
	@Transactional
	public void testCreatChekRuleTopology() {
		//
		ChainRuleTopology topo = new ChainRuleTopology();
		topo.setKey("PIS-EntityCheck");
		// 
		Node n1 = createEntityExsitRule();		
		Node n2 = createEntityAssociationRule();
		//
		nodeDao.saveOrUpdate(n1);
		nodeDao.saveOrUpdate(n2);
		//
		topo.setRules(Arrays.asList(n1, n2));
		//
		topoDao.saveOrUpdate(topo);
	}
	
	// 增加规则
	@Test
	@Transactional
	public void testTopologyAddNode() {
		//
		ChainRuleTopology topo = topoDao.get("PIS-EntityCheck");
		// 
		Node n1 = createEntityExsitRule();
		//
		nodeDao.saveOrUpdate(n1);
		//
		topo.getRules().add(n1);
		//
		topoDao.saveOrUpdate(topo);
	}
	
	// 构建实体是否已存在检查规则
	public Node createEntityExsitRule() {
		//
		Node node = new Node();
		node.setRuleClazz(EntityExsitRule.class);
		// 参数
		/*RuleParam p1 = new RuleParam();
		p1.setName("threshold");
		p1.setValue(3);
		//
		Map<String, RuleParam> params = new HashMap<String, RuleParam>();
		params.put("threshold", p1);
		//
		node.setParams(params);*/
		//
		return node;
		
	}
	
	// 上传文档总数
	public Node createEntityAssociationRule() {
		//
		Node node = new Node();
		node.setRuleClazz(EntityAssociationRule.class);
		// 参数
		/*
		 * RuleParam p1 = new RuleParam(); p1.setName("threshold");
		 * p1.setValue(3); // Map<String, RuleParam> params = new
		 * HashMap<String, RuleParam>(); params.put("threshold", p1); //
		 * node.setParams(params);
		 */
		//
		return node;

	}
	
	// 检查规则测试
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	@Transactional
	public void testExecCheckRule() throws Exception {
		/*// 构建事实		
		ShootingAssessmentReport report = assessmentService.getShootingAccessmentReport(4L);
		//
		NameValueReferableMap<ShootingAssessmentSummary> facts = new MapWrapper<ShootingAssessmentSummary>();
		ShootingAssessmentSummary sum = report.getSumOfShootingAssessment();
		facts.setValue("sum", sum);
		// 执行规则
		RuleBook ruleBook = ruleService.run("PIS-EntityCheck", facts);
		// 获取结果
		Result r = (Result)ruleBook.getResult().get();
		List<AssessmentAlert> alerts = (List<AssessmentAlert>)r.getValue();
		//
		BeanUtils.printBean(alerts);*/
			
	}
	
	/********************/
	// 获取拓扑
	@Test
	@Transactional
	public void testGetRuleTopo() {
		//
		ChainRuleTopology topo = topoDao.get("assessmentAlert");
		//
		List<Node> nodes = topo.getRules();
		for(Node node : nodes) {
			System.out.println(node.getName()+","+node.getDescription());
		}
	}
	
	// 规则参数测试
	@Test
	public void testGetRuleParam() {
		//
		List<RuleParam> ruleParams = ruleService.getParamsOfRule(3L);
		//
		for(RuleParam param : ruleParams) {
			System.out.println(param.getName());
			System.out.println(param.getValue().toString());
			System.out.println(param.getValue().getClass().getName());
		}
	}
	
	@Test
	@Transactional
	public void testParam() {
		RuleParam param = paramDao.get(12L);
		param.setValue((float)1/3);
		paramDao.saveOrUpdate(param);
	}
	
	// 删除节点
	@Test
	@Transactional
	public void testDelNode() {
		//
		Node node = nodeDao.get(4L);
		nodeDao.delete(node);
	}

	
}
