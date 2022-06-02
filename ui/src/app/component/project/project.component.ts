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

  constructor(
    private _projectService: ProjectService,
    private router: Router
  ) { }

  ngOnInit() {
    this._projectService.getAllProjects().subscribe(projects => {
      this.projects = projects;
    });
  }

  chooseProject(project: Project) {
    this.router.navigate(['/projects/', project.id]);
  }
}
