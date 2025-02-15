package com.example.waffle_project.Service;

import com.example.waffle_project.Dto.CustomUserDetails;
import com.example.waffle_project.Entity.UserEntity;
import com.example.waffle_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDeatilsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDeatilsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity != null){
            return new CustomUserDetails(userEntity);
        }

        return null;
    }


}
