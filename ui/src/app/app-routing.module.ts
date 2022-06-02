import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {NotFoundComponent} from "./component/not-found/not-found.component";
import {UserComponent} from "./component/user/user.component";
import {ProjectComponent} from "./component/project/project.component";
import { ProjectOneComponent } from './component/project-one/project-one.component';
import {RegisterUserComponent} from "./component/register-user/register-user.component";
import {AskComponent} from "./component/ask/ask.component";

const routes: Routes = [
  {path: '', redirectTo: '/projects', pathMatch: 'full'},
  {path: 'user', component: UserComponent},
  {path: 'projects', component: ProjectComponent},
  {path: 'projects/:id', component: ProjectOneComponent},
  {path: 'new/user/:id', component: RegisterUserComponent},
  {path: 'ask/:id', component: AskComponent},
  {path: 'second', redirectTo: '/projects', pathMatch: 'full'}, // second/kek/kek doesn't allowed
  {path: 'third', redirectTo: '/projects', pathMatch: 'prefix'}, // third/lol/kek allowed
  {path: '**', redirectTo: '/not-found', pathMatch: 'full'},
  {path: 'not-found', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
