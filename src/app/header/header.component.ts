import { Component, OnInit } from '@angular/core';
import { OidcSecurityService, OpenIdConfiguration } from 'angular-auth-oidc-client';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {VideoService} from "../video.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  token!:any;

  isAuthenticated: boolean = false;

  constructor(private oidcSecurityService: OidcSecurityService, private http: HttpClient,private videoService:VideoService ) {}

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(({ isAuthenticated }) => {
      this.isAuthenticated = isAuthenticated;
      if(this.isAuthenticated){
        this.getIdToken();
      }
    });
  }

  login():void {
    this.oidcSecurityService.authorize();


  }

  logOff() {
    this.oidcSecurityService.logoff().subscribe((result) => console.log(result));
  }

  getIdToken(): void {
    this.oidcSecurityService.getUserData().subscribe(data=>{
      console.log(data)
    })

    this.oidcSecurityService.getAccessToken().subscribe((token) => {
      this.token = token;
      console.log("click");
      // @ts-ignore
      this.videoService.getToken(this.token).subscribe(
        (response) => {
          console.log("Token received from the backend:", response);
        },
        (error: any) => {
          console.error("Error while getting the token:", error);
        }
      );
    });
  }


}
