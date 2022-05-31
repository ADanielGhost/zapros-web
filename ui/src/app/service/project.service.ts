import { Injectable } from '@angular/core';
import {BackendService} from "./backend.service";
import {Observable} from "rxjs";
import {Project} from "../type/projects";

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
}
