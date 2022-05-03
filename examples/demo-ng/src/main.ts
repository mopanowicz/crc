import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { Config } from './app/config';
import { KeycloakService } from './app/keycloak.service';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

fetch('assets/config.json')
  .then(response => response.json())
  .then(json => {
    Config.apiRootUrl = json.apiRootUrl;

    KeycloakService.init({ onLoad: 'login-required', checkLoginIframeInterval: 1 })
      .then(() => {
        platformBrowserDynamic().bootstrapModule(AppModule).catch(err => console.error(err));
      })
  });
