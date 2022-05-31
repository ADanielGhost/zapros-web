import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {Project} from "../../type/projects";
import {NavigationExtras, Router} from "@angular/router";
import {CurrentProjectService} from "../../service/current-project.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  public projects: Project[] | undefined;

  constructor(
    private _projectService: ProjectService,
    private _currentProjectService: CurrentProjectService,
    private router: Router
  ) { }

  ngOnInit() {
    this._projectService.getAllProjects().subscribe(projects => {
      this.projects = projects;
      console.log(this.projects);
      this.projects.forEach(x => console.log(x.id + " " + x.name))
    });
  }

  chooseProject(project: Project) {
    this._currentProjectService.setCurrentProject(project);
    this.router.navigate(['/projects/', project.id]);
  }
}
