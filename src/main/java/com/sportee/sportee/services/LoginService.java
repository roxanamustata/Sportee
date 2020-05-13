package com.sportee.sportee.services;


import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User ai = userRepository.findByUserName(s)
                .orElseThrow(() -> new UsernameNotFoundException(s));
        return new CustomUserDetails(ai);
    }

//    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String userRole = user.getRole().map((role) -> role.getName()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }
}
