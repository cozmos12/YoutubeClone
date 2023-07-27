package com.example.demo.controller;

import com.example.demo.dto.UploadVideoDto;
import com.example.demo.dto.VideoDto;
import com.example.demo.model.Video;
import com.example.demo.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoDto uploadVideo(@RequestParam("file") MultipartFile file){

        return videoService.uploadVideo(file);

    }


    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId")String videoId){

        return videoService.uploadThumbnail(file, videoId);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideo(@RequestBody VideoDto videoDto){
      return videoService.editVideo(videoDto);
    }

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable("videoId") String videoId){

        return videoService.getVideoDetails(videoId);

    }
}
