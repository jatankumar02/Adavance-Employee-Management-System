package com.jatan.ems.config;
import com.jatan.ems.model.*;
import com.jatan.ems.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seedUsers(UserRepository users,PasswordEncoder encoder){return args->{
  if(users.count()==0){
   AppUser admin=new AppUser(); admin.setUsername("admin"); admin.setPassword(encoder.encode("ChangeMe123!")); admin.setRole(Role.ADMIN); users.save(admin);
   AppUser hr=new AppUser(); hr.setUsername("hr"); hr.setPassword(encoder.encode("ChangeMe123!")); hr.setRole(Role.HR); users.save(hr);
  }
 };}
}
