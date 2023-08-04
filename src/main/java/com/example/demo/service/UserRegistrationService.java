package com.example.demo.service;

import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.VideoDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final UserService userService;
    @Value("${oauth0.userinfoEndpoint}")
    String userInfoEndpoint;



    public String register(String tokenValue){
        HttpRequest httpRequest=HttpRequest.newBuilder().GET().uri(URI.create(userInfoEndpoint))
                .setHeader("Authorization", String.format("Bearer %s",tokenValue))
                .build();

       HttpClient httpClient= HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

         try {
            HttpResponse <String>responseString= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body=responseString.body();
             ObjectMapper objectMapper=new ObjectMapper();
             objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
            UserInfoDto userInfoDto= objectMapper.readValue(body, UserInfoDto.class);
             User user= UserMapper.toUser(userInfoDto);
             userService.getCurrentUser(user.getSub());
            Boolean result= userRepository.existsByEmail(user.getEmail());

             if(result){
                return "user registered in the system";
             }else{
                 userRepository.save(user);
               return  "user successfully registered";
             }

         }catch (Exception e){

             throw new RuntimeException("Exception occurred while registering user "+e);
         }
    }


}
