package org.btsoft.jf.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName PagedResult
 * @Description 分页查询结果对象
 * @author bchen
 * @created 2017-6-29 23:18:59
 */
@XmlRootElement
public class PagedResult<T> implements Serializable {

	private static final long serialVersionUID = 6836075175803582005L;

	/**
	 * 总数记录
	 */
	private int total;

	/**
	 * 当前页的结果
	 */
	private List<T> result;

	private PageVO page;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public PageVO getPage() {
		return page;
	}

	public void setPage(PageVO page) {
		this.page = page;
	}

}
