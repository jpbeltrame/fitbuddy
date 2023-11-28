package com.example.fitbuddy.security;


import com.example.fitbuddy.Entities.UserFitbuddy;
import com.example.fitbuddy.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserFitbuddy userFitbuddy = userRepository.findUserByEmail(username);

        //TO-DO fix roles
        List<String> list = Arrays.asList("user", "temp");

        if (userFitbuddy != null) {
            return new User(
                    userFitbuddy.getUsername(),
                    userFitbuddy.getPassword(),
                    list.stream().map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
