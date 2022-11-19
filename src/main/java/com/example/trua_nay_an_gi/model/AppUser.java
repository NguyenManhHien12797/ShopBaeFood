package com.example.trua_nay_an_gi.model;

import com.example.trua_nay_an_gi.model.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    private String avatar;
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;
}
