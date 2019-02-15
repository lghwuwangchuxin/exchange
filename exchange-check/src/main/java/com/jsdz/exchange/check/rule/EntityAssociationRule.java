package com.jsdz.exchange.check.rule;

import java.util.ArrayList;
import java.util.List;
import com.jsdz.exchange.check.report.DataReportItem;
import com.jsdz.ruleengine.RuleState;
import com.jsdz.ruleengine.annotation.Given;
import com.jsdz.ruleengine.annotation.Result;
import com.jsdz.ruleengine.annotation.Rule;
import com.jsdz.ruleengine.annotation.Then;
import com.jsdz.ruleengine.annotation.When;
import com.jsdz.ruleengine.model.topo.Node;

/**
 * @类名: EntityAssociationRule
 * @说明: 实体关联规则
 *
 * @author leehom
 * @Date 2017年9月4日 上午11:29:26 
 * 修改记录：
 *
 * @see
 */
@Rule(name="entityAssociationRule", description="实体关联规则")
public class EntityAssociationRule {

	@Given
	private Object entity;
	
	@Given
	private Node node;
	
	/** */
	@Result
	private List<DataReportItem> _result;

	@When
	public boolean when() {
		return false;
	}

	@Then
	public RuleState then() {
		DataReportItem item = new DataReportItem();
		
		if(_result==null)
			_result = new ArrayList<DataReportItem>();
		_result.add(item);
		// 
		return RuleState.BREAK;
	}

}
