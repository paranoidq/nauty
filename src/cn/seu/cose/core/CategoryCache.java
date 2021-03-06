package cn.seu.cose.core;

import java.util.HashMap;
import java.util.List;

import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.CategoryService;

public class CategoryCache {
	private static List<CategoryPojo> categories;
	private static HashMap<Integer, CategoryPojo> idMap = new HashMap<Integer, CategoryPojo>();
	private static List<CategoryPojo> rootsWithChildren;

	public static void init() {
		CategoryService categoryService = (CategoryService) SystemContainer
				.lookup("categoryService");
		categories = categoryService.getAllCategories();
		rootsWithChildren = categoryService.getRootsWithChildren();
		for (CategoryPojo category : categories) {
			idMap.put(category.getId(), category);
		}
	}

	public static CategoryPojo get(int id) {
		return idMap.get(id);
	}

	public static List<CategoryPojo> getRootsWithChildren() {
		return rootsWithChildren;
	}
}
