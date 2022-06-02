package tn.esprit.userservice.service;


import tn.esprit.userservice.model.User;

import java.util.List;

public interface IUserService {
    public User Authority(String login, String password);
    public String Signup(User us);
    public List<User> GetAllUsers();
    public List<User> GetAllAdmin();
    public User Update(User us);
    public String VerifyMyaccount(User u,String verifcode);
    public int ResetPassword(String mail);
    public boolean isVerified(String login,String pwd);
    public boolean VerifLogin (String login,long id);
    public boolean VerifEmail (String Email,long id);
    public boolean exist(String login,String pwd);
    public void AddCode(String code,User user_id) ;
    public void mailling(String mail,String message);
    public void sendsms(String str,int body);
    public User GetUser(long id);
    public long SelectByid(String login);
    public void Delete(long id);
    public void ifNotverifiedVerif(long id,String verification_code);
    public String UpdateRestPassword(int code,String password,long id);

    /*
    public String VerifyAccount();
    public Void AddAdmin();
    */

}