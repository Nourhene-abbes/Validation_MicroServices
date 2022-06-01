package tn.esprit.categoryservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.categoryservice.model.Category;
import tn.esprit.categoryservice.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    private final String welcome = "Welcome to Categories home page.";

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public String sayWelcome(){
        System.out.println(welcome);
        return welcome;
    }

    @GetMapping("/findAll")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping ("/findByName")
    public Category findByName(String name) {
        return categoryService.findByName(name);
    }

    @PostMapping("/addCategory")
    public Category create(Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/updateCategory")
    public Category update(@RequestBody Category category) {
        return categoryService.update(category);
    }


}