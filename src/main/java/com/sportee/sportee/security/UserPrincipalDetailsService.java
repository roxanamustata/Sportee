package com.sportee.sportee.security;

import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + s + ".");
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
