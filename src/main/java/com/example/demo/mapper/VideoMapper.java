package com.example.demo.mapper;

import com.example.demo.dto.VideoDto;
import com.example.demo.model.Video;

public class VideoMapper {

    public static VideoDto toDto(Video video){
        VideoDto videoDto=new VideoDto();
        videoDto.setTitle(video.getTitle());
        videoDto.setDescription(video.getDescription());
        videoDto.setId(video.getId());
        videoDto.setTags(video.getTags());
        videoDto.setThumbnailUrl(video.getThumbnailUrl());
        videoDto.setVideoUrl(video.getVideoUrl());
        videoDto.setVideoStatus(video.getVideoStatus());
        videoDto.setLikesCount(video.getLike().get());
        videoDto.setDislikeCount(video.getDislike().get());
        videoDto.setViewCount(video.getViewCount().get());

        return videoDto;
    }

    public static Video toVideo (VideoDto videoDto){
        Video video=new Video();
        video.setTitle(videoDto.getTitle());
        video.setDescription(videoDto.getDescription());
        video.setId(videoDto.getId());

        video.setTags(videoDto.getTags());
        video.setThumbnailUrl(videoDto.getThumbnailUrl());

        return video;
    }

    public static Video updateVideo(VideoDto videoDto,Video video){
        video.setTitle(videoDto.getTitle());
        video.setTags(videoDto.getTags());
        video.setThumbnailUrl(videoDto.getThumbnailUrl());
        video.setVideoStatus(videoDto.getVideoStatus());
        video.setDescription(videoDto.getDescription());

        return video;
    }

}
