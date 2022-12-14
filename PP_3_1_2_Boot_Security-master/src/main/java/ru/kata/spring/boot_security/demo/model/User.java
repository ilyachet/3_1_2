package ru.kata.spring.boot_security.demo.model;



import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 35, message = "Min 2 characters max 35")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 35, message = "Min 2 characters max 35")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Password should not be empty")
    @Column(name = "password")
    private String password;

    @Min(value = 0, message = "Value not less than 0")
    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.JOIN)
    private Collection<Role> roles;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(String name, String surname, String password, int age) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
    }

    public User(String name, String surname, String password, int age, Collection<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
