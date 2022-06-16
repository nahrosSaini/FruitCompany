package com.saini.company.Service;

import com.saini.company.DTO.UserRegistrationDTO;
import com.saini.company.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User saveUser(UserRegistrationDTO userRegistrationDTO);
}
