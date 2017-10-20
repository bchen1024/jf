package org.btsoft.jf.core.entity;

import java.io.Serializable;

/**
 * @ClassName PageVO
 * @Description 分页对象VO，包含分页信息的基本属性
 * @author bchen
 * @created 2017年6月29日 下午10:50:18
 */
public class PageVO implements Serializable {

	private static final long serialVersionUID = 2749419142634153065L;

	/**
	 * 每页大小，默认15条
	 */
	private int pageSize = 15;

	/**
	 * 当前第几页，默认为第一页
	 */
	private int curPage = 1;

	/**
	 * 记录起始位置
	 */
	private int startRow;

	/**
	 * 总记录数
	 */
	private int total = 0;

	/**
	 * 总分页数
	 */
	private int pageNum = 0;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStartRow() {
		this.startRow = (this.curPage - 1) * this.pageSize;
		return this.startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		this.pageNum = (int) Math.ceil((double) (this.total / this.pageSize));
		return this.pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
