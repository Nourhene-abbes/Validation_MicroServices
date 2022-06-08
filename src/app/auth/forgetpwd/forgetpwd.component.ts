import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { TokenStorageService } from 'src/app/services/TokenStorageService';

@Component({
  selector: 'app-forgetpwd',
  templateUrl: './forgetpwd.component.html',
  styleUrls: ['./forgetpwd.component.css']
})
export class ForgetpwdComponent implements OnInit {
email:string=""
  constructor(private authservice:AuthenticationService,private router:Router,private tokenservice:TokenStorageService) { }

  ngOnInit(): void {
  }
  requestpwd(){
    console.log(this.email);
    
    this.authservice.resetPwd(this.email).subscribe(res=>{
      console.log(res);
      this.tokenservice.saveUser(this.email)
      window.alert("An SMS Is sent to you check your phone and reset you password")
      this.router.navigate(['changepwd'])
    
    })
  }

}
