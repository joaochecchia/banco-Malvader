package com.example.EstudosSpring.Mapper;

import com.example.EstudosSpring.Response.RegisterRequest;
import com.example.EstudosSpring.User.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserModel map(RegisterRequest request){
        UserModel userModel = new UserModel();

        userModel.setEmail(request.email());
        userModel.setUsername(request.username());
        userModel.setPassword(request.password());

        return userModel;
    }
}
