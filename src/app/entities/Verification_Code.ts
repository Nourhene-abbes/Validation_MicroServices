import { User } from "./user";

export class Verification_Code {
    id : number;
    code : string;
    user_id : User

    constructor (){
        this.id = 0
        this.code=""
        this.user_id= new User()
        
    }
}