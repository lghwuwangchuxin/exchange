package com.jsdz.exchange.verify.rule;

import java.util.ArrayList;
import java.util.List;

import com.jsdz.exchange.verify.report.DataReportItem;
import com.jsdz.ruleengine.RuleState;
import com.jsdz.ruleengine.annotation.Given;
import com.jsdz.ruleengine.annotation.Result;
import com.jsdz.ruleengine.annotation.Rule;
import com.jsdz.ruleengine.annotation.Then;
import com.jsdz.ruleengine.annotation.When;

/**
 * @类名: DataForeignKeyRule
 * @说明: 数据外键规则
 *
 * @author leehom
 * @Date 2017年9月4日 上午11:29:26 
 * 修改记录：
 *
 * @see
 */
@Rule(name="dataFKRule", description="数据外键规则")
public class DataForeignKeyRule {

	@Given
	private ShootingAssessmentSummary sum;
	
	/** */
	@Result
	private List<DataReportItem> _result;

	@When
	public boolean when() {
		Integer t = sum.getTotalDoc();
		if(t<threshold)
			return true;
		return false;
	}

	@Then
	public RuleState then() {
		AssessmentAlertItem<Integer> item = new AssessmentAlertItem<Integer>();
		item.setItem("上传文件总数");
		item.setV(sum.getTotalDoc());
		item.setRef(threshold);
		if(_result==null)
			_result = new ArrayList<AssessmentAlertItem<?>>();
		_result.add(item);
		// 总数不足，跳出
		return RuleState.BREAK;
	}

}
