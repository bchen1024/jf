package org.btsoft.jf.config.personalized.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IGlobalPersonalizedService
 * @Description 全局个性化设置接口
 * @author bchen
 * @created 2017年9月24日 下午11:27:05
 */
public interface IGlobalPersonalizedService {

	/**
	 * @Description 保存全局设置
	 * @param content
	 * @author bchen
	 * @created 2017年9月24日 下午11:28:39
	 */
	public void saveGlobalPersonalized(HashMap<String, Object> content);
	
	/**
	 * @Description 获取全局设置
	 * @return
	 * @author bchen
	 * @created 2017年9月24日 下午11:32:20
	 */
	public Map<String,Object> findGlobalPersonalized();
}
