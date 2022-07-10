package com.new_assement.security_expenses.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.new_assement.security_expenses.Models.Category;
import com.new_assement.security_expenses.Repository.CategoryRepository;
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService( CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    public Category saveCategory(String category_name,String category_id){
        Category cat= new Category();
        if(category_name==null || category_id==null){
            return null;
        }
        cat.setCategory_name(category_name);
        cat.setCategory_id(category_id);
         return categoryRepository.save(cat);
    }
    public List<Category> allcategories(){
        List<Category> listofcategories= categoryRepository.findAll();
        return listofcategories;
    }
    
}
