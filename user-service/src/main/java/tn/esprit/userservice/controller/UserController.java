package tn.esprit.userservice.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.userservice.model.JwtRequest;
import tn.esprit.userservice.model.User;
import tn.esprit.userservice.service.ServiceUser;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {


    @Autowired
    private ServiceUser srvUsr;


    /// Sign up user
    @PostMapping("/Signup")
    @ResponseBody
    public String Adduser( User us) {
        return srvUsr.Signup(us);

    }
    /// Sign up user
    @PostMapping("/SignupClient")
    @ResponseBody
    public String AdduserClient( User us) {
        return srvUsr.SignupClient(us);

    }

    // Get All
    @GetMapping("/list")
    @ResponseBody
    public List<User> getAllUsers(){

        List<User> list = srvUsr.GetAllUsers();
        System.out.println("we are in controller");
        return list;
    }

    // Get All Admin
    @GetMapping("Admins")
    @ResponseBody
    public List<User> getAllAdmin(){

        List<User> list = srvUsr.GetAllAdmin();
        if (list != null && list.isEmpty()) {

            return null;
        }
        return list;
    }

    // Login
    @PostMapping("/signin")
    public String login(@Valid@RequestBody JwtRequest loginRequest) {
    System.out.println("nchoufou el token kifeh");
    System.out.println(srvUsr.signin(loginRequest.getUsername(),loginRequest.getPassword()));

    return srvUsr.signin(loginRequest.getUsername(),loginRequest.getPassword());

    }

    //update
    @PutMapping("/modify")
    @ResponseBody
    public User modifyUser( User user) {
        return srvUsr.Update(user);
    }
    @DeleteMapping("/suppression/{id}")
    @ResponseBody
    public String SupprimerUser( @PathVariable("id") long id) {
        srvUsr.Delete(id);
        return "User Deleted successfully !";
    }
    //Get user infos
    @GetMapping("c")
    @ResponseBody
    public User userInfo( @PathVariable("id") long id) {
        return srvUsr.GetUser(id);

    }
    @GetMapping("/resetPassword")
    @ResponseBody
    public int Reset(@RequestParam String email) {
        int x=srvUsr.ResetPassword(email);
        return ServiceUser.coderest;
    }
    @GetMapping("/ChangePassword")
    @ResponseBody
    public String Change(@RequestParam int number,@RequestParam String password,@RequestParam long id) {
        String x=srvUsr.UpdateRestPassword(number,password,id);
        return x;
    }
    @GetMapping("/VerifyAccount")
    @ResponseBody
    public String verify( User u, @RequestParam String Code) {
        return srvUsr.VerifyMyaccount(u, Code);
    }
    @GetMapping("/isVerified")
    @ResponseBody
    public boolean isverify(User u) {
        return srvUsr.isVerified(u.getLogin(),u.getPassword());
    }
    @RequestMapping("/hello")
    public String hello() {
        return "helloworld";

    }


}