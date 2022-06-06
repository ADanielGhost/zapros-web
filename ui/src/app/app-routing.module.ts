import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {NotFoundComponent} from "./component/not-found/not-found.component";
import {ProjectComponent} from "./component/project/project.component";
import { ProjectOneComponent } from './component/project-one/project-one.component';
import {RegisterUserComponent} from "./component/register-user/register-user.component";
import {AskComponent} from "./component/ask/ask.component";
import {CheckValidComponent} from "./component/check-valid/check-valid.component";
import {ViewResultComponent} from "./component/view-result/view-result.component";

const routes: Routes = [
  {path: '', redirectTo: '/projects', pathMatch: 'full'},
  {path: 'projects', component: ProjectComponent},
  {path: 'projects/:id', component: ProjectOneComponent},
  {path: 'new/user/:id', component: RegisterUserComponent},
  {path: 'ask/:id', component: AskComponent},
  {path: 'check/valid/:id', component: CheckValidComponent},
  {path: 'view/result/:id', component: ViewResultComponent},
  // {path: 'second', redirectTo: '/projects', pathMatch: 'full'}, // second/kek/kek doesn't allowed
  // {path: 'third', redirectTo: '/projects', pathMatch: 'prefix'}, // third/lol/kek allowed
  {path: '**', redirectTo: '/not-found', pathMatch: 'full'},
  {path: 'not-found', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
