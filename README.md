组件说明
1.marker
标记组件，对数据库表分块，记录分块处理位置，标记依赖表递增字段，字段类型支持整数，时间戳，字符串，将来升级为分片组件
2.scheduler-schedulerengine
分布式高效任务调度平台，调度任务组件，该组件被封装，只要引用即可使用(该项目没有该组件源码)
3.task
任务组件，接入调度任务平台；协调其他组件
4.load
数据采集组件，支持载入，读取中间库；载出，二级平台写入到中间库，基于hibernate模型定义
5.topo
数据拓扑组件，定义数据依赖，入库顺序,将来可以扩展为实体关联关系，类型描述
6.check
数据检验组件，支持基于规则检验,该组件还未开发
7.report
数据检验报告组件，支持数据质控闭环，数据质量评分，该组件还未开发
8.write
数据入库组件，基于hibernate模型定义
9.convert
数据转换组件，使用字典组件，目前未实现
10.dictionary
字典组件，未开发
11.规则引擎（未开发）
12.消息引擎（未开发）
在大规模应用中，分离采集和入库，消息引擎放在采集和入库之间，作为交换引擎，起到缓冲，路由，削峰作用
入库配置循序
注意：改项目是基于Class依赖
一.digitalevidence-site注册平台，通过平台来执行不同的数据交换任务，解决多个平台数据交换
	1.1test-config.properties中设置平台，数据源，mid.name=yh0102平台名称，可以不用,platformId=2平台ID，必须填写
	#加载数据
	#写入目标库
	2.Platform.java平台实体类
二.exchange-model配置模型
三.exchange-writer编写handler处理交换
  1.每个实体类一个handler,处理关系，入库，出库方法，公共实体模型
四.exchange-marker设置模型实体标记
  4.1设置标记类型和标记字段
  f.setName("id")标记字段，唯一值，递增
  f.setSvalue()数字，setDvalue()日期，标记起始值,用于标记记录数
  f.setType(MarkerType.L)标记类型，日期，数字，字符串
五.exchange-task设置任务（可以测试任务）
	5.1addExchangeJobCategory()增加作业分组
	5.2addExchangeJob()设置交换作业
	5.3addJobParam()增加参数测试
	5.4testExecExchangeJob()测试作业
	5.6testApplicationContext-dao.xml配置数据源和处理器
	5.7testApplicationContext-exwriterhandler.xml配置入库处理器
	注意:Exchange-Load.hbm.xml里面标志记录<property name="markerFieldS" column="zzjgbm" insert="false" update="false"></property>
	name标记分为三种
	private String markerFieldS;
	private Long markerFieldN;
	private Date markerFieldD;
六.exchange-topo配置
6.1handler的执行循序，先处理那个后处理那个，解决主表和从表外键关系
7.exchange-web启动web,定时处理数据，当定时任务不起效果时,resetExchangeJob()重设定时任务，之后添加addJobParam()增加参数测试
8.exchange-check质检报告记录，错误数据报告
需要重新设置定时任务，或者删除定时任务相关数据重新设置exchange-task

出库配置循序
4.exchange-marker设置实体标记，添加目标实体标记
5.exchange-task设置任务（可以测试任务）
5.6testApplicationContext-dao.xml配置数据源和处理器，调换exLoadDao的数据源
5.7testApplicationContext-exwriterhandler.xml配置入库处理器，设置为目标处理器
说明：其他设置跟入库一样
