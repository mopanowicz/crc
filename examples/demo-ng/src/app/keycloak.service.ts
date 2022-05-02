import { Injectable } from '@angular/core';

import * as Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  static auth: any = {};

  constructor() { }

  static init(): Promise<void> {
    const keycloakAuth: any = Keycloak('assets/keycloak.json');
    KeycloakService.auth.looggedIn = false;
    return new Promise<void>((resolve, reject) => {
			keycloakAuth.init({onLoad: 'login-required'})
				.then(() => {
					console.log('init '+ keycloakAuth);
					KeycloakService.auth.loggedIn = true;
					KeycloakService.auth.authz = keycloakAuth;
					resolve();
				})
				.catch(() => {
					reject();
				});
		});
  }

  static logout(): void {
		console.log('logout');
    KeycloakService.auth.authz.logout().then(() => {
      KeycloakService.auth.loggedIn = false;
      KeycloakService.auth.authz = null;
      console.log('logout success');
    }).catch(() => {
      console.log('logout error');
    });
  }
}
