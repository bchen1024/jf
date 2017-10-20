package org.btsoft.jf.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName BatchVO
 * @Description Grid批量操作对象
 * @author bchen
 * @created 2013-11-6 18:59:44
 */
@XmlRootElement
public class BatchVO<T> implements Serializable {

	private static final long serialVersionUID = -7697581668037262398L;

	private List<T> batchCreates;// 批量新增的对象
	private List<T> batchUpdates;// 批量更新的对象
	private List<T> batchDeletes;// 批量删除的对象

	public List<T> getBatchCreates() {
		return batchCreates;
	}

	public void setBatchCreates(List<T> batchCreates) {
		this.batchCreates = batchCreates;
	}

	public List<T> getBatchUpdates() {
		return batchUpdates;
	}

	public void setBatchUpdates(List<T> batchUpdates) {
		this.batchUpdates = batchUpdates;
	}

	public List<T> getBatchDeletes() {
		return batchDeletes;
	}

	public void setBatchDeletes(List<T> batchDeletes) {
		this.batchDeletes = batchDeletes;
	}

}
