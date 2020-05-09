package cn.ekgc.ams.pojo.vo;

import cn.ekgc.ams.util.ConstantUtil;

import java.io.Serializable;
import java.util.List;

/**
 * <b>分页视图</b>
 */
public class Page<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pageNo;            //当前页码
	private Integer pageSize;           //每页显示的数量
	private List<E> list;               //结果列表
	private Long Total;                 //总记录数
	private Long Pages;                 //总页数
	private Integer draw;               //用于DateTable 组件

	public Page() {}
	public Page(Integer pageNo, Integer pageSize, Integer draw) {
		if (pageNo != null && pageNo > 0) {
			this.pageNo = pageNo;
		} else {
			this.pageNo = ConstantUtil.PAGE_NO;
		}

		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = ConstantUtil.PAGE_SIZE;
		}
		this.draw = draw;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public Long getTotal() {
		return Total;
	}

	public void setTotal(Long total) {
		Total = total;
	}

	public Long getPages() {
		return Pages;
	}

	public void setPages(Long pages) {
		Pages = pages;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}
}
