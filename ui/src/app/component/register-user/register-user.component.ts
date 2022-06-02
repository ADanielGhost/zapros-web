import { Component, OnInit } from '@angular/core';
import {AlternativePackage} from "../../type/alternative-package";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectService} from "../../service/project.service";
import {User} from "../../type/user";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  public currentAP: AlternativePackage | undefined;
  //@ts-ignore
  public registrationForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private _projectService: ProjectService
  ) { }

  ngOnInit() {
    this.registrationForm = this.fb.group({
      name: [''],
      email: [''],
      methodType: ['ZAPROS_II'],
      threshold: ['']
    });
  }

  sendToBack() {
    const data = this.registrationForm.value;
    const user: User = {
      id: 0,
      name: data.name,
      email: data.email,
      methodType: data.methodType,
      threshold: data.threshold
    }

    const id = parseInt(<string> this.route.snapshot.paramMap.get('id'));
    this._projectService.registerUser(id, user).subscribe(x => {
      this.router.navigate(['/ask/', x.id]);
    });
  }
}
