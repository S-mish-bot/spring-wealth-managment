package com.new_assement.security_expenses.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.new_assement.security_expenses.Models.UserModel;

public interface UsersRepository  extends JpaRepository<UserModel,Integer>{
        Optional<UserModel> findByLoginAndPassword(String login,String Password);
    
}
