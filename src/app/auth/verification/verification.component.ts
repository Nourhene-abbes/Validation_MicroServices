import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/AuthenticationService';
import { TokenStorageService } from 'src/app/services/TokenStorageService';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {
code:string=""
  constructor(private authservice:AuthenticationService,private tokenservice:TokenStorageService,private route : Router) { }

  ngOnInit(): void {
  }
  verify(){
    let login =this.tokenservice.getUser()
    this.authservice.getMe(login).subscribe(res=>{
      this.authservice.verifyaccount(this.code,res).subscribe(res=>{
        console.log(res);
        
       
      })
    })
    window.alert("Your Account is Verified ! Welcome On Board !")
    this.route.navigate(['login'])
  }

}
