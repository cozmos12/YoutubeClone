package com.example.demo.repository;

import com.example.demo.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.GetMapping;

public interface VideoRepository  extends MongoRepository<Video,String> {


}
