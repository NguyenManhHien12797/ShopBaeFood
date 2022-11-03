package com.example.trua_nay_an_gi.model.app_users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private boolean isEnabled;
    private String email;
    @OneToOne(mappedBy = "account")
    private AppUser user;
    @OneToOne(mappedBy = "account")
    private Merchant merchant;

    @OneToMany(mappedBy = "account")
    private Set<AccountRoleMap> accountRoleMapSet;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


}
