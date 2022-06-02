import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './component/not-found/not-found.component';
import {HttpClientModule} from "@angular/common/http";
import {BackendService} from "./service/backend.service";
import { UserComponent } from './component/user/user.component';
import { MainHeaderComponent } from './component/main-header/main-header.component';
import { ProjectComponent } from './component/project/project.component';
import { ProjectOneComponent } from './component/project-one/project-one.component';
import { RegisterUserComponent } from './component/register-user/register-user.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AskComponent } from './component/ask/ask.component';

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    UserComponent,
    MainHeaderComponent,
    ProjectComponent,
    ProjectOneComponent,
    RegisterUserComponent,
    AskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    BackendService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
