package net.guides.springboot.notificationsystem.util;

import net.guides.springboot.notificationsystem.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserBasedUserDetails implements UserDetails {
        private final User user;
        private final Collection<? extends GrantedAuthority> authorities;

        public UserBasedUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
            this.user = user;
            this.authorities = authorities;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
        }

        public String getPassword() {
            return this.user.getPassword();
        }


        public boolean isAccountNonExpired() {
            return true;
        }


        public boolean isCredentialsNonExpired() {
            return true;
        }


        public User getUser() {
            return this.user;
        }
    }

