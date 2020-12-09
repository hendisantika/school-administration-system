package com.hendisantika.schooladministrationsystem.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.15
 */
@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Role name.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="name")
    private UserRoleName name;


    /**
     * Get authority.
     *
     * @return authority of user.
     */
    @Override
    public String getAuthority() {
        return name.name();
    }

    /**
     * Get Role name.
     *
     * @return Role name.
     */
    @JsonIgnore
    public UserRoleName getName() {
        return name;
    }

    /**
     * Get role id.
     *
     * @return Id of the Authority.
     */
    @JsonIgnore
    public Long getId() {
        return id;
    }
}