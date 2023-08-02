import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UploadVideoComponent} from "./upload-video/upload-video.component";
import {SaveVideoComponent} from "./save-video/save-video.component";
import {VideoDetailComponent} from "./video-detail/video-detail.component";

const routes: Routes = [
  {
    path:'upload-video',component:UploadVideoComponent,

  },{
    path:'save-video/:videoId',component:SaveVideoComponent,

  },{
    path:'video-detail/:videoId',component:VideoDetailComponent,

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
