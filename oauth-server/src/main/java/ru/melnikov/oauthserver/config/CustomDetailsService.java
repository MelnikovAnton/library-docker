package ru.melnikov.oauthserver.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.melnikov.oauthserver.model.CustomUser;
import ru.melnikov.oauthserver.model.UserEntity;
import ru.melnikov.oauthserver.repo.UserRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Requested userDetails for {}",username);
        UserEntity userEntity = userRepo.findByUsername(username);
        return new CustomUser(userEntity);
    }
}