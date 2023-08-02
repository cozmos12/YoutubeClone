import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../video.service";

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent implements OnInit {

  videoId!: string;
  videoUrl!:string;
  description!:string
  title!:string
  tags:Array<string>=[]



  videoAvailable:boolean=false

  constructor(private activatedRoute:ActivatedRoute ,private videoService:VideoService) {

    // @ts-ignore
    this.videoId=this.activatedRoute.snapshot.params.videoId;
    this.videoService.getVideo(this.videoId).subscribe(data=>{
      console.log("first"+data.videoUrl)
      this.videoUrl=data.videoUrl;
     this.title= data.title
      this.description=data.description
      this.tags=data.tags
      this.videoAvailable=true
    })

  }
  ngOnInit(): void {
  }

}
