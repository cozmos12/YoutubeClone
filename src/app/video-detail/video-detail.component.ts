import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../video.service";
import {OidcSecurityService} from "angular-auth-oidc-client";
import {UserService} from "../user.service";

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
  likeCount:number=0;
  dislikesCount:number=0;
  viewCount:number=0;
  isAuthenticated: boolean = false;
  userId!: any;
  showSubscribeButton:boolean = true;
  showUnSubscribeButton:boolean = false;





  videoAvailable:boolean=false


  constructor(private userService:UserService,private oidcSecurityService: OidcSecurityService,private activatedRoute:ActivatedRoute ,private videoService:VideoService) {

    // @ts-ignore
    this.videoId=this.activatedRoute.snapshot.params.videoId;
    this.videoService.getVideo(this.videoId).subscribe(data=>{

      this.videoUrl=data.videoUrl;
      this.title= data.title
      this.description=data.description
      this.tags=data.tags
      this.likeCount=data.likesCount
      this.dislikesCount=data.dislikeCount
      this.viewCount=data.viewCount
      this.videoAvailable=true
    })

  }
  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(({ isAuthenticated }) => {
      this.isAuthenticated = isAuthenticated;
      if(isAuthenticated){
        this.getData();

      }

    });
  }

  getData(){
    this.oidcSecurityService.userData$.subscribe((userData:any) => {
      if (userData) {
        this.userId=userData.userData.sub;
      }
    });
  }

  disLikeVideo() {
    this.videoService.disLikeVideo(this.videoId).subscribe(data=>{

      this.likeCount=data.likesCount
      this.dislikesCount=data.dislikeCount
    })
  }

  likeVideo() {
    this.videoService.likeVideo(this.videoId).subscribe(data=>{

      this.likeCount=data.likesCount
      this.dislikesCount=data.dislikeCount

    })

  }

  subscribeToUser() {
    this.userService.subscribeToUser(this.userId).subscribe(result=>{
      this.showUnSubscribeButton=true;
      this.showSubscribeButton=false;
    })
  }

  unSubscribeToUser() {
    this.userService.unSubscribeToUser(this.userId).subscribe(result=>{
      this.showUnSubscribeButton=false;
      this.showSubscribeButton=true;
    })

  }
}
