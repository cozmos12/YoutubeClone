package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.UploadVideoDto;
import com.example.demo.dto.VideoDto;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.model.Comment;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final S3service service;
    private final UserService userService;

    private final VideoRepository videoRepository;

    public VideoService(S3service service, UserService userService, VideoRepository videoRepository) {
        this.service = service;
        this.userService = userService;
        this.videoRepository = videoRepository;
    }

    public UploadVideoDto uploadVideo(MultipartFile multipartFile){
        String url=service.uploadFile(multipartFile);
        var video =new Video();
        video.setVideoUrl(url);
       var videoDto= videoRepository.save(video);
       return new UploadVideoDto(video.getId(), video.getVideoUrl() );
    }

    public VideoDto editVideo(VideoDto videoDto) {
       Video video= videoRepository.findById(videoDto.getId())
               .orElseThrow(()->new IllegalStateException("Cannot find video"+videoDto.getId()));
       Video saveVideo=VideoMapper.updateVideo(videoDto,video);
       videoRepository.save(saveVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video thumbnail= videoRepository.findById(videoId)
                .orElseThrow(()->new IllegalStateException("Cannot find video"+videoId));

        String thumbnailUrl=service.uploadFile(file);

        thumbnail.setThumbnailUrl(thumbnailUrl);

        videoRepository.save(thumbnail);
        return thumbnailUrl;

    }

    public VideoDto getVideoDetails(String videoId) {
        Optional<Video> saveVideo=videoRepository.findById(videoId);
        VideoDto videoDto=VideoMapper.toDto(saveVideo.get());
        userService.addVideoToHistory(videoId);
        increaseVideoCount(saveVideo.get());
        return videoDto;
    }

    private void increaseVideoCount(Video saveVideo) {
        saveVideo.incrementViewCount();
        videoRepository.save(saveVideo);
    }


    public VideoDto likeVideo(String videoId) {
        Video video=getVideoById(videoId);
        // Increment Like Count
// If user already liked the video, then decrement like count
// like - 0, dislike - 0
// like - 1, dislike - 0
// like - 0, dislike - 0
// like - 0, dislike - 1
// like - 1, dislike - 0 I
// If user already disliked the video, then increment like count and decrement dislike count
        if(userService.ifLikeVideos(videoId)){
            video.decrementLikes();
            userService.removeFromLikedVideos(videoId);
        }else if(userService.ifDislikeVideos(videoId)){
            video.decrementDislike();
            userService.removeFromDislikeVideos(videoId);
            video.incrementLikes();
            userService.addToLikeVideos(videoId);
        }else{
            video.incrementLikes();
            userService.addToLikeVideos(videoId);
        }
        videoRepository.save(video);

        return VideoMapper.toDto(video);
    }

    public Video getVideoById(String videoId) {

        return videoRepository.findById(videoId).orElseThrow(() ->new IllegalArgumentException("cannot video Id"+videoId));
    }


    public VideoDto dislike(String videoId) {

        Video video=getVideoById(videoId);
        // Increment Like Count
// If user already liked the video, then decrement like count
// like - 0, dislike - 0
// like - 1, dislike - 0
// like - 0, dislike - 0
// like - 0, dislike - 1
// like - 1, dislike - 0 I
// If user already disliked the video, then increment like count and decrement dislike count
        if(userService.ifDislikeVideos(videoId)){
            video.decrementDislike();
            userService.removeFromDislikeVideos(videoId);
        }else if(userService.ifLikeVideos(videoId)){
            video.decrementLikes();
            userService.removeFromLikedVideos(videoId);
            video.incrementLikes();
            userService.addToDislikeVideos(videoId);
        }else{
            video.incrementDislike();
            userService.addToDislikeVideos(videoId);
        }
        videoRepository.save(video);

        return VideoMapper.toDto(video);
    }

    public void addComment(String videoId, CommentDto commentDto) {
        Video video=getVideoById(videoId);
        Comment comment= CommentMapper.toComment(commentDto);
        video.addComment(comment);
        videoRepository.save(video);
    }

    public List<CommentDto> getAllComments(String videoId){
        Video video=getVideoById(videoId);
        List<Comment> comments=video.getCommentList();
        List<CommentDto> commentDtos=new ArrayList<CommentDto>();


        for(Comment comment: comments){
            commentDtos.add(CommentMapper.toCommentDto(comment));
        }
        return commentDtos;


    }

    public List<VideoDto> getAllVideos() {

         List<Video> videos=videoRepository.findAll();
         List<VideoDto> videosDto=new ArrayList<VideoDto>();
         for(Video video: videos){
            videosDto.add(VideoMapper.toDto(video)) ;
         }
         return videosDto;
    }
}
