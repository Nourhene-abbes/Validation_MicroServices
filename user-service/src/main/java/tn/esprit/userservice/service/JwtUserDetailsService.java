package tn.esprit.userservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.userservice.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository srv;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(srv.Auth(username)==null) {
            throw new UsernameNotFoundException("No user with: " + username);
        }
        return new User(srv.Auth(username).getLogin(),srv.Auth(username).getPassword(),new ArrayList<>());


    }
}