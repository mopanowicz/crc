import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import { Item } from './item';

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  itemsURL = 'http://localhost:8081/v1/items'

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Item[]> {
    return this.httpClient.get<Item[]>(this.itemsURL)
      .pipe(
        tap(v => console.log(`got ${v.length} items`))
      )
  }

  get(id: number): Observable<Item> {
    return this.httpClient.get<Item>(`${this.itemsURL}/${id}`)
      .pipe(
        tap(_ => console.log(`got item id=${id}`))
      )
  }

  create(item: Item): Observable<any> {
    return this.httpClient.post(this.itemsURL, item, this.httpOptions)
    .pipe(
      tap(v => console.log(`created item ${v}`))
    )
  }  

  update(item: Item): Observable<any> {
    return this.httpClient.patch(this.itemsURL, item, this.httpOptions)
    .pipe(
      tap(v => console.log(`updated item ${v}`))
    )
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete(`${this.itemsURL}/${id}`, this.httpOptions)
    .pipe(
      tap(_ => console.log(`deleted item id=${id}`))
    )
  }
}
