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

  userData!:any;
  userId!:any;

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
    this.oidcSecurityService.logoffAndRevokeTokens()
    this.oidcSecurityService.logoffLocal()
  }

  getIdToken(): void {
    this.oidcSecurityService.userData$.subscribe((userData:any) => {
      if (userData) {
        this.userId=userData.userData.sub;
      }
    });

    this.oidcSecurityService.getAccessToken().subscribe((token) => {
      this.token = token;

      // @ts-ignore
      this.videoService.getToken(this.token).subscribe(
        (response) => {
        },
        (error: any) => {
        }
      );
    });
  }


}
