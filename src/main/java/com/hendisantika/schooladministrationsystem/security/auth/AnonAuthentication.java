package com.hendisantika.schooladministrationsystem.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/12/20
 * Time: 05.28
 */
public class AnonAuthentication extends AbstractAuthenticationToken {

    public AnonAuthentication() {
        super(null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
