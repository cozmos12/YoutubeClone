import {Component, OnInit} from '@angular/core';
import {VideoService} from "../video.service";
import {VideoDto} from "../video-dto";

@Component({
  selector: 'app-featured',
  templateUrl: './featured.component.html',
  styleUrls: ['./featured.component.css']
})
export class FeaturedComponent implements OnInit {

  FeaturedVideos:Array<VideoDto>=[];
  constructor(private videoService: VideoService) {

  }
  ngOnInit(): void {
    this.videoService.getAllVideos().subscribe(response=>{
      this.FeaturedVideos = response
    })
  }

}
