import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private backendHost: string = "http://localhost:8090";

  constructor(
    private _http: HttpClient
  ) { }

  public get<T>(url: string): Observable<T> {
    console.log('! get with: ' + this.backendHost + url)
    return this._http.get<T>(this.backendHost + url);
  }

  public post<T>(url: string, body: any): Observable<T> {
    const jsonBody: string = JSON.stringify(body);
    console.log('! post with: ' + this.backendHost + url)
    console.log('! post with body: ' + jsonBody)
    return this._http.post<T>(this.backendHost + url, jsonBody);
  }
}
