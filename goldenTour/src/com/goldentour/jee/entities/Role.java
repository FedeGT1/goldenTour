package com.goldentour.jee.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    public static final long USER_ROLE_USER = 2L;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idRole;

    @Column(name = "name", nullable = false)
    private String name;

    private Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRolename() {
        return name;
    }

    public void setRolename(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role tmp = (Role) obj;
        return idRole.equals(tmp.getIdRole());
    }

}