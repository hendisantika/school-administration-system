package com.hendisantika.schooladministrationsystem.service.auth;

import com.hendisantika.schooladministrationsystem.entity.user.Authority;
import com.hendisantika.schooladministrationsystem.entity.user.UserRoleName;
import com.hendisantika.schooladministrationsystem.repository.user.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void save(UserRoleName userRoleName) {
        authorityRepository.saveAuth(userRoleName.toString());
    }

    public List<Authority> findById(Long id) {
        Authority authority = authorityRepository
                .findById(id).orElse(null);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }
}
