package com.nagarrow.statements.util;

import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;
import com.nagarrow.statements.security.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUtils {

    private SecurityUtils() {

    }
    public static UserRole getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toUnmodifiableList());
        boolean isAdmin = roles.contains(UserRole.ADMIN.getRoleName());
        boolean isUser = roles.contains(UserRole.USER.getRoleName());
        if (isAdmin) {
            return UserRole.ADMIN;
        } else if (isUser) {
            return UserRole.USER;
        } else {
            throw new AccountServiceException(AppError.INVALID_USER_ROLE);
        }
    }
}
