package com.new_assement.security_expenses.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.new_assement.security_expenses.Models.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
        Optional<Category> findByIdcat(int id);
    
}
