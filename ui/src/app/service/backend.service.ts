import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private backendHost: string = environment.production ? "https://zapros-web.herokuapp.com" : "http://localhost:8090";

  constructor(
    private _http: HttpClient
  ) { }

  public get<T>(url: string): Observable<T> {
    return this._http.get<T>(this.backendHost + url);
  }

  public post<T>(url: string, body: any): Observable<T> {
    // return this._http.post<T>(this.backendHost + url, body, { responseType: 'text' as 'json'});
    return this._http.post<T>(this.backendHost + url, body);
  }
}
