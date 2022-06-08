import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtRequest } from 'src/app/entities/JwtRequest';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { TokenStorageService } from 'src/app/services/TokenStorageService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username : string =""; password : string = "";cred = new JwtRequest();token:string=""
  constructor(private authService :AuthenticationService,private tokenService:TokenStorageService,private route:Router) { }

  ngOnInit(): void {

  }
  login(){
    this.cred.password=this.password
    this.cred.username=this.username
    console.log("username is "+this.cred.username);
    console.log("password is "+this.cred.password);
    this.authService.login(this.cred).subscribe(res=>{
      console.log(res);
      this.token=res
      console.log(this.token);
      this.tokenService.saveToken(this.token)
      
     let username :any =  this.authService.decode()
      console.log(username.sub);
      this.tokenService.saveUser(username.sub)
      this.route.navigate(['profile'])
    })
  }

}
