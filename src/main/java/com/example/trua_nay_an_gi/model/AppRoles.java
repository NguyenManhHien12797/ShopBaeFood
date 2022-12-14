package com.example.trua_nay_an_gi.model;


import com.example.trua_nay_an_gi.model.AccountRoleMap;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class AppRoles implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private Set<AccountRoleMap> accountRoleMapSet;

    @Override
    public String getAuthority() {
        return name;
    }

    public AppRoles(String name) {
        this.name = name;
    }
}
