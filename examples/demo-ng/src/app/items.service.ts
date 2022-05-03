import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import { Item } from './item';
import { Config } from './config';
import { KeycloakService } from './keycloak.service';

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  itemsURL = Config.apiRootUrl + '/v1/items'

  constructor(private httpClient: HttpClient) {
  }

  private httpHeaders(): any {
    return new HttpHeaders({
        'Authorization': `Bearer ${KeycloakService.getToken()}`
      });
  }

  getAll(): Observable<Item[]> {
    console.log(this.httpHeaders());
    return this.httpClient.get<Item[]>(this.itemsURL, { headers: this.httpHeaders() })
      .pipe(
        tap(v => console.log(`got ${v.length} items`))
      )
  }

  get(id: number): Observable<Item> {
    return this.httpClient.get<Item>(`${this.itemsURL}/${id}`, { headers: this.httpHeaders() })
      .pipe(
        tap(_ => console.log(`got item id=${id}`))
      )
  }

  create(item: Item): Observable<any> {
    return this.httpClient.post(this.itemsURL, item, { headers: this.httpHeaders() })
      .pipe(
        tap(v => console.log(`created item ${JSON.stringify(v)}`))
      )
  }  

  update(item: Item): Observable<any> {
    return this.httpClient.patch(this.itemsURL, item, { headers: this.httpHeaders() })
      .pipe(
        tap(v => console.log(`updated item ${JSON.stringify(v)}`))
      )
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete(`${this.itemsURL}/${id}`, { headers: this.httpHeaders() })
      .pipe(
        tap(_ => console.log(`deleted item id=${id}`))
      )
  }
}
