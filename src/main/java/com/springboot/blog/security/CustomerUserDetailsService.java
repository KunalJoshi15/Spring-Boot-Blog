package com.springboot.blog.security;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        com.springboot.blog.entity.User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()->new UsernameNotFoundException("User not found using the username or email"+usernameOrEmail));
        return new User(user.getEmail(),user.getPassword(),mapRolesAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
