package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.dao.RestaurantDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantCategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private CategoryDao categoryDao;


    /**
     * The method implements the business logic for getAllRestaurants endpoint.
     */
    public List<RestaurantEntity> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntity = restaurantDao.getAllRestaurants().getResultList();
        return restaurantEntity;
    }

    public List<RestaurantCategoryEntity> getCategoryByRestaurant(RestaurantEntity restaurantEntity) throws RestaurantNotFoundException {

        List<RestaurantCategoryEntity> restaurantCategoryEntity = restaurantDao.getCategoryByRestaurant(restaurantEntity);
        return restaurantCategoryEntity;
    }

    public List<RestaurantEntity> getRestaurantByRestaurantName(String restaurantName) throws RestaurantNotFoundException {
        if (restaurantName == null) {
            throw new RestaurantNotFoundException("RNF-003", "Restaurant name field should not be empty");
        }
        List<RestaurantEntity> restaurantEntity = restaurantDao.getRestaurantsByName(restaurantName);

        return restaurantEntity;
    }

    public List<RestaurantEntity> getRestaurantsByCategory(String categoryId) throws CategoryNotFoundException {
        if (categoryId == null) {
            throw new CategoryNotFoundException("CNF-001", "Category id field should not be empty");
        }

        CategoryEntity categoryEntity = categoryDao.getCategoryById(categoryId);
        if (categoryEntity == null) {
            throw new CategoryNotFoundException("CNF-002", "No category by this id");
        }


        List<RestaurantCategoryEntity> restaurantCategoryEntities = restaurantDao.restaurantsByCategoryId(categoryEntity).getResultList();
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        if (!restaurantCategoryEntities.isEmpty()) {
            restaurantCategoryEntities.stream()
                    .map(e -> restaurantEntities.add(e.getRestaurant()))
                    .collect(Collectors.toList());
            return restaurantEntities;

        } else {
            return Collections.emptyList();
        }
    }

}
