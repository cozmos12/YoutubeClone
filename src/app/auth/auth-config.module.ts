import { NgModule } from '@angular/core';

import { AuthModule, LogLevel } from 'angular-auth-oidc-client';
import {HttpHeaders} from "@angular/common/http";




@NgModule({
    imports: [AuthModule.forRoot({

        config: {
            authority: 'https://dev-gpva6ltihjajt4ka.us.auth0.com',
          redirectUrl: "http://localhost:4200/callback",
            postLogoutRedirectUri: window.location.origin,
            clientId: '8YzAGIZzBzKUfqmbo3RIf08KAffX4GqW',
            scope: 'openid profile offline_access email',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            logLevel: LogLevel.Debug,
            secureRoutes: ['http://localhost:8080/'],
          customParamsAuthRequest: {
            audience: 'http://localhost:8080/'
          }


        }
      })],
    providers:[],
    exports: [AuthModule],
})
export class AuthConfigModule {}
