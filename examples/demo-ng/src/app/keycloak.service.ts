import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import * as Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  static auth: any = {};

  private static jwtHelper: JwtHelperService = new JwtHelperService();

  constructor() { }

  static init(): Promise<void> {
    const keycloakAuth: any = Keycloak('assets/keycloak.json');
    return new Promise<void>((resolve, reject) => {
      keycloakAuth.init({
          onLoad: 'login-required'
        })
        .then(() => {
          console.log('init '+ keycloakAuth);
          KeycloakService.auth = keycloakAuth;
          resolve();
        })
        .catch(() => {
          reject();
        });
    });
  }

  private static getKeycloak() {
    // @ts-ignore
    return window['_keycloak'];
  }

  static getToken(): string {
    var keycloak = this.getKeycloak();
    return keycloak != null ? keycloak.token : null;
  }

  static isTokenValid(): boolean {
    var token = this.getToken();
    return token != null ? !KeycloakService.jwtHelper.isTokenExpired(token) : false;
  }

  static logout(): void {
    console.log('logout');
    var keycloak = this.getKeycloak();
    keycloak.logout().then(() => {
        console.log('logout success');
        KeycloakService.auth = null;
        keycloak.login({redirectUri: window.location.origin});
      }).catch(() => {
        console.log('logout error');
      });
  }
}
