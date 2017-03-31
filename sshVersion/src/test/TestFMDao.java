package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import foodMaterial.dao.FoodMaterialDao;
import foodMaterial.dao.impl.FoodMaterialDaoImpl;
import foodMaterial.domain.FoodMaterial;
import foodMaterial.domain.FoodMaterialCategory;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:bean1.xml")
public class TestFMDao extends AbstractJUnit4SpringContextTests{
	
		@Resource
		FoodMaterialDao fmDao;
		
		@Test
		public void testListFirstCategory(){
			List<FoodMaterialCategory> list = fmDao.listCategory();
			System.out.println(list.size());
		}
		
		@Test
		public void testListSecondCategory(){
			List<FoodMaterialCategory> list = fmDao.listSecondCategroy
					("2c96040d5acb049b015acb04a117001d");
			System.out.println(list.size());
		}
		
		@Test
		public void testFindFM(){
			List<FoodMaterial> list = fmDao.findFm("²¤²Ë", 0, 1000);
			List<FoodMaterial> list2 = fmDao.findFmByCalorie(20, 100);
			System.out.println(list.size());
			System.out.println(list2.size());
		}
		
		@Test
		public void testFindFMByPage(){
			List<FoodMaterial> list = fmDao.findFMByPage(0, 3, "2c96040d5acb049b015acb04a1480021");
			System.out.println(list.size());
		}
}
