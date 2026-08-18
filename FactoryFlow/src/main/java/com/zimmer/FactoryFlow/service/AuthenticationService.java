package com.zimmer.FactoryFlow.service;


import com.zimmer.FactoryFlow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String edv) throws UsernameNotFoundException {
            return userRepository.findByEdv(edv)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o EDV: " + edv));
        }
    }


