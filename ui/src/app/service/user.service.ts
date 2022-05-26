import { Injectable } from '@angular/core';
import {BackendService} from "./backend.service";
import {Observable} from "rxjs";
// @ts-ignore
import {User} from "../../app/type/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private mapping: String = '/user';

  constructor(
    private _backendService: BackendService
  ) { }

  public getOneUser(userId: number): Observable<User> {
    return this._backendService.get<User>(this.mapping + `/${userId}`);
  }

  public getAllUsers(): Observable<User[]> {
    return this._backendService.get<User[]>(this.mapping + `/all`);
  }

  public addOneUser(user: User): Observable<void> {
    return this._backendService.post<void>(this.mapping + `/add/one`, user);
  }

  public addManyUsers(users: User[]): Observable<void> {
    return this._backendService.post<void>(this.mapping + `/add`, users);
  }
}
