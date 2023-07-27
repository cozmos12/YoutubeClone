package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Set;

@Document(value="video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private Integer like;
    private Integer dislike;
    private Set<String> tags;

    private String videoUrl;
    private VideoStatus videoStatus;

    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;

}
