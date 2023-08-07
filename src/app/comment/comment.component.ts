import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CommnetsService} from "../commnets.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommentDto} from "../comment-dto";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  @Input()
  videoId:string ='';
  @Input()
  UserId:string='';

  commentsDto:CommentDto[]=[]

  commentsFormGroup :FormGroup;

  constructor(private commentService: CommnetsService,private matSnackBar: MatSnackBar) {
    this.commentsFormGroup=new FormGroup({
      comment: new FormControl(),
    });

  }




  postComment() {

    const comment=this.commentsFormGroup.get('comment')?.value

    const commentDto={

      "text":comment,
      "authorId":this.UserId


    }
    this.commentService.postComment(commentDto,this.videoId).subscribe(()=>{
      this.matSnackBar.open("success","OK")
      this.commentsFormGroup.get('comment')?.reset();
      this.getComment()
    })

  }

  ngOnInit(): void {
  this.getComment()
  }

  getComment(){
    this.commentService.getAllComments(this.videoId).subscribe(data=>{

      this.commentsDto=data;
    })
  }
}
