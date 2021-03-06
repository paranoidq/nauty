package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.CategoryDAO;
import cn.seu.cose.entity.CategoryPojo;

@Service
public class CategoryService {

	@Autowired
	CategoryDAO categoryDAOImpl;

	public List<CategoryPojo> getCategoriesByParentId(int parentId) {
		return categoryDAOImpl.getCategoriesByParentId(parentId);
	}

	public List<CategoryPojo> getRootCategories() {
		return categoryDAOImpl.getRootCategories();
	}

	public List<CategoryPojo> getRootsWithChildren() {
		List<CategoryPojo> roots = categoryDAOImpl.getRootCategories();
		for (CategoryPojo categoryPojo : roots) {
			int parentId = categoryPojo.getId();
			List<CategoryPojo> children = categoryDAOImpl
					.getCategoriesByParentId(parentId);
			categoryPojo.setChildren(children);
		}
		return roots;
	}

	public CategoryPojo getCategoryById(int id) {
		return categoryDAOImpl.getCatById(id);
	}
	
	public List<CategoryPojo> getAllCategories() {
		return categoryDAOImpl.getAllCats();
	}
}
