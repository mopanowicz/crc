import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { Config } from './app/config';
import { KeycloakService } from './app/keycloak.service';
import { environment } from './environments/environment';

import { Client } from '@stomp/stompjs';
// import { WebSocket } from 'ws';

// Object.assign(global, { WebSocket });

if (environment.production) {
  enableProdMode();
}

fetch('assets/config.json')
  .then(response => response.json())
  .then(json => {
    Config.apiRootUrl = json.apiRootUrl;

    const client = new Client({
      brokerURL: 'http://localhost:8081/demo-websocket',
      onConnect: () => {
        client.subscribe('/topic/item', message =>
          console.log(`Received: ${message}`)
        );
        client.publish({ destination: '/topic/item', body: 'First Message' });
      },
    });

    client.activate();

    KeycloakService.init()
      .then(() => {
        platformBrowserDynamic().bootstrapModule(AppModule).catch(err => console.error(err));
      })
  });
