package com.saini.company.Service;

import com.saini.company.DTO.UserRegistrationDTO;
import com.saini.company.Models.Role;
import com.saini.company.Models.User;
import com.saini.company.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(UserRegistrationDTO userRegistrationDTO) {

        User user= new User(
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastname(),
                userRegistrationDTO.getEmail(),
                bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()),
                Arrays.asList(new Role("ADMIN"),new Role("USER"))
        );
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(username);
        if(username==null){
            throw new UsernameNotFoundException("Invalid User Name or Password..!!");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
