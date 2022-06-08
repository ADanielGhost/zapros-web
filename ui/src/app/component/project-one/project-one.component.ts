import { Component, OnInit } from '@angular/core';
import {Project} from "../../type/project-type";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../service/project.service";
import {AlternativePackage} from "../../type/alternative-package";

@Component({
  selector: 'app-project-one',
  templateUrl: './project-one.component.html',
  styleUrls: ['./project-one.component.css']
})
export class ProjectOneComponent implements OnInit {

  public currentProject: Project | undefined;
  public isDataLoad: boolean = false;

  constructor(
    private _projectService: ProjectService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const id = parseInt(<string> this.route.snapshot.paramMap.get('id'));
    this._projectService.getProjectById(id).subscribe(x => {
      this.currentProject = x;
      this.isDataLoad = true;
    });
  }

  choosePackage(alternativePackage: AlternativePackage) {
    this.router.navigate(['/new/user', alternativePackage.id]);
  }
}
