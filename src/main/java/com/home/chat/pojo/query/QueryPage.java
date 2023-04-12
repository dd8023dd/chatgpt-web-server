/**
 * 系统名称: 
 * 模块名称:
 * 创建者：     zhouwm
 * 创建时间： 2018年5月24日上午8:29:33
 * 主要功能：
 * 相关文档:
 * 修改记录:
 * 修改日期      修改人员                     修改说明<BR>
 * ========     ======  ============================================
 *   
 * ========     ======  ============================================
 * 评审记录：
 * 
 * 评审人员：
 * 评审日期：
 * 发现问题：
 */
package com.home.chat.pojo.query;

import java.util.List;

public class QueryPage<T> {

	private static final long serialVersionUID = 9097196584393175462L;
	/**
	 * 总记录数
	 */
	protected int totalCount;

	/**
	 * 每页记录数大小
	 */
	protected int pageSize = 20;

	/**
	 * 查询第几页
	 */
	protected int pageNo = 1;

	/**
	 * 排序字段,如: row_id desc, create_date asc
	 */
	private String order;

	/**
	 * 分页查询返回的结果List
	 */
	List<T> data;

	/**
	 * 是否需要分页
	 **/
	private Boolean needPaging;

	/**
	 * 获取开始的记录条数序号,从0开始
	 * 
	 * @return
	 */
	public int getStart() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize + 1;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Boolean getNeedPaging() {
		return needPaging;
	}

	public void setNeedPaging(Boolean needPaging) {
		this.needPaging = needPaging;
	}
}
