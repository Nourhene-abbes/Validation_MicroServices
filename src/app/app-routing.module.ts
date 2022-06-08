import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChangepwdComponent } from './auth/changepwd/changepwd.component';
import { ForgetpwdComponent } from './auth/forgetpwd/forgetpwd.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { VerificationComponent } from './auth/verification/verification.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
{path: 'register', component: RegisterComponent},
{path: 'login', component: LoginComponent},
{path: 'verify', component: VerificationComponent},
{path: 'forgotpwd', component: ForgetpwdComponent},
{path: 'profile', component: ProfileComponent},
{path: 'changepwd', component: ChangepwdComponent},
{path: 'book', component: HomeComponent},
{path: '', redirectTo: 'login',pathMatch: 'full' },

{path: '**', redirectTo: 'login' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
