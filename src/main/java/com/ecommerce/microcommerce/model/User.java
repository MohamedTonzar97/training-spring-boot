package com.ecommerce.microcommerce.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(nullable = false,length = 50)
    private String firstName;
    @Column(nullable = false,length = 50)
    private String lastName;
    @Column(nullable = false,length = 150)
    private String email;
    @Column(nullable = false,length = 50)
    private String password;
    @Column(nullable = true)
    private String emailverificationToken;
    @Column(nullable = true)
    private String emailverificationStatus;

    public String getEmailverificationToken() {
        return emailverificationToken;
    }

    public void setEmailverificationToken(String emailverificationToken) {
        this.emailverificationToken = emailverificationToken;
    }

    public String getEmailverificationStatus() {
        return emailverificationStatus;
    }

    public void setEmailverificationStatus(String emailverificationStatus) {
        this.emailverificationStatus = emailverificationStatus;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


        private boolean enabled;
        private boolean tokenExpired;
        @ManyToMany
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(
                        name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"))
        private Collection<Role> roles;
    }

