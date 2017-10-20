package org.btsoft.jf.core.file;

import java.util.Map;

import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IUploadSupport
 * @Description 上传处理接口
 * @author bchen
 * @created 2015-2-3 23:46:22
 */
public interface IUploadSupport {

	/**
	 * @Description 获取上传配置信息
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2015-2-4 下午11:59:46
	 */
	public UploadSetting uploadSetting(Map<String,String> params)  throws ApplicationException;
	
	/**
	 * @Description 校验文件上传安全性
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2014-11-14 下午7:32:46
	 */
	boolean checkSecurity(Map<String, String> params) throws ApplicationException;
	
	/**
	 * @Description 上传成功的回调函数
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2017-6-3 下午7:38:29
	 */
	Map<String,Object> uploadCallback(Map<String,Object> params)  throws ApplicationException;
}
