import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../video.service";

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent implements OnInit {
  showSubscribeButton:boolean=false;

  videoId!: string;
  videoUrl!:string;
  description!:string
  title!:string
  tags:Array<string>=[]
  likeCount:number=0;
  dislikesCount:number=0;
  viewCount:number=0;



  videoAvailable:boolean=false

  constructor(private activatedRoute:ActivatedRoute ,private videoService:VideoService) {

    // @ts-ignore
    this.videoId=this.activatedRoute.snapshot.params.videoId;
    this.videoService.getVideo(this.videoId).subscribe(data=>{

      this.videoUrl=data.videoUrl;
      this.title= data.title
      this.description=data.description
      this.tags=data.tags
      this.likeCount=data.likeCount
      this.dislikesCount=data.dislikesCount
      this.viewCount=data.viewCount

      this.videoAvailable=true
    })

  }
  ngOnInit(): void {
  }

  disLikeVideo() {
    this.videoService.disLikeVideo(this.videoId).subscribe(data=>{

      this.likeCount=data.likeCount
      this.dislikesCount=data.dislikesCount
    })
  }

  likeVideo() {
    this.videoService.likeVideo(this.videoId).subscribe(data=>{

      this.likeCount=data.likeCount
      this.dislikesCount=data.dislikesCount

    })

  }

  subscribeToUser() {

  }

  unSubscribeToUser() {

  }
}
