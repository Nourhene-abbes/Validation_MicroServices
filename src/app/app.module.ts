import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ForgetpwdComponent } from './auth/forgetpwd/forgetpwd.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { VerificationComponent } from './auth/verification/verification.component';
import { ProfileComponent } from './profile/profile.component';
import { ChangepwdComponent } from './auth/changepwd/changepwd.component';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    AppComponent , LoginComponent , RegisterComponent , VerificationComponent , ForgetpwdComponent, ProfileComponent, ChangepwdComponent, HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
