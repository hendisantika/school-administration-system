package com.hendisantika.schooladministrationsystem.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/12/20
 * Time: 05.32
 */
@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
}
