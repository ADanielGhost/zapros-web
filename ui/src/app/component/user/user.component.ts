import { Component, OnInit } from '@angular/core';
// @ts-ignore
import {User} from "../../app/type/user";
import {UserService} from "../../service/user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.styl']
})
export class UserComponent implements OnInit {

  public userOne: User | undefined;
  public userList: User[] | undefined;

  constructor(
    private _userService: UserService
  ) { }

  ngOnInit(): void {
  }

  public getOneUser() {
    const userId: number = 3;
    this._userService.getOneUser(userId).subscribe(x => {
     this.userOne = x;
    });
  }

  public getAllUsers() {
    this._userService.getAllUsers().subscribe(x => {
      this.userList = x;
    });
  }

  public addOneUser() {
    const user: User = {
      id: 6,
      name: 'keker6',
      email: 'lol6@lollll'
    }
    return this._userService.addOneUser(user);
  }

  public addManyUsers() {
    const users: User[] = [
      {
        id: 7,
        name: 'keker7',
        email: 'lol7@lollll'
      },
      {
        id: 8,
        name: 'keker8',
        email: 'lol8@lollll'
      }
    ];

    return this._userService.addManyUsers(users);
  }
}
