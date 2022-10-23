package net.guides.springboot.notificationsystem.util;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;


    public interface UserDetails extends Serializable {
        Collection<? extends GrantedAuthority> getAuthorities();

        String getPassword();

        boolean isAccountNonExpired();

        boolean isCredentialsNonExpired();



}
