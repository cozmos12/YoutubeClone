
import { Component } from '@angular/core';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import {VideoService} from "../video.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-upload-video',
  templateUrl: './upload-video.component.html',
  styleUrls: ['./upload-video.component.css']
})
export class UploadVideoComponent {
  fileUpload:boolean=false;
 fileEntry:FileSystemFileEntry |undefined

  constructor(private videoService:VideoService,private router:Router) {
  }

  public files: NgxFileDropEntry[] = [];

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
         this.fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
         console.log(this.fileUpload)

        this.fileEntry.file((file: File) => {

          console.log(droppedFile.relativePath, file);
          this.fileUpload=true;




        });
      } else {
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }

  public fileOver(event:any){
    console.log(event);
  }

  public fileLeave(event:any){
    console.log(event);
  }

   uploadVideo(){
    if(this.fileEntry!==undefined){

      this.fileEntry.file(file => {
        // @ts-ignore
        this.videoService.uploadVideo(file).subscribe(data=>{

          this.router.navigateByUrl('/save-video/' + data.videoId);
        })
      })


    }


  }
}
