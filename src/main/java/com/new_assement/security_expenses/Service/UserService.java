package com.new_assement.security_expenses.Service;

import org.springframework.stereotype.Service;

import com.new_assement.security_expenses.Models.UserModel;
import com.new_assement.security_expenses.Repository.UsersRepository;

@Service
public class UserService {
 

    private final UsersRepository usersRepository;

    public UserService (UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }
    
    public UserModel registeruser(String login,String password,String user_email){
        if (login==null||password==null||user_email==null){
            return null;
        }
        else{
            UserModel userModel= new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setUser_email(user_email);
            return usersRepository.save(userModel);
        }
    }
     public UserModel authenticate (String login,String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
     }

    
}

    

