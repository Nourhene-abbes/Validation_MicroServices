package tn.esprit.categoryservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.categoryservice.model.Category;
import tn.esprit.categoryservice.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final String welcome = "Welcome to Categories home page.";

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayWelcome(){
        System.out.println(welcome);
        return welcome;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponseEntity findAll() {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(categoryService.findAll(), httpHeaders, HttpStatus.OK);
    }

    @GetMapping ("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable("name") String name) {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(categoryService.findByName(name), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    @ResponseBody
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }


    @PutMapping("/updateCategory")
    @ResponseBody
    public Category update(@RequestBody Category category) {
        return categoryService.update(category);
    }


}