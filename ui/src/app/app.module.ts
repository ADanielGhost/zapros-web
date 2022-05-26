import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaincComponent } from './component/mainc/mainc.component';
import { NotFoundComponent } from './component/not-found/not-found.component';
import {HttpClientModule} from "@angular/common/http";
import {BackendService} from "./service/backend.service";
import {UserService} from "./service/user.service";
import { UserComponent } from './component/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    MaincComponent,
    NotFoundComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    BackendService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
