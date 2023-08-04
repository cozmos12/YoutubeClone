package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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
    private AtomicInteger like=new AtomicInteger(0);
    private AtomicInteger dislike=new AtomicInteger(0);
    private Set<String> tags;

    private String videoUrl;
    private VideoStatus videoStatus;

    private AtomicInteger viewCount=new AtomicInteger(0);
    private String thumbnailUrl;
    private List<Comment> commentList=new CopyOnWriteArrayList<>();

    public void incrementLikes(){
        like.incrementAndGet();
    }
    public void decrementLikes(){
        like.decrementAndGet();
    }

    public void incrementDislike(){
        dislike.incrementAndGet();
    }
    public void decrementDislike(){
        dislike.decrementAndGet();
    }

    public void incrementViewCount(){

        viewCount.incrementAndGet();
    }

    public void addComment(Comment comment) {
        commentList.add(comment);

    }
}
