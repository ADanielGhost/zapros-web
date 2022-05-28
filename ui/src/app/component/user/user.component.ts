import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../../type/user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.styl']
})
export class UserComponent implements OnInit {

  public userList: User[] | undefined;

  constructor(
    private _userService: UserService
  ) { }

  ngOnInit(): void {
    this._userService.getAllUsers().subscribe(x => {
      this.userList = x;
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
    this._userService.addOneUser(user).subscribe();
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

    this._userService.addManyUsers(users).subscribe();
  }
}
