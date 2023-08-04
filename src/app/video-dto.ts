
export interface VideoDto{
   id:string;
   title:string;
   description:string;
   tags:Array<string>;
   videoStatus:string;
   thumbnailUrl:string;
   videoUrl:string;
   likeCount:number;
   dislikesCount:number;
   viewCount:number;
}
