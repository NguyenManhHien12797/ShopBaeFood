package com.example.trua_nay_an_gi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "account_role")
public class AccountRoleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppRoles role;
}
