package com.hendisantika.schooladministrationsystem.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hendisantika.schooladministrationsystem.entity.Student;
import com.hendisantika.schooladministrationsystem.entity.Teacher;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : school-administration-system
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/12/20
 * Time: 15.05
 */
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails, Serializable {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User username.
     */
    @Column(name = "username", nullable = false, length = 16)
    private String username;

    /**
     * User password.
     */
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * User full name.
     */
    @Column(name = "fullName", nullable = false, length = 32)
    private String fullName;

    /**
     * Constructor to make a new instance.
     *
     * @param username User username.
     * @param password User password.
     * @param fullName User full name.
     */
    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Teacher> teachers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Student> students = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}