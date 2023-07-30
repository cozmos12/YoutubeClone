import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { FileSystemFileEntry } from 'ngx-file-drop';
import {Observable} from "rxjs";
import {uploadVideoResponse} from "./upload-video/UploadVideoResponse";
import {VideoDto} from "./video-dto";


@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient:HttpClient) { }

  uploadVideo(fileEntry:File):Observable<uploadVideoResponse> {

    const formData = new FormData()
    // @ts-ignore
    formData.append('file', fileEntry, fileEntry.name)
    return this.httpClient.post<uploadVideoResponse>("http://localhost:8080/api/videos",formData)
  }
  uploadThumbnail(fileEntry:File,videoId:String):Observable<string> {

    const formData = new FormData()
    // @ts-ignore
    formData.append('file', fileEntry, fileEntry.name)
    // @ts-ignore
    formData.append('videoId', videoId);

    return this.httpClient.post("http://localhost:8080/api/videos/thumbnail",formData,{
      responseType:'text'
    })
  }

  getVideo(videoId:String)  : Observable<VideoDto>{
    return this.httpClient.get<VideoDto>("http://localhost:8080/api/videos/"+ videoId);
  }

  saveVideo(videoMetaData: VideoDto)  : Observable<VideoDto> {
   return  this.httpClient.put<VideoDto>("http://localhost:8080/api/videos",videoMetaData)
  }
}