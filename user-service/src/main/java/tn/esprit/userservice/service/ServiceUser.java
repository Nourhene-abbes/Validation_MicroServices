package tn.esprit.userservice.service;


import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import tn.esprit.userservice.model.User;
import tn.esprit.userservice.model.Verification_Code;
import tn.esprit.userservice.repository.UserRepository;
import tn.esprit.userservice.repository.VerificationCodeRepository;


@Service
public class ServiceUser implements IUserService{
    @Autowired
    UserRepository UserRepo;
    @Autowired
    VerificationCodeRepository vcRepo;
    public static int  coderest=0;
    public static int  idverif=0;

    private static final Logger L=LogManager.getLogger(ServiceUser.class);
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User Authority(String login, String password)  {
        User logged= UserRepo.Auth(login);
        if(bCryptPasswordEncoder.matches(password,logged.getPassword())){
            return logged;
        }

        return null;
    }

    @Override
    public String Signup(User us) {
        // TODO Auto-generated method stub
        L.info(us);
        if(UserRepo.Existmail(us.getEmail(),us.getLogin())==false) {
            String encodedPassword = bCryptPasswordEncoder.encode(us.getPassword());
            String verifCode = bCryptPasswordEncoder.encode(us.getFull_name());

            us.setPassword(encodedPassword);
            User x=UserRepo.save(us);

            mailling(us.getEmail(),"Your verification code is : "+verifCode );
            return "User added successfully !";
        }
        return "User already Exist";
    }

    public String SignupClient(User us) {
        // TODO Auto-generated method stub
        L.info(us);
        if(UserRepo.Existmail(us.getEmail(),us.getLogin())==false) {
            String encodedPassword = bCryptPasswordEncoder.encode(us.getPassword());
            String verifCode = bCryptPasswordEncoder.encode(us.getFull_name());
            us.setRole(0);
            us.setPassword(encodedPassword);
            User x=UserRepo.save(us);

            mailling(us.getEmail(),"Your verification code is : "+verifCode );
            return "User added successfully !";
        }
        return "User already Exist";
    }
    @Override
    public List<User> GetAllUsers() {
        return (List<User>) UserRepo.findAll();
    }

    @Override
    public List<User> GetAllAdmin() {
        return (List<User>) UserRepo.WhereAdmin();
    }

    @Override
    public User Update(User us) {
        if(UserRepo.findById(us.getId())!=null) {
            String encodedPassword = bCryptPasswordEncoder.encode(us.getPassword());
            // TODO Auto-generated method stub
            us.setPassword(encodedPassword);
            UserRepo.save(us);
            return us;
        }
        return null;
    }

    @Override
    public int ResetPassword(String email) {
        // TODO Auto-generated method stub
        if(UserRepo.sms(email)==null) {
            return 0;
        }
        int ran = new Random().nextInt((90000-10000)+1)+10000;
        int tel=UserRepo.sms(email).getTelephone();
        ServiceUser.coderest=ran;
        sendsms(String.valueOf(tel),ran);
        return ran;
    }

    @Override
    public boolean isVerified(String login, String pwd) {
        if(UserRepo.isverified(login)==null)
            return false;
        return true;
    }

    @Override
    public boolean VerifLogin(String login, long id) {
        // TODO Auto-generated method stub
        if(UserRepo.findByLogin(login,id)!=null)
            return true;

        return false;
    }

    @Override
    public boolean exist(String login, String pwd) {
        // TODO Auto-generated method stub
        if(UserRepo.existence(login, pwd))
            return true;

        return false;
    }

    @Override
    public void AddCode(String code, User user_id) {
        // TODO Auto-generated method stub
        Verification_Code Vc=new Verification_Code();
        Vc.setCode(code);
        Vc.setUser_id(user_id);

        vcRepo.save(Vc);
    }

    @Override
    public void mailling(String mail,String message) {
        final String username = "nourhene.abbes@esprit.tn";
        final String password = "lemtswrurzsowvoc";
        String fromEmail = "nourhene.abbes@esprit.tn";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            msg.setSubject("User Verification");
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(message);
            emailContent.addBodyPart(textBodyPart);
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unused")
	@Override
    public void sendsms(String str,int body) {
        Twilio.init("ACc84c5a207336b46ee6d4f2bc6954b86a", "d983c46f68a2f6a7e27a5f6cca9df235");
        try {
        	 com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                    new PhoneNumber("+216"+str), // To number
                    new PhoneNumber("+19378065252"), // From number
                    "Verification code to reset password is :"+ body
            ).create();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public User GetUser(long id) {
        return UserRepo.findById(id);
        // TODO Auto-generated method stub

    }

    @Override
    public boolean VerifEmail(String Email, long id) {
        // TODO Auto-generated method stub
        if(UserRepo.findByEmail(Email,id)!=null)
            return true;

        return false;
    }

    @Override
    public long SelectByid(String login) {
        // TODO Auto-generated method stub

        return UserRepo.SelectByid(login);
    }

    @Override
    public void Delete(long id) {
        // TODO Auto-generated method stub
        User x=UserRepo.findById(id);
        if(x!=null) {
            vcRepo.DeletedVerific(x);



            UserRepo.deleteById(id);
        }
    }


    @Override
    public void ifNotverifiedVerif(long id,String verification_code) {
        // TODO Auto-generated method stub
        if(vcRepo.CheckVerification(verification_code,id)==null) {

            AddCode(verification_code,UserRepo.findById(id) );
        }
    }

    @Override
    public String UpdateRestPassword(int code, String password, long id) {
        // TODO Auto-generated method stub
        User t=UserRepo.findById(id);
        if(t==null) {
            return "User not found";
        }
        if(ServiceUser.coderest==code) {
            t.setPassword(bCryptPasswordEncoder.encode(password));
            UserRepo.save(t);
            return "Password updated successfully !";
        }

        return "wrong code";
    }

    @Override
    public String VerifyMyaccount(User u, String verifcode) {
        // TODO Auto-generated method stub
        if(isVerified(u.getLogin(),u.getPassword()))
            return "you are already verified";

        else if(bCryptPasswordEncoder.matches(UserRepo.Auth(u.getLogin()).getFull_name(),verifcode) && (UserRepo.Auth(u.getLogin())!=null )) {
            AddCode(verifcode, UserRepo.Auth(u.getLogin()));
            return "you have been verified";




        }
        return "Something went wrong";
    }


}