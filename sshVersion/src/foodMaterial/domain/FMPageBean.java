package foodMaterial.domain;

import java.util.List;

public class FMPageBean {
//	当前页数
	private Integer currentPage;
//	总页数
	private Integer totalPage;
//	总记录数
	private Integer totalCount;
//	每页记录数
	private Integer pageSize;
//	开始位置
	private Integer begin;
//	每页记录的list集合
	private List<FoodMaterial> list;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public List<FoodMaterial> getList() {
		return list;
	}
	public void setList(List<FoodMaterial> list) {
		this.list = list;
	}
	
}
