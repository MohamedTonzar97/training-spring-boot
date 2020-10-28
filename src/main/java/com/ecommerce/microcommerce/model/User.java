/*
package com.ecommerce.microcommerce.model;

import io.swagger.annotations.ApiModelProperty;
*/
/*import org.springframework.security.core.GrantedAuthority;*//*

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USER")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(
            notes = " Id",
            name = "id",
            dataType = "String",
            value = "test id"
    )
    private Integer userId;

    @ApiModelProperty(
            notes = " name of the student",
            name = "firstName",
            required = true,
            dataType = "String",
            value = "test name"
    )
    private String username;
    @ApiModelProperty(
            notes = " password of the student",
            name = "password",
            dataType = "String",
            value = "test name"
    )
    private String password;

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    private String role ;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//        list.add(new SimpleGrantedAuthority(this.getRole()));
//        return list;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}*/
