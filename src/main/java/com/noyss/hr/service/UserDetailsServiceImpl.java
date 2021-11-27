package com.noyss.hr.service;

import com.noyss.hr.configs.security.UserSession;
import com.noyss.hr.entity.db.AppUser;
import com.noyss.hr.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    IAppUserRepository iAppUserRepository;

    @Autowired
    UserSession userSession;
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
      
        AppUser user = iAppUserRepository.findByUsername(username)
                  .orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
        );

        userSession.setUsername(user.getUsername());
 
        return UserPrinciple.build(user);
    }
}
