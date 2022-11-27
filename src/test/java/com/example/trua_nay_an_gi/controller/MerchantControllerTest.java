//package com.example.trua_nay_an_gi.controller;
//
//
//import com.example.trua_nay_an_gi.model.Merchant;
//import com.example.trua_nay_an_gi.service.seviceImpl.MerchantServiceImpl;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(MerchantController.class)
//public class MerchantControllerTest{
//
//   @Autowired
//    private MockMvc mockMvc;
//
//   @MockBean
//    private MerchantServiceImpl merchantService;
//
//   @Test
//    public void getAllMerchant_thenReturnListMerchant() throws Exception{
//       Iterable<Merchant> merchants = merchantService.findAll();
//       when(merchantService.findAll()).thenReturn(merchants);
//   }
//
//
//}