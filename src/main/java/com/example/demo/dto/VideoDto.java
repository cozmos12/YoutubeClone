package com.example.demo.dto;

import com.example.demo.model.Comment;
import com.example.demo.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private Integer likesCount;
    private Integer dislikeCount;
    private String description;
    private Set<String> tags;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer viewCount;

}
