import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Project} from "../type/projects";

@Injectable({
  providedIn: 'root'
})
export class CurrentProjectService {

  private _currentProject$$: Subject<Project> = new Subject();
  public currentProject$: Observable<Project> = this._currentProject$$.asObservable();

  constructor() { }

  setCurrentProject(project: Project) {
    this._currentProject$$.next(project);
  }
}
