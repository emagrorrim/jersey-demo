package zp.personal.shopping.mapper;

import zp.personal.shopping.bean.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    int insertCategory(Category category);
    int deleteCategoryById(int id);
    int updateCategory(Category category);
}
