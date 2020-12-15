package com.hendisantika.schooladministrationsystem.security.auth;

import com.hendisantika.schooladministrationsystem.security.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/12/20
 * Time: 05.33
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    public static final String ROOT_MATCHER = "/";
    public static final String HTML_MATCHER = "/**/*.html";
    public static final String CSS_MATCHER = "/**/*.css";
    public static final String LOGIN_MATCHER = "/auth/login";
    public static final String LOGOUT_MATCHER = "/auth/logout";
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    UserDetailsService userDetailsService;

    private final List<String> pathsToSkip = Arrays.asList(
            ROOT_MATCHER,
            HTML_MATCHER,
            CSS_MATCHER,
            LOGIN_MATCHER,
            LOGOUT_MATCHER
    );

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authToken = tokenHelper.getToken(httpServletRequest);
        if (authToken != null && !skipPathRequest(httpServletRequest, pathsToSkip)) {
            try {
                String username = tokenHelper.getUsernameFromToken(authToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
