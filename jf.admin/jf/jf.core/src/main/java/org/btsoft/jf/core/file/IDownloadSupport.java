package org.btsoft.jf.core.file;

import java.util.Map;

import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IDownload
 * @Description 下载支持接口
 * @author bchen
 * @created 2014-11-14 下午7:29:10
 */
public interface IDownloadSupport {

	/**
	 * @Description 处理下载文件信息
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2014-11-14 下午7:32:32
	 */
	FileInfo processFile(Map<String, String> params)throws ApplicationException;
	
	/**
	 * @Description 校验文件下载安全性
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2014-11-14 下午7:32:46
	 */
	boolean checkSecurity(Map<String, String> params);
}
