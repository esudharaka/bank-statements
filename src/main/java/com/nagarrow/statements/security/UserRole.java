package com.nagarrow.statements.security;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),USER("ROLE_USER");

    private String roleName;
    UserRole(String userRole) {
        this.roleName = userRole;
    }

    public String getRoleName() {
        return roleName;
    }
}
