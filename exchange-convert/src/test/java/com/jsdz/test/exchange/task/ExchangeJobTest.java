package com.jsdz.test.exchange.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.admin.security.model.Employees;
import com.jsdz.admin.security.service.EmployeesService;
import com.jsdz.digitalevidence.assessment.cycle.Cycle;
import com.jsdz.digitalevidence.assessment.job.AssessmentAlertJob;
import com.jsdz.digitalevidence.assessment.job.ShootingAssessmentDailyReportJob;
import com.jsdz.scheduler.repo.JobBean;
import com.jsdz.scheduler.repo.JobCategory;
import com.jsdz.scheduler.repo.dao.JobCategoryDao;
import com.jsdz.scheduler.service.JobService;
import com.jsdz.scheduler.service.JobServiceException;
import com.jsdz.scheduler.service.SchedulerService;
import com.jsdz.utils.DateTimeUtils;

/**
 * @类名: AssessmentJobTest
 * @说明: 考核作业测试
 *
 * @author   leehom
 * @Date	 2017年4月24日 下午2:00:03
 * 修改记录：
 *
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations={
		"/testApplicationContext-common.xml",
		"/testApplicationContext-scheduler.xml",
		"/testApplicationContext-jobs.xml",})
public class ExchangeJobTest {
	
	@Autowired
	private JobService jobService;
	@Autowired
	private SchedulerService schedulerService;
	@Autowired
	private JobCategoryDao jobCategoryDao;
	@Autowired
	private EmployeesService empService;
	
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
	
	// 考核报告生成
	@Test
	@Transactional
	public void testAssessmentAlert() {
		
	}
	
	public static final Long AssessmentJobGroupId = 17L;
	
	// 考核作业分组
	@Value("${assessment.jobgroup}")
	private String assessmentGroup;
	
	/************考核周报告作业************/
	@Value("${assessment.dailyreportjob.name}")
	private String dailyReportJobName;
	@Value("${assessment.dailyreportjob.type}")
	private String dailyReportJobType;
	@Value("${assessment.dailyreportjob.desc}")
	private String dailyJobDesc;

	// 日考核报告作业测试
	// 每日运行，生成当前考核报告
	@Test
	public void testExecDailyReportJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(8L);
    	jb.setJobName(dailyReportJobName);
    	jb.setJobType(dailyReportJobType);
    	// 周一 凌晨1点
    	jb.setTimeExp("0 0 1 * * ?");
    	jb.setDesc(dailyJobDesc);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	// 报告时间
    	Date reportDate = DateTimeUtils.StringToDate("2017-10-29", DateTimeUtils.defaultDatePatten2);
    	params.put(ShootingAssessmentDailyReportJob.KEY_PARAM_DATE, reportDate);
    	// 警员
    	List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);
    	//
		jobService.executeJob(jc, jb, params);
	}

	// 设置周考核报告作业
	@Test
	@Transactional
	public void setDailyReportJob() throws JobServiceException {
    	//
    	JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName(dailyReportJobName);
    	jb.setJobType(dailyReportJobType);
    	// 0 0 1 * * ? ，每日凌晨1点
    	jb.setTimeExp("0 0 1 * * ?");
    	jb.setDesc(dailyJobDesc);
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	/*************推送文档**************/
	@Value("${assessment.pushjob.name}")
	private String pushJobName;
	@Value("${assessment.pushjob2.name}")
	private String pushJobName2;
	@Value("${assessment.pushjob.type}")
	private String pushJobType;
	@Value("${assessment.pushjob.desc}")
	private String pushJobDesc;
	
	// 考核周报告作业测试
	// 使用即时运行api
	@Test
	public void testExecPushJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(8L);
    	jb.setJobName(pushJobName);
    	jb.setJobType(pushJobType);
    	jb.setDesc(pushJobDesc);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	// 周期
    	Date start = DateTimeUtils.StringToDate("2017-11-05", DateTimeUtils.defaultDatePatten2);
    	Date end = DateTimeUtils.StringToDate("2017-12-05", DateTimeUtils.defaultDatePatten2);
    	Cycle cycle = new Cycle(start, end);
    	params.put(AssessmentAlertJob.KEY_PARAM_CYCLE, cycle);
    	// 警员
    	List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);
    	//
		jobService.executeJob(jc, jb, null);
	}
	
	// 设置考核视频推送作业
	@Test
	@Transactional
	public void setPushJob() throws JobServiceException {
    	// 考核作业分类
    	JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName(pushJobName);
    	jb.setJobType(pushJobType);
    	// 0 0 0 * 3,6 ? ，
    	// 星期2推送
    	jb.setTimeExp("0 0 0 * 3 ?");
    	jb.setDesc(pushJobDesc);
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	// 设置考核视频推送作业2，周六执行
	@Test
	@Transactional
	public void setPushJob2() throws JobServiceException {
    	// 考核作业分类
    	JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName(pushJobName2);
    	jb.setJobType(pushJobType);
    	// 0 0 0 * 3,6 ? ，
    	// 星期6推送
    	jb.setTimeExp("0 0 0 ? * 7");
    	jb.setDesc(pushJobDesc);
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	// 重设考核推送作业
	@Test
	@Transactional
	public void resetPushJob() throws JobServiceException {
		//
		JobCategory jc = jobCategoryDao.get(17L);
		//
		JobBean jb = new JobBean();
		jb.setJobId(8L);
		jb.setJobName(alertJobName);
		jb.setJobType(assessmentGroup);
		// 0 0 1 11 * ? ，11日凌晨1点
		// 10号冬季而，冻结日后
		jb.setTimeExp("0 30 1 11 * ?");
		jb.setDesc(alertJobDesc);
		//
		jobService.updateJob(jc, jb);
	}
	
	/************周考核预警作业************/
	@Value("${assessment.weeklyalertjob.name}")
	private String weeklyAlertJobName;
	@Value("${assessment.weeklyalertjob.type}")
	private String weeklyAlertJobType;
	@Value("${assessment.weeklyalertjob.desc}")
	private String weeklyAlertJobDesc;

	// 周考核预警作业测试
	@Test
	public void testExecWeeklyAlertJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(8L);
    	jb.setJobName(weeklyAlertJobName);
    	jb.setJobType(weeklyAlertJobType);
    	// 周二凌晨
    	jb.setTimeExp("0 0 0 * 2 ?");
    	jb.setDesc(weeklyAlertJobDesc);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	// 周期
    	Date start = DateTimeUtils.StringToDate("2017-11-05", DateTimeUtils.defaultDatePatten2);
    	Date end = DateTimeUtils.StringToDate("2017-12-05", DateTimeUtils.defaultDatePatten2);
    	Cycle cycle = new Cycle(start, end);
    	params.put(AssessmentAlertJob.KEY_PARAM_CYCLE, cycle);
    	// 警员
    	/*List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);*/
    	//
		jobService.executeJob(jc, jb, params);
	}

	// 设置周考核预警作业
	@Test
	@Transactional
	public void setWeeklyAlertJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobName(weeklyAlertJobName);
    	jb.setJobType(weeklyAlertJobType);
    	jb.setDesc(weeklyAlertJobDesc);
    	// 周一
    	jb.setTimeExp("0 0 0 ? * 2");
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	/************月考核报告作业************/
	@Value("${assessment.monthlyreportjob.name}")
	private String monthlyReportJobName;
	@Value("${assessment.monthlyreportjob.type}")
	private String monthlyReportJobType;
	@Value("${assessment.monthlyreportjob.desc}")
	private String monthlyReportJobDesc;

	/*****************月考核报告作业********************/
	// 考核月报告作业测试
	@Test
	public void testExecMonthlyReportJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(8L);
    	jb.setJobName(monthlyReportJobName);
    	jb.setJobType(monthlyReportJobType);
    	jb.setDesc(monthlyReportJobDesc);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	// 周期
    	Date start = DateTimeUtils.StringToDate("2017-10-05", DateTimeUtils.defaultDatePatten2);
    	Date end = DateTimeUtils.StringToDate("2017-11-05", DateTimeUtils.defaultDatePatten2);
    	Cycle cycle = new Cycle(start, end);
    	params.put(AssessmentAlertJob.KEY_PARAM_CYCLE, cycle);
    	// 警员
    	List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);
    	//
		jobService.executeJob(jc, jb, params);
	}

	// 设置月考核报告作业
	@Test
	@Transactional
	public void setMonthlyReportJob() throws JobServiceException {
    	//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName(monthlyReportJobName);
    	jb.setJobType(monthlyReportJobType);
    	// 0 0 1 11 * ? ，10号凌晨1点
    	jb.setTimeExp("0 0 1 10 * ?");
    	jb.setDesc(monthlyReportJobDesc);
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	/************冻结月考核报告作业************/
	@Value("${assessment.freezejob.name}")
	private String freezeJobName;
	@Value("${assessment.freezejob.type}")
	private String freezeJobType;
	@Value("${assessment.freezejob.desc}")
	private String freezeJobDesc;

	/*****************冻结核报告作业********************/
	// 冻结月考核报告作业测试
	@Test
	public void testExecFreezeJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(8L);
    	jb.setJobName(freezeJobName);
    	jb.setJobType(freezeJobType);
    	jb.setDesc(freezeJobDesc);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	// 周期
    	Date start = DateTimeUtils.StringToDate("2017-10-05", DateTimeUtils.defaultDatePatten2);
    	Date end = DateTimeUtils.StringToDate("2017-11-05", DateTimeUtils.defaultDatePatten2);
    	Cycle cycle = new Cycle(start, end);
    	params.put(AssessmentAlertJob.KEY_PARAM_CYCLE, cycle);
    	// 警员
    	List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);
    	//
		jobService.executeJob(jc, jb, params);
	}

	// 设置冻结核报告作业
	@Test
	@Transactional
	public void setFreezeJob() throws JobServiceException {
    	//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName(freezeJobName);
    	jb.setJobType(freezeJobType);
    	// 0 0 0 12 * ?，10号凌晨生成月考核报告，12号冻结
    	jb.setTimeExp("0 0 0 12 * ?");
    	jb.setDesc(freezeJobDesc);
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	/************月预警作业************/
	@Value("${assessment.alertjob.name}")
	private String alertJobName;
	@Value("${assessment.alertjob.type}")
	private String alertJobType;
	@Value("${assessment.alertjob.desc}")
	private String alertJobDesc;

	// 考核预警作业测试
	@Test
	public void testExecAlertJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(21L);
    	jb.setJobName(alertJobName);
    	jb.setJobType(alertJobType);
    	jb.setDesc(alertJobDesc);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	// 周期
    	Date start = DateTimeUtils.StringToDate("2017-10-05", DateTimeUtils.defaultDatePatten2);
    	Date end = DateTimeUtils.StringToDate("2017-11-05", DateTimeUtils.defaultDatePatten2);
    	Cycle cycle = new Cycle(start, end);
    	params.put(AssessmentAlertJob.KEY_PARAM_CYCLE, cycle);
    	// 警员
    	List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);
    	//
		jobService.executeJob(jc, jb, params);
	}

	// 设置考核报警作业
	@Test
	@Transactional
	public void setAlertJob() throws JobServiceException {
    	//
    	JobCategory jc = jobCategoryDao.get(17L);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName(alertJobName);
    	jb.setJobType(alertJobType);
    	// 0 0 1 13 * ? ，13日凌晨1点
    	jb.setTimeExp("0 0 0 13 * ?");
    	jb.setDesc(alertJobDesc);
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	// 重设置考核报警作业
	@Test
	@Transactional
	public void resetAlertJob() throws JobServiceException {
		//
		JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
		//
		JobBean jb = new JobBean();
		jb.setJobId(8L);
		jb.setJobName(alertJobName);
		jb.setJobType(assessmentGroup);
		// 0 0 1 11 * ? ，11日凌晨1点
		// 10号冬季而，冻结日后
		jb.setTimeExp("0 30 1 11 * ?");
		jb.setDesc(alertJobDesc);
		//
		jobService.updateJob(jc, jb);
	}	
		
	/*****************************/
	@Test
	public void deleteJob() throws JobServiceException {
		JobCategory jc = jobCategoryDao.get(AssessmentJobGroupId);
		//
		JobBean j = new JobBean();
		j.setJobId(8L);
		j.setJobName("AssessmentAlertJob");
		schedulerService.unScheduleJob(jc, j);
	}
	
}
