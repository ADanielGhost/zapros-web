import { Component, OnInit } from '@angular/core';
import {Project} from "../../type/projects";
import {CurrentProjectService} from "../../service/current-project.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../service/project.service";

@Component({
  selector: 'app-project-one',
  templateUrl: './project-one.component.html',
  styleUrls: ['./project-one.component.css']
})
export class ProjectOneComponent implements OnInit {

  public curProject: Project | undefined;

  constructor(
    private _currentProjectService: CurrentProjectService,
    private _projectService: ProjectService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const id = parseInt(<string> this.route.snapshot.paramMap.get('id'));
    this._projectService.getProjectById(id).subscribe(x => {
      this.curProject = x;
    })
  }
}
