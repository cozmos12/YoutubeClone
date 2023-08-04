package com.example.demo.mapper;

import com.example.demo.dto.UserInfoDto;
import com.example.demo.model.User;

public class UserMapper {

    public static User toUser(UserInfoDto userInfoDto){
        User user=new User();
        user.setFullName(userInfoDto.getName());
        user.setName(userInfoDto.getGivenName());
        user.setLastName(userInfoDto.getFamilyName());
        user.setEmail(userInfoDto.getEmail());
        user.setSub(userInfoDto.getSub());

        return user;
    }

}
