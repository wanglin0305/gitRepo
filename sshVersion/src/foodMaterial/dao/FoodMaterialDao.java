package foodMaterial.dao;

import java.util.List;

import foodMaterial.domain.FoodMaterial;
import foodMaterial.domain.FoodMaterialCategory;

public interface FoodMaterialDao {
	List<FoodMaterialCategory> listCategory();
	
	List<FoodMaterial> listAll(String categoryId);

	List<FoodMaterialCategory> listSecondCategroy(String categoryId);

	List<FoodMaterial> findFm(String name, float minfloat, float maxfloat);

	List<FoodMaterial> findFmByCalorie(float minfloat, float maxfloat);

	FoodMaterial getFmById(String fmId);

	int countFM(String categoryId);

	List<FoodMaterial> findFMByPage(int begin, int pageSize, String categoryId);
}
