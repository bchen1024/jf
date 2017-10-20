package org.btsoft.jf.config.excel.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.excel.entity.ExcelSheetVO;
import org.btsoft.jf.core.constants.CoreConstant;

/**
 * @ClassName IExcelSheetService
 * @Description 模板sheet服务接口
 * @author bchen
 * @created 2016-8-5 下午10:55:00
 */
@Path("/excel/sheet")
@Produces(MediaType.APPLICATION_JSON)
public interface IExcelSheetService {

	/**
	 * @Description 获取某个模板的sheet列表
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2016-8-5 下午10:56:14
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public List<ExcelSheetVO> findExcelSheetList(
			@QueryParam("") ExcelSheetVO excelSheet);

	/**
	 * @Description 创建excelSheet
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createExcelSheet(ExcelSheetVO excelSheet);

	/**
	 * @Description 获取excel Sheet配置
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:51:32
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public ExcelSheetVO findExcelSheet(@QueryParam("") ExcelSheetVO excelSheet);

	/**
	 * @Description 更新excelSheet
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	@POST
	@Path(CoreConstant.PATH_UPDATE)
	public void updateExcelSheet(ExcelSheetVO excelSheet);

	/**
	 * @Description 删除excel Sheet
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	@POST
	@Path(CoreConstant.PATH_DELETE)
	public void deleteExcelSheet(ExcelSheetVO excelSheet);

}
