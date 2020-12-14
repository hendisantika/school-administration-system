package com.hendisantika.schooladministrationsystem.service.auth;

import com.hendisantika.schooladministrationsystem.repository.user.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/12/20
 * Time: 05.39
 */
@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
}
