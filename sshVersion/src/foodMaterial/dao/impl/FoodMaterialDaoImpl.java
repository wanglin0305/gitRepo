package foodMaterial.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.pagination.SQL2008StandardLimitHandler;
import org.springframework.orm.hibernate5.HibernateTemplate;

import foodMaterial.dao.FoodMaterialDao;
import foodMaterial.domain.FoodMaterial;
import foodMaterial.domain.FoodMaterialCategory;

public class FoodMaterialDaoImpl implements FoodMaterialDao {
	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public List<FoodMaterialCategory> listCategory() {
		String sql="from FoodMaterialCategory where fMaterialCategory=null";
		List<FoodMaterialCategory> list = (List<FoodMaterialCategory>) hibernateTemplate.find(sql);
		return list;
	}
	@Override
	public List<FoodMaterial> listAll(String categoryId) {
		/*两种方法都可以*/
		/*String sql ="from FoodMaterial where foodMaterialCategory.id=?";
		List<FoodMaterial> list = (List<FoodMaterial>) hibernateTemplate.find(sql, categoryId);*/
		String sql = "from FoodMaterialCategory where fId=?";
		FoodMaterialCategory fmc = hibernateTemplate.get(FoodMaterialCategory.class, categoryId);
		return new ArrayList<FoodMaterial>(fmc.getfMaterialset());
	}
	@Override
	public List<FoodMaterialCategory> listSecondCategroy(String categoryId) {
		String sql = "from FoodMaterialCategory where fMaterialCategory.fmCategoryId=?";
		List<FoodMaterialCategory> list = (List<FoodMaterialCategory>) hibernateTemplate.find(sql, categoryId);
		return list;
	}
	@Override
	public List<FoodMaterial> findFm(String name, float minfloat, float maxfloat) {
		String sql = "from FoodMaterial where fName like ? and calorie > ? and calorie < ?";
		List<FoodMaterial> list = (List<FoodMaterial>) hibernateTemplate.find(sql, "%" +name+"%", minfloat,maxfloat);
		return list;
	}
	@Override
	public List<FoodMaterial> findFmByCalorie(float minfloat, float maxfloat) {
		String sql = "from FoodMaterial where calorie > ? and calorie < ?";
		List<FoodMaterial> list = (List<FoodMaterial>) hibernateTemplate.find(sql, minfloat,maxfloat);
		return list;
	}
	@Override
	public FoodMaterial getFmById(String fmId) {
		String sql = "from FoodMaterial where fId=?";
		return hibernateTemplate.get(FoodMaterial.class, fmId) ;
	}
	@Override
	public int countFM(String categoryId) {		
		return hibernateTemplate.get(FoodMaterialCategory.class, categoryId).getfMaterialset().size();
	}
	@Override
	public List<FoodMaterial> findFMByPage(int begin, int pageSize, String categoryId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FoodMaterial.class);
		criteria.createAlias("fMaterialCategorieSet", "fmc");
		criteria.add(Restrictions.eq("fmc.fmCategoryId", categoryId));
		return (List<FoodMaterial>) hibernateTemplate.findByCriteria(criteria, begin, pageSize);

		
	}
	

		
}
