import { Component } from '@angular/core';
import { OAuthService, JwksValidationHandler, AuthConfig } from 'angular-oauth2-oidc';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Desafio Maptriz';

  authConfig: AuthConfig = {
    issuer: 'http://localhost:8080/oauth/token',
    redirectUri: window.location.origin,
    clientId: 'test',
    dummyClientSecret: '123',
    scope: 'password',
    strictDiscoveryDocumentValidation: false
  };

  constructor(private oauthService: OAuthService) {
    this.oauthService.configure(this.authConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
   }

/*  constructor(private oauthService: OAuthService) {
    this.oauthService.redirectUri = window.location.origin;
    this.oauthService.clientId = '{test}';
    this.oauthService.scope = 'password';
    this.oauthService.issuer = 'https://dev-{dev-id}.oktapreview.com';
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();


this.oauthService.loginUrl = 'http://localhost:8080/oauth/token';
this.oauthService.clientId = 'test';
//this.oauthService.resource = 'service URL';
//this.oauthService.logoutUrl = 'https://login.microsoftonline.com/tennant_id/oauth2/logout';
this.oauthService.redirectUri = window.location.origin + '/';
this.oauthService.scope = 'password';
this.oauthService.oidc = false;
this.oauthService.setStorage(sessionStorage);



    // Load Discovery Document and then try to login the user
    this.oauthService.loadDiscoveryDocument().then(() => {
      this.oauthService.tryLogin();
    });
  }*/
}
