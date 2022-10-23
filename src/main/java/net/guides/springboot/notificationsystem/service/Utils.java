package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.util.UserBasedUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


public class Utils {

    public static Long getUserIdFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserBasedUserDetails) {
            UserBasedUserDetails userDetails = (UserBasedUserDetails)principal;
            return userDetails.getUser().getId();
        } else {
            return principal instanceof Long ? (Long)principal : null;
        }
    }
}
