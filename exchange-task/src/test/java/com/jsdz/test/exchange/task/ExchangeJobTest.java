package com.jsdz.test.exchange.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

import com.jsdz.exchange.task.TaskConsts;
import com.jsdz.scheduler.repo.JobBean;
import com.jsdz.scheduler.repo.JobCategory;
import com.jsdz.scheduler.repo.dao.JobCategoryDao;
import com.jsdz.scheduler.service.JobService;
import com.jsdz.scheduler.service.JobServiceException;
import com.jsdz.scheduler.service.SchedulerService;

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
		"/testApplicationContext-jobs.xml",
		"/testApplicationContext-topo.xml",
		"/testApplicationContext-exwriterhandler.xml",
		"/testApplicationContext-dao.xml",
		"/testApplicationContext-documenttype.xml"})
public class ExchangeJobTest {
	
	@Autowired
	private JobService jobService;
	@Autowired
	private SchedulerService schedulerService;
	@Autowired
	private JobCategoryDao jobCategoryDao;
	
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
	
	public static final Long exchangeJobGroupId = 127L;
	
	// 数据交换作
	@Value("${exchange.job.group}")
	private String exchangeJobGroup;
	
	@Value("${exchange.exchangejob.name}")
	private String exchangJobName;
	@Value("${exchange.exchangejob.type}")
	private String exchangJobType;
	@Value("${exchange.exchangejob.desc}")
	private String exchangJobDesc;
	

	// 测试作业
	@Test
	public void testExecExchangeJob() throws JobServiceException {
		//
    	JobCategory jc = jobCategoryDao.get(exchangeJobGroupId);
    	//
    	JobBean jb = new JobBean();         
    	jb.setJobId(2L);
    	jb.setJobName("test");
    	jb.setJobType(exchangJobType);
    	// 指定参数
    	Map<String, Object> params = new HashMap<String, Object>();
    	/*
    	// 报告时间
    	Date reportDate = DateTimeUtils.StringToDate("2017-10-29", DateTimeUtils.defaultDatePatten2);
    	params.put(ShootingAssessmentDailyReportJob.KEY_PARAM_DATE, reportDate);
    	// 警员
    	List<Employees> emps = new ArrayList<Employees>();
    	Employees emp = empService.locateEmployeesById(4L);
    	emps.add(emp);
    	params.put(AssessmentAlertJob.KEY_PARAM_POLICE, emps);*/
    	//
		jobService.executeJob(jc, jb, params);
	}
	
	// 增加参数测试
	@Test
	@Transactional
	public void addJobParam() {
		//
		JobBean job = jobService.getJob(exchangeJobGroup, "test");
		//
		Map<String, Object> params = new HashMap<String, Object>();
		//
    	params.put(TaskConsts.TASK_PARAM_KEY_MARKER_OWNER, "exchangeJob");
		params.put(TaskConsts.TASK_PARAM_KEY_TOPO_KEY, "PIS-CCTC");		
    	//
    	job.setParams(params);
	}

	// 设置交换作业
	@Test
	public void addExchangeJob() throws JobServiceException {
    	//
    	JobCategory jc = jobCategoryDao.get(exchangeJobGroupId);
    	//
    	JobBean jb = new JobBean();           
    	jb.setJobName("test");
    	jb.setJobType(exchangJobType);
    	// 
    	jb.setTimeExp("0 * * * * ?");
    	//
    	schedulerService.scheduleJob(jc, jb);
	}
	
	// 重设作业调度时间
	@Test
	public void resetExchangeJob() throws JobServiceException {
		//
		JobCategory jc = jobCategoryDao.get(exchangeJobGroupId);
		//
		JobBean jb = new JobBean();
		jb.setJobId(2L);
		jb.setJobName("test");
		jb.setJobType(exchangJobType);
		jb.setTimeExp("0 0 * * * ?");
		//
		schedulerService.reScheduleJob("exchange", jb);
		//
		jobService.updateJob(jc, jb);
	}	
	
	// 增加作业分组
	/*@Test
	@Transactional
	public void addExchangeJobCategory() throws JobServiceException {
		JobCategory cate = new JobCategory();
		cate.setCateName(exchangeJobGroup);
		jobCategoryDao.saveOrUpdate(cate);

	}*/
		
	/*****************************/
	@Test
	public void deleteJob() throws JobServiceException {
		JobCategory jc = jobCategoryDao.get(exchangeJobGroupId);
		//
		JobBean j = new JobBean();
		j.setJobId(1L);
		j.setJobName("exchangeJob");
		schedulerService.unScheduleJob(jc, j);
	}
	
	
	@Test
	public void testJob() throws JobServiceException {
		Scanner input = new Scanner(System.in);
		String val = null;
		do {
			System.out.println("请输入：");
			val = input.next();
			
		} while(!val.equals("#"));
		input.close();
	}
	
}
