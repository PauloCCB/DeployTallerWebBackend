package com.Plataforma_Educativa.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Plataforma_Educativa.model.entity.Usuario;
import com.Plataforma_Educativa.model.entity.UsuarioPerfil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    //User
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; // Roles

    //UserProfile
    private String firstName;
    private String lastName;
    private String dni;

   public static UserPrincipal create(Usuario user){
       //TODO: convert  List<Role> to List<GrantedAuthority>
      List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role-> new SimpleGrantedAuthority(role.getName().name())
                    ).collect(Collectors.toList());

      UsuarioPerfil profile = user.getUsuarioPerfil();

      return new UserPrincipal(
            user.getId(),
            user.getEmail(),
            user.getPassword(),authorities,

            profile.getFirstName(),
            profile.getLastName(),
            profile.getDni()
      );
   }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }//si es false o por defecto es como si la cuenta no tuviera acceso a nada

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
