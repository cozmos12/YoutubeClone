import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  // @ts-ignore
  subscribeToUser(userId: string):Observable<boolean> {
    console.log("subscribeToUser")
   return  this.httpClient.post<boolean>("http://localhost:8080/api/user/subscribe/"+userId,null)
  }

  unSubscribeToUser(userId: string):Observable<boolean> {
    console.log("subscribeToUser")
    return  this.httpClient.post<boolean>("http://localhost:8080/api/user/unSubscribe/"+userId,null)
  }
}
