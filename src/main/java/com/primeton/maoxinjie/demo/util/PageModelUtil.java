package com.primeton.maoxinjie.demo.util;

import java.util.List;

/**
 * 封装分页信息
 */
public class PageModelUtil<E> {
	
	private E searchObj;

	// 结果集
	private List<E> list;

	// 查询记录数
	private int totalRecords;

	// 第几页
	private int pageNo = 0;

	// 每页多少条记录
	private int pageSize = 10;
	
	private String order = "ID";

	// 总页数
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}

	// 首页
	public int getTopPage() {
		return 0;
	}

	// 上一页
	public int getPreviousPage() {
		if (pageNo <= 0) {
			return 0;
		}
		return pageNo - 1;
	}

	// 下一页
	public int getNextPage() {
		if (pageNo >= getBottomPage()) {
			return getBottomPage();
		}
		return pageNo + 1;
	}

	// 尾页
	public int getBottomPage() {
		int bottomPage = 0;
		
		if (getTotalPages() > 0) {
			bottomPage = getTotalPages()-1;
		}
		
		return bottomPage;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public E getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(E searchObj) {
		this.searchObj = searchObj;
	}
	
	public int getStartNo() {
		return this.pageNo * this.pageSize;
	}

	@Override
	public String toString() {
		return "PageModel [list=" + list + ", totalRecords=" + totalRecords + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize + "]";
	}

}
