package tn.esprit.categoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.categoryservice.model.Category;
import tn.esprit.categoryservice.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService implements ICategoryService<Category>{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category category1 = categoryRepository.findById(category.getId()).get();
        category1.setName(category.getName());
        return categoryRepository.save(category1);
    }


}