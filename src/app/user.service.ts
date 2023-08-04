import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  subscribeToUser(videoId: string){
    this.httpClient.post<boolean>("http://localhost:8080/api/user/subscribe/"+videoId,null)
  }
}
