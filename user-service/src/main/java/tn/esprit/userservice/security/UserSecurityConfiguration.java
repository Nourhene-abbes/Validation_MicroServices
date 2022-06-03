package tn.esprit.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.esprit.userservice.service.ServiceUser;



@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter  {

   
   
    @Autowired
    private ServiceUser srv;
   
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserDetailsService jwtUserDetailsService;



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }
   


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        super.configure(auth);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Disable CSRF (cross site request forgery)
    	http.cors().and().csrf().disable();



    	// No session will be created or used by spring security
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



    	// Entry points
    	http.authorizeRequests()//
    	.antMatchers("**").permitAll()//
    	.antMatchers("/users/signup").permitAll()//
    	 .antMatchers("/h2/**/**").permitAll()
    	// Disallow everything else..
    	.anyRequest().authenticated();
    	http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    	
    	
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
    }

}