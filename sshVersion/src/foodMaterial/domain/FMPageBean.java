package foodMaterial.domain;

import java.util.List;

public class FMPageBean {
//	��ǰҳ��
	private Integer currentPage;
//	��ҳ��
	private Integer totalPage;
//	�ܼ�¼��
	private Integer totalCount;
//	ÿҳ��¼��
	private Integer pageSize;
//	��ʼλ��
	private Integer begin;
//	ÿҳ��¼��list����
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
