package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    public User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getCurrentUser(String sub){
        this.user= userRepository.findBySub(sub);
    }

    public void addToLikeVideos(String videoId) {
        User user=this.user;
        user.addToLikeVideos(videoId);
        userRepository.save(user);
    }

    public boolean ifLikeVideos(String videoId){
       return this.user.getLikeVideos().stream().anyMatch(likeVideo->likeVideo.equals(videoId));
    }

    public boolean ifDislikeVideos(String videoId){
        return this.user.getDislikeVideos().stream().anyMatch(dislikeVideos->dislikeVideos.equals(videoId));
    }

    public void removeFromLikedVideos(String videoId) {
        User user=this.user;
        user.removeFromLikedVideos(videoId);
        userRepository.save(user);
    }

    public void removeFromDislikeVideos(String videoId) {
        User user=this.user;
        user.removeFromDislikedVideos(videoId);
        userRepository.save(user);
    }

    public void addToDislikeVideos(String videoId) {
        User user=this.user;
        user.addToDislikeVideos(videoId);
        userRepository.save(user);
    }

    public void addVideoToHistory(String videoId) {
        user.addToVideoHistory(videoId);
        userRepository.save(user);
    }

    public void subscribe(String userId) {
        user.addToSubscribeUsers(userId);
       User user1 =userRepository.findById(userId).orElseThrow(()->new IllegalStateException("Cannot find user "+userId));
       user1.addToSubscribers(user.getId());
       userRepository.save(user);
       userRepository.save(user1);
    }

    public void unSubscribe(String userId) {
        user.removeFromSubscribeUsers(userId);
        User user1 =userRepository.findById(userId).orElseThrow(()->new IllegalStateException("Cannot find user "+userId));
        user1.removeFromSubscribers(user.getId());
        userRepository.save(user);
        userRepository.save(user1);
    }

    public Set<String> userHistory(String userId) {
        User user =userRepository.findById(userId).orElseThrow(()->new IllegalStateException("Cannot find user "+userId));
        return user.getVideoHistory();


    }
}
