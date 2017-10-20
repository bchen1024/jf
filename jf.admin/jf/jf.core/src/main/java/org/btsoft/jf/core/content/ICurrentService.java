package org.btsoft.jf.core.content;

import java.util.Map;

/**
 * @ClassName ICurrentService
 * @Description 获取当前对象接口
 * @author bchen
 * @created 2017年9月24日 下午11:23:46
 */
public interface ICurrentService {

	/**
	 * @Description 获取当前对象
	 * @return
	 * @author bchen
	 * @created 2017年9月24日 下午11:25:00
	 */
	public Map<String,Object> findCurrentData();
}
