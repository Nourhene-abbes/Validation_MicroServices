package tn.esprit.categoryservice.service;

import java.util.List;

public interface ICategoryService<T> {

    List<T> findAll();

    List<T> findByName(String name);

    T create(T category);

    T update(T category);
}