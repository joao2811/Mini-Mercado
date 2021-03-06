package util;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private Integer id_category;
	private String categoryName;
	private Integer categoryLevel;

	public Category() {
		
	}
	
	public Category(Integer id_category, String categoryName, Integer categoryLevel) {
		this.id_category = id_category;
		this.categoryName = categoryName;
		this.categoryLevel = categoryLevel;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	public Integer getCategoryLevel() {
		return categoryLevel;
	}
	
	public Integer getCategoryID() {
		return id_category;
	}
	
	@Override
	public String toString() {
		StringBuilder montar = new StringBuilder();
		
		montar.append("Category " + id_category + "#: \n");
		montar.append("======================================\n");
		montar.append("Category Name: " + categoryName + "\n");
		montar.append("Category Level: " + categoryLevel + "\n");
		montar.append("======================================\n\n");
		
		return montar.toString();
	}

	public List<Object> list(){
		List<Object> list = new ArrayList<>();
		
		list.add(id_category);
		list.add(categoryName);
		list.add(categoryLevel);
		
		return list;
	}

}
