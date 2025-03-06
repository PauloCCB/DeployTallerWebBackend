package com.Plataforma_Educativa.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Plataforma_Educativa.model.entity.Usuario;
import com.Plataforma_Educativa.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private final UsuarioRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user =userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + email));

        return UserPrincipal.create(user);
    }
}