import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entities/user';
import { AuthenticationService } from '../services/AuthenticationService';
import { TokenStorageService } from '../services/TokenStorageService';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile = new User()
  upprofile=new User()
  saved=new User();
  login:string="";full_name:string="";telephone:number=0;zipCode:number=0;address:string="";email:string="";password:string=""
  constructor(private authService:AuthenticationService,private tokenservice:TokenStorageService,private router:Router) { }

  ngOnInit(): void {
   
    this.getProfile()
  }
  
  getProfile(){
    console.log(this.tokenservice.getUser());
    
    this.authService.getMe(this.tokenservice.getUser()).subscribe(res=>{
      this.profile=res
      this.saved=res
      console.log(this.profile);
      
    })
  }
  updateProfile(){
    let profileup=this.saved;
    console.log(profileup);
    
   

    
    console.log(this.address);
    
    if(this.address!=""){
      
      profileup.address=this.address;
      
      }
      console.log(this.email);
      if(this.email!=""){

        profileup.email=this.email
        
        }
        console.log(this.full_name);
        if(this.full_name!=""){

          profileup.full_name=this.full_name
          
          }
          console.log(this.login);
          if(this.login!=""){

            profileup.login=this.login
            
            }
            console.log(this.password);
            if(this.password!=""){

              profileup.password=this.password
              
              }
              console.log(this.telephone);
              if(this.telephone!=0){

                profileup.telephone=this.telephone
                
                }
                console.log(this.zipCode);
                if(this.zipCode!=0){

                  profileup.zipCode=this.zipCode
                  
                  }
 
   
    
    
   
   
    
    
    
    
    console.log(profileup);
    this.upprofile=profileup
    console.log(this.upprofile);
    
    this.authService.updateUser(profileup).subscribe(res=>{
      console.log(res);
      //this.reloadPage();
      
  
    })
  }
  reloadPage(): void {
    window.location.reload();
    }
}
