package com.example.trua_nay_an_gi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;

    @ManyToOne
    private AppUser appUser;

    @Column(columnDefinition = "BIT default 0")
    private boolean selected;
}
