package com.new_assement.security_expenses.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.new_assement.security_expenses.Models.Expense;

public interface ExpenseRepository  extends JpaRepository<Expense,Integer>{
    
}
