export class User {
id : number;
full_name : string;
email : string;
telephone : number;
login : string;
password : string;
role : number;
address : string;
zipCode : number;

constructor(){
    this.id = 0
    this.full_name = ""
    this.email = ""
    this.telephone = 0
    this.login= ""
    this.password = ""
    this.role=0
    this.address=""
    this.zipCode=0
}
}