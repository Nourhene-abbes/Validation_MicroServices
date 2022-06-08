
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtRequest } from '../entities/JwtRequest';
import { User } from '../entities/user';
import decode from 'jwt-decode';
import { TokenStorageService } from './TokenStorageService';
const AUTH_API = 'http://192.168.249.1:8763/user-service/users/';
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const formData = new FormData()
@Injectable({
providedIn: 'root'
})
export class AuthenticationService {
user=new User();
constructor(private httpClient: HttpClient,private tokenService:TokenStorageService) { }


decode(){
    console.log(this.tokenService.getToken());
    
    console.log(decode(this.tokenService.getToken()));
    
    return decode(this.tokenService.getToken());
    }


login(credentials:JwtRequest):Observable<any> {
    const creds = credentials.username + ":" + credentials.password;
    const headers = new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic" + btoa(creds)
        });
console.log(credentials);
return this.httpClient.post(AUTH_API + 'signin',credentials, {
    headers: headers,
    responseType: 'text'
    });
    }
register(user:User){
console.log("hana fl auth service");
console.log(user);
return this.httpClient.post(AUTH_API + 'SignupClient',user)
}

logOut() {
sessionStorage.removeItem('auth-user')
}

isUserLoggedIn() {
    let user = !!this.tokenService.getToken();
    console.log(user);
    
    console.log((user === null))
    return user;
    }

    getUserbyId(id:number){
        return this.httpClient.get<User>(AUTH_API+'informationUser/'+id)
        }
        updateUser(user:User){
        return this.httpClient.put<User>(AUTH_API+"modify/",user)
        }
        resetPwd(email:string){
            let params = new HttpParams().set('email', email);
            return this.httpClient.get<number>(AUTH_API+"resetPassword",{params:params})

        }
        changePwd(number:number,password:string,email:string){
            let params = new HttpParams().append("number", number).append("password",password).append("email",email)
            // params.set('number', number)
            // params.set('password',password)
            // params.set('email',email)
            return this.httpClient.get<number>(AUTH_API+"ChangePassword",{params:params})

        }

        verifyaccount(code:string,user:User){
            console.log(user.id);
            
            let params = new HttpParams().append("Code", code).append("id",user.id)
            // params.set('code', code);
            // params.set('id',user.id)e
            return this.httpClient.get<string>(AUTH_API+"VerifyAccount",{ params: params })
        }
        isverified(id : number){
            let params = new HttpParams().set('id', id);
            return this.httpClient.get<string>(AUTH_API+"isVerified",{ params: params })
        }

        getMe(username : string){
            let params = new HttpParams().set('username', username);
            return this.httpClient.get<User>(AUTH_API+"me",{ params: params })
        }

      

        }



