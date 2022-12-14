package com.example.trua_nay_an_gi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    private boolean isEnabled;
    @NotBlank
    private String email;
    private String otp;
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

    public Account(String userName, String password, boolean isEnabled, String email, Set<AccountRoleMap> accountRoleMapSet) {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.email = email;
        this.accountRoleMapSet = accountRoleMapSet;
    }

    public Account(String userName, String password, String email, boolean isEnabled) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isEnabled = isEnabled;
    }


}