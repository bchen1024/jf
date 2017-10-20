package org.btsoft.jf.config.excel.entity;

/**
 * @ClassName ExcelTaskConstant
 * @Description excel任务常量类
 * @author bchen
 * @created 2016-5-21 下午10:25:19
 */
public interface ExcelTaskConstant {

	public final static String RESOURCE_CODE = "config.excelTask";

	public final static String RESOURCE_DESC_CN = "Excel任务";

	public final static String RESOURCE_DESC_EN = "Excel Task";

	public final static String LIST_IMPORT_TASK_CODE = "listImportTask";

	public final static String LIST_IMPORT_TASK_DESC_CN = "导入任务列表";

	public final static String LIST_IMPORT_TASK_DESC_EN = "Import Task List";

	public final static String MY_IMPORT_TASK_CODE = "myImportTask";

	public final static String MY_IMPORT_TASK_DESC_CN = "我的导入任务";

	public final static String MY_IMPORT_TASK_DESC_EN = "My Import Task";

	public final static String LIST_EXPORT_TASK_CODE = "listExportTask";

	public final static String LIST_EXPORT_TASK_DESC_CN = "导出任务列表";

	public final static String LIST_EXPORT_TASK_DESC_EN = "Export Task List";

	public final static String MY_EXPORT_TASK_CODE = "myExportTask";

	public final static String MY_EXPORT_TASK_DESC_CN = "我的导出任务";

	public final static String MY_EXPORT_TASK_DESC_EN = "My Export Task";

	public final static String MODULE = "Excel Task";

	public final static String TASK_TYPE_IMPORT = "import";

	public final static String TASK_TYPE_EXPORT = "export";

	public final static String DATA_OWNER_ALL = "all";

	public final static String LIST_IMPORT_URL = "/import/find/page";

	public final static String MY_IMPORT_URL = "/import/myTask";

	public final static String LIST_EXPORT_URL = "/export/find/page";

	public final static String MY_EXPORT_URL = "/export/myTask";

	public final static String TASK_STATUS_WAIT = "1";// 待处理
	
	public final static String TASK_STATUS_PROCESSING = "2";// 处理中
	
	public final static String TASK_STATUS_SUCCESS = "3";// 成功
	
	public final static String TASK_STATUS_FAIL = "4";// 失败
	
	public final static String TASK_STATUS_PART_SUCCESS = "5";// 部分成功
}
