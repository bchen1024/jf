package org.btsoft.jf.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TreeUtils
 * @Description 树工具类
 * @author bchen
 * @created 2016-10-2 下午3:15:46
 */
public class TreeUtils {

	public static List<Map<String, Object>> listToTreeList(
			List<Map<String, Object>> treeList, Long parentId,String idKey,String pidKey) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : treeList) {
			Long id = Long.parseLong(map.get(idKey).toString());
			Long pid = map.get(pidKey) == null ? -1L : Long.parseLong(map
					.get(pidKey).toString());
			if (pid.equals(parentId)) {
				List<Map<String, Object>> children = listToTreeList(treeList,
						id,idKey,pidKey);
				if(!CollectionUtils.isNullOrEmpty(children)){
					map.put("children", children);
				}
				trees.add(map);
			}
		}
		return trees;
	}
}
