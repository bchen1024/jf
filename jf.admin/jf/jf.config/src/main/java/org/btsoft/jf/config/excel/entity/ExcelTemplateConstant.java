package org.btsoft.jf.config.excel.entity;

/**
 * @ClassName ExcelTemplateConstant
 * @Description excel模板常量类
 * @author bchen
 * @created 2016-5-21 下午10:25:19
 */
public interface ExcelTemplateConstant {

	public final static String RESOURCE_CODE = "config.excelTemplate";

	public final static String RESOURCE_DESC_CN = "Excel模板";

	public final static String RESOURCE_DESC_EN = "Excel Template";

	public final static String LIST_IMPORT_TEMPLATE_CODE = "listImportTemplate";

	public final static String LIST_IMPORT_TEMPLATE_DESC_CN = "导入模板列表";

	public final static String LIST_IMPORT_TEMPLATE_DESC_EN = "Import Template List";

	public final static String LIST_EXPORT_TEMPLATE_CODE = "listExportTemplate";

	public final static String LIST_EXPORT_TEMPLATE_DESC_CN = "导出模板列表";

	public final static String LIST_EXPORT_TEMPLATE_DESC_EN = "Export Template List";
	
	public final static String CREATE_IMPORT_CODE= "createImportTemplate";
	
	public final static String CREATE_IMPORT_DESC_CN = "创建导入模板";
	
	public final static String CREATE_IMPORT_DESC_EN = "Create Import Template";
	
	public final static String CREATE_EXPORT_CODE= "createExportTemplate";
	
	public final static String CREATE_EXPORT_DESC_CN = "创建导出模板";
	
	public final static String CREATE_EXPORT_DESC_EN = "Create Export Template";
	
	public final static String UPDATE_IMPORT_CODE= "updateImportTemplate";
	
	public final static String UPDATE_IMPORT_DESC_CN = "更新导入模板";
	
	public final static String UPDATE_IMPORT_DESC_EN = "Update Import Template";
	
	public final static String UPDATE_EXPORT_CODE= "updateExportTemplate";
	
	public final static String UPDATE_EXPORT_DESC_CN = "更新导出模板";
	
	public final static String UPDATE_EXPORT_DESC_EN = "Update Export Template";
	
	public final static String DELETE_IMPORT_CODE= "deleteImportTemplate";
	
	public final static String DELETE_IMPORT_DESC_CN = "删除导入模板";
	
	public final static String DELETE_IMPORT_DESC_EN = "Delete Import Template";
	
	public final static String DELETE_EXPORT_CODE= "deleteExportTemplate";
	
	public final static String DELETE_EXPORT_DESC_CN = "删除导出模板";
	
	public final static String DELETE_EXPORT_DESC_EN = "Delete Export Template";
	
	public final static String SINGLE_IMPORT_CODE= "singleImportTemplate";
	
	public final static String SINGLE_IMPORT_DESC_CN = "单个导入模板";
	
	public final static String SINGLE_IMPORT_DESC_EN = "Single Import Template";
	
	public final static String SINGLE_EXPORT_CODE= "singleExportTemplate";
	
	public final static String SINGLE_EXPORT_DESC_CN = "单个导出模板";
	
	public final static String SINGLE_EXPORT_DESC_EN = "Single Export Template";
	
	public final static String TEMPLATE_CODE_DESC_CN = "获取模板编码";
	
	public final static String TEMPLATE_CODE_DESC_EN = "Template Code";

	public final static String MODULE = "Excel Template";

	public final static String TEMPLATE_TYPE_IMPORT = "import";

	public final static String TEMPLATE_TYPE_EXPORT = "export";

	public final static String LIST_IMPORT_PATH = "/import/find/page";

	public final static String LIST_EXPORT_PATH = "/export/find/page";
	
	public final static String CREATE_IMPORT_PATH = "/import/create";

	public final static String CREATE_EXPORT_PATH = "/export/create";
	
	public final static String UPDATE_IMPORT_PATH = "/import/update";

	public final static String UPDATE_EXPORT_PATH = "/export/update";
	
	public final static String DELETE_IMPORT_PATH = "/import/delete";

	public final static String DELETE_EXPORT_PATH = "/export/delete";
	
	public final static String SINGLE_IMPORT_PATH = "/import/find/single";

	public final static String SINGLE_EXPORT_PATH = "/export/find/single";
	
	public final static String TEMPATE_TYPE_PATH = "/templateCodes/{excelTemplateType}";

}
