package foodMaterial.service;

import java.util.List;

import foodMaterial.domain.FMPageBean;
import foodMaterial.domain.FoodMaterial;
import foodMaterial.domain.FoodMaterialCategory;

public interface FoodMaterialService {

	List<FoodMaterialCategory> listCategory();

	List<FoodMaterial> listAll(String categoryId);

	List<FoodMaterialCategory> listSecondCategory(String categoryId);

	List<FoodMaterial> findFm(String name, String min, String max);

	FoodMaterial getFmById(String fmId);

	FMPageBean listPage(Integer currentPage, String categoryId);

}