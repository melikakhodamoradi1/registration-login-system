package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.util.UserBasedUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class Utils {

    public static String getUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        } else {
            return principal instanceof String ? (String) principal : null;
        }
    }
}
