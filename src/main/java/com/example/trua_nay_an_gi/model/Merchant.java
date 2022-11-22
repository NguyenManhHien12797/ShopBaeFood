package com.example.trua_nay_an_gi.model;

import com.example.trua_nay_an_gi.model.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotEmpty
    @NotBlank
    private String name;
//    @NotEmpty
//    @Pattern(regexp = "^[0](\\+\\d{1,3}\\s?)?((\\(\\d{3}\\)\\s?)|(\\d{3})(\\s|-?))(\\d{3}(\\s|-?))(\\d{3})(\\s?(([E|e]xt[:|.|]?)|x|X)(\\s?\\d+))?")
    @NotBlank
    private String phone;

//    @NotEmpty
    @NotBlank
    private String address;

    private String avatar;
    private String imageBanner;

//    @Column(columnDefinition = "TIME")
    private String openTime;

//    @Column(columnDefinition = "TIME")
    private String closeTime;

    private String status;


//    @Column(name = "isActive", columnDefinition = "boolean default true")
//    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;
}
