package com.hendisantika.schooladministrationsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/12/20
 * Time: 05.24
 */
@Component
public class TokenHelper {
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    @Value("${app.name}")
    private String APP_NAME;
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;
    @Value("${jwt.header}")
    private String AUTH_HEADER;
    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;
    @Autowired
    private UserDetailsService userDetailsService;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

}
