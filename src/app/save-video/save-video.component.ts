import {Component, inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import {MatChipEditedEvent, MatChipInputEvent} from "@angular/material/chips";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {ActivatedRoute} from "@angular/router";
import {VideoService} from "../video.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {VideoDto} from "../video-dto";


class tags {
}

@Component({
  selector: 'app-save-video',
  templateUrl: './save-video.component.html',
  styleUrls: ['./save-video.component.css']
})
export class SaveVideoComponent implements OnInit {

  saveVideoForm:FormGroup;
  title:FormControl=new FormControl('')
  description:FormControl=new FormControl('')
  videoStatus:FormControl=new FormControl('')
  addOnBlur = true;
  videoAvailable:boolean=false

  selectFile!:File;
  // @ts-ignore
  selectFileName:string;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  videoId='';
  fileSelected = false
 public videoUrl!:string;


  announcer = inject(LiveAnnouncer);
  private thumnailUrl!: string;

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      this.tags.push(value);
    }
    event.chipInput!.clear();
  }

  constructor(private activatedRoute:ActivatedRoute ,private videoService:VideoService ,private matSnackBar:MatSnackBar) {
    // @ts-ignore
    this.videoId=this.activatedRoute.snapshot.params.videoId;
    this.videoService.getVideo(this.videoId).subscribe(data=>{
      console.log("first"+data.videoUrl)
      this.videoUrl=data.videoUrl;
      this.thumnailUrl=data.thumbnailUrl;
      this.videoAvailable=true;
    })
    this.saveVideoForm = new FormGroup({
      videoStatus:this.videoStatus,
      title:this.title,
      description:this.description


    })
  }

  remove(value: String): void {
    // @ts-ignore
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);

      this.announcer.announce(`Removed ${this.tags}`);
    }
  }

  edit(tsg: tags, event: MatChipEditedEvent) {
    const value = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!value) {
      this.remove(value);
      return;
    }

    // @ts-ignore
    const index = this.tags.indexOf(this.tags);
    if (index >= 0) {
      this.tags[index] = value;
    }
  }

  ngOnInit(): void {
  }

  protected readonly name = name;

  onFileSelected($event: Event) {

    console.log("click")
    // @ts-ignore
    this.selectFile=$event.target.files[0];
    this.selectFileName=this.selectFile.name;
    this.fileSelected=true;
  }

  onUpload() {
    console.log("click")
  this.videoService.uploadThumbnail(this.selectFile,this.videoId)
    .subscribe(data=>{
      this.matSnackBar.open("Thumbnail Upload Success","OK")
      console.log(data)
    })
  }


  saveVideo() {
    // @ts-ignore
    console.log(this.videoStatus.get('videoStatus')?.value)
    const videoMetaData:VideoDto={
      "id":this.videoId,
      "title":this.saveVideoForm.get('title')?.value,
      "description":this.saveVideoForm.get('description')?.value,
      "tags":this.tags,
      "videoStatus":this.saveVideoForm.get('videoStatus')?.value,
      "thumbnailUrl":this.thumnailUrl,
      "videoUrl":this.videoUrl

    }
    this.videoService.saveVideo(videoMetaData).subscribe(data=>{
      this.matSnackBar.open("Video Metadata Success","OK")
    })
  }
}
