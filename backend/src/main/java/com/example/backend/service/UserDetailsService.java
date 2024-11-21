package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.model.UserPrincipal;
import com.example.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByFirstName(username);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found");

        }
        return new UserPrincipal(user);
    }
}
