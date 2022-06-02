import { Injectable } from '@angular/core';
import {BackendService} from "./backend.service";
import {Observable} from "rxjs";
import {Project} from "../type/project-type";
import {User} from "../type/user";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private mapping: String = '/project';

  constructor(
    private _backendService: BackendService
  ) { }

  public getAllProjects(): Observable<Project[]> {
    return this._backendService.get<Project[]>(this.mapping + `/all`);
  }

  public getProjectById(id: number): Observable<Project> {
    return this._backendService.get<Project>(this.mapping + `/${id}`);
  }

  public registerUser(alternativePackageId: number, user: User): Observable<User> {
    return this._backendService.post<User>(this.mapping + `/new/user/${alternativePackageId}`, user);
  }

  public getUserById(userId: number): Observable<User> {
    return this._backendService.get<User>(this.mapping + `/user/${userId}`);
  }
}
