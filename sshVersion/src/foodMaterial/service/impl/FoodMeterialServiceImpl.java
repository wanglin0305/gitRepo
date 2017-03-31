package foodMaterial.service.impl;



import java.util.List;

import foodMaterial.dao.FoodMaterialDao;
import foodMaterial.domain.FMPageBean;
import foodMaterial.domain.FoodMaterial;
import foodMaterial.domain.FoodMaterialCategory;
import foodMaterial.service.FoodMaterialService;


public class FoodMeterialServiceImpl implements FoodMaterialService{
	
	private FoodMaterialDao fmd;
	public void setFmd(FoodMaterialDao fmd) {
		this.fmd = fmd;
	}

	@Override
	public List<FoodMaterialCategory> listCategory() {
		
		return fmd.listCategory();
	}

	@Override
	public List<FoodMaterial> listAll(String categoryId) {
		
		return fmd.listAll(categoryId);
	}

	@Override
	public List<FoodMaterialCategory> listSecondCategory(String categoryId) {
		return fmd.listSecondCategroy(categoryId);
	}

	@Override
	public List<FoodMaterial> findFm(String name, String min, String max) {
		float minfloat = 0;
		float maxfloat = 10000;
		if(min!=null && !min.equals("")){
			minfloat = Float.parseFloat(min);
		}
		if(max!=null && !max.equals("")){
			maxfloat = Float.parseFloat(max);
		}
		if(name == null || name.equals("")){
			List<FoodMaterial> list = fmd.findFmByCalorie(minfloat,maxfloat);
			return list;
		}
		List<FoodMaterial> list = fmd.findFm(name,minfloat,maxfloat);
		return list;
	}

	@Override
	public FoodMaterial getFmById(String fmId) {
		
		return fmd.getFmById(fmId);
	}

	@Override
	public FMPageBean listPage(Integer currentPage,String categoryId) {
		FMPageBean pageBean = new FMPageBean();
		pageBean.setCurrentPage(currentPage);
		int totalCount = fmd.countFM(categoryId);
		pageBean.setTotalCount(totalCount);
		int pageSize = 3;
		int totalPage = 0;
		if(totalCount%pageSize==0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize + 1;
		}
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		int begin = pageSize*(currentPage - 1);
		pageBean.setBegin(begin);
		
		List<FoodMaterial> list = fmd.findFMByPage(begin,pageSize,categoryId);
		pageBean.setList(list);
		return pageBean;
	}
	
	

}
