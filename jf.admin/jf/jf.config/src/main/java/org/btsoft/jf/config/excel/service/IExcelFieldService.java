package org.btsoft.jf.config.excel.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.excel.entity.ExcelFieldVO;
import org.btsoft.jf.config.excel.entity.ExcelSheetVO;
import org.btsoft.jf.core.constants.CoreConstant;

/**
 * @ClassName IExcelFieldService
 * @Description Excel字段Service接口
 * @author bchen
 * @created 2017-6-11 下午10:59:11
 */
@Path("/excel/field")
@Produces(MediaType.APPLICATION_JSON)
public interface IExcelFieldService {

	/**
	 * @Description 获取某个sheet的字段配置
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2016-5-23 下午10:30:20
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public List<ExcelFieldVO> findExcelFiledList(
			@QueryParam("") ExcelSheetVO excelSheet);

	/**
	 * @Description 创建excel字段
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createExcelField(ExcelFieldVO excelField);

	/**
	 * @Description 获取excel字段配置
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:51:32
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public ExcelFieldVO findExcelField(@QueryParam("") ExcelFieldVO excelField);

	/**
	 * @Description 更新excel字段
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	@POST
	@Path(CoreConstant.PATH_UPDATE)
	public void updateExcelField(ExcelFieldVO excelField);

	/**
	 * @Description 删除excel字段
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	@POST
	@Path(CoreConstant.PATH_DELETE)
	public void deleteExcelField(ExcelFieldVO excelField);
}
