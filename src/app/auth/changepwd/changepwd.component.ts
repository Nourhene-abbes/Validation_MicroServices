import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { TokenStorageService } from 'src/app/services/TokenStorageService';

@Component({
  selector: 'app-changepwd',
  templateUrl: './changepwd.component.html',
  styleUrls: ['./changepwd.component.css']
})
export class ChangepwdComponent implements OnInit {
password:string="";code:number=0
  constructor(private authservice:AuthenticationService,private tokenservice:TokenStorageService,private route : Router) { }

  ngOnInit(): void {
  }
reset(){
  console.log(this.code);
  console.log(this.password);
    let email=this.tokenservice.getUser()
this.authservice.changePwd(this.code,this.password,email).subscribe(res=>{


})
window.alert('Your PASSWORD HAS BEEN CHANGED SUCCEFULLY !')
this.route.navigate(['login'])
}
}
