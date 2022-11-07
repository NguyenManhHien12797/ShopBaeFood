package com.example.trua_nay_an_gi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoleDTO {
   private Long id;
   private Long account_id;
   private Long role_id;
}
