import { Component, OnInit } from '@angular/core';
import {AlternativePackage} from "../../type/alternative-package";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectService} from "../../service/project.service";
import {User} from "../../type/user";
import {ActivatedRoute, Router} from "@angular/router";
import {debugOutputAstAsTypeScript} from "@angular/compiler";

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  public currentAP: AlternativePackage | undefined;
  //@ts-ignore
  public registrationForm: FormGroup;

  public needAlert: boolean = false;
  public textAlert: string = 'Ошибка!';

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
      methodType: ['ARACE'],
      threshold: ['']
    });
  }

  sendToBack() {
    const data = this.registrationForm.value;
    this.needAlert = false;

    if (data.name.length == 0) {
      this.needAlert = true;
      this.textAlert = "Имя не может быть пустым!";
      return;
    }
    if (data.email.length == 0) {
      this.needAlert = true;
      this.textAlert = "Email не может быть пустым!";
      return;
    }
    let num: number = parseFloat(data.threshold.replace(',', '.'));
    if (data.methodType === 'ARACE') {
      if (isNaN(num)) {
        this.needAlert = true;
        this.textAlert = "Введенное значение для порога не число!";
        return;
      }
      if (num < 0 || num > 1) {
        this.needAlert = true;
        this.textAlert = "Введенное значение должно быть в пределах от 0 до 1!";
        return;
      }
    }

    const user: User = {
      id: 0,
      name: data.name,
      email: data.email,
      methodType: data.methodType == 'ZAPROS' ? 'ZAPROS_II' : 'ARACE',
      threshold: num
    }

    const id = parseInt(<string> this.route.snapshot.paramMap.get('id'));
    this._projectService.registerUser(id, user).subscribe(x => {
      this.router.navigate(['/ask/', x.id]);
    });
  }
}
