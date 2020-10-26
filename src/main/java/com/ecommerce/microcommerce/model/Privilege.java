/*
package com.ecommerce.microcommerce.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Privilege(String name) {
        this.name = name;
    }

    private String name;

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}*/
