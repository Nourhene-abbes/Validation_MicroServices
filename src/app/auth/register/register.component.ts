import { Token } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/entities/user';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { TokenStorageService } from 'src/app/services/TokenStorageService';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  password:string="";passwordp:string="";login:string="";telephone:number=0;email:string="";full_name:string=""
  user = new User()
  userlogin = true;
  userregister = false;
  constructor(private authservice : AuthenticationService,private route : Router,private tokenservice:TokenStorageService) { }

  ngOnInit(): void {
  }
  user_register()
  {
    this.userlogin = false;
    this.userregister = true;
  }
  user_login()
  {
    this.userlogin = true;
    this.userregister = false;
  }
  register(){
    console.log(this.password);
    console.log(this.passwordp);
    
    this.user.full_name=this.full_name
    this.user.email=this.email
    this.user.telephone=this.telephone
    this.user.login=this.login
   
      this.user.password=this.password
      console.log(this.user);
      
      this.authservice.register(this.user).subscribe(res=>{
        console.log(res);
       
      })
      this.tokenservice.saveUser(this.user.login)
      window.alert("your registration was completed successfully!")
      this.route.navigate(['verify'])
  }

}
