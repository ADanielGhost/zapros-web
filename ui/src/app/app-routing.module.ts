import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MaincComponent} from "./component/mainc/mainc.component";
import {NotFoundComponent} from "./component/not-found/not-found.component";
import {UserComponent} from "./component/user/user.component";

const routes: Routes = [
  {path: '', component: MaincComponent},
  {path: 'user', component: UserComponent},
  {path: 'second', redirectTo: '/first', pathMatch: 'full'}, // second/kek/kek doesn't allowed
  {path: 'third', redirectTo: '/first', pathMatch: 'prefix'}, // third/lol/kek allowed
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
