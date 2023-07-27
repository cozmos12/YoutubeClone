package com.example.demo.service;

import com.example.demo.dto.UploadVideoDto;
import com.example.demo.dto.VideoDto;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class VideoService {

    private final S3service service;

    private final VideoRepository videoRepository;

    public VideoService(S3service service,  VideoRepository videoRepository) {
        this.service = service;
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
        return videoDto;



    }
}
