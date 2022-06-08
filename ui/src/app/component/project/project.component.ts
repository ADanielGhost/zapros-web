import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {Project} from "../../type/project-type";
import {Router} from "@angular/router";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  public projects: Project[] | undefined;
  public isDataLoad: boolean = false;

  constructor(
    private _projectService: ProjectService,
    private router: Router
  ) { }

  ngOnInit() {
    this._projectService.getAllProjects().subscribe(projects => {
      this.projects = projects;
      this.isDataLoad = true;
    });
  }

  chooseProject(project: Project) {
    this.router.navigate(['/projects/', project.id]);
  }
}
