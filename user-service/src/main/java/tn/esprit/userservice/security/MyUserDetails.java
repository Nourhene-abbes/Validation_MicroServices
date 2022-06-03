package tn.esprit.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.userservice.model.User;
import tn.esprit.userservice.repository.UserRepository;








@Service
public class MyUserDetails implements UserDetailsService {



@Autowired
private UserRepository userRepository;



@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
final User user = userRepository.findByUserName(username);



if (user == null) {
throw new UsernameNotFoundException("User '" + username + "' not found");
}



return org.springframework.security.core.userdetails.User//
.withUsername(username)//
.password(user.getPassword())//
.accountExpired(false)//
.accountLocked(false)//
.credentialsExpired(false)//
.disabled(false)//
.build();
}



}