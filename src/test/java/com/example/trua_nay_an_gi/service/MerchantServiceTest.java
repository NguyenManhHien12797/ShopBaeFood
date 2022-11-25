package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Account;
import com.example.trua_nay_an_gi.model.Merchant;
import com.example.trua_nay_an_gi.repository.IMerchantRepository;
import com.example.trua_nay_an_gi.service.seviceImpl.MerchantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MerchantServiceTest {

    private IMerchantRepository merchantRepository = Mockito.mock(IMerchantRepository.class);


    private IMerchantService merchantService = new MerchantServiceImpl(merchantRepository);

    @BeforeEach
    public void setup(){
        Account account = new Account();
        account.setId(1L);
        account.setUserName("Hien");
        Merchant merchant = new Merchant();
        merchant.setId(2L);
        merchant.setName("Nha hang ngan sao");
        merchant.setAccount(account);

       Merchant merchant1 = new Merchant();
        merchant1.setId(3L);
        merchant1.setName("Merchant");

        List<Merchant> merchantList = Arrays.asList(merchant, merchant1);
        List<Merchant> merchantList1 = Arrays.asList(merchant1);

        doReturn(Optional.of(merchant)).when(merchantRepository).findById(2L);
        doReturn(merchantList).when(merchantRepository).findAll();
        doReturn(merchantList1).when(merchantRepository).findAllMerchantAndNameContainer("Merchant");
    }



    @Test
    @DisplayName("finById return merchant with name is Nha hang ngan sao")
    void whenfindById_thenReturnMerchant(){
        Optional<Merchant> merchant1 = merchantService.findById(2L);
        String merchantName = "Nha hang ngan sao";
        assertThat(merchant1.get().getName().equals(merchantName));
    }

    @Test
    @DisplayName("finById 1L return merchant isPresent ")
    void whenfindById_thenReturnMerchantIsPresent(){
        Optional<Merchant> merchant1 = merchantService.findById(1L);
        assertThat(merchant1.isPresent()).isFalse();
    }

    @Test
    @DisplayName("findAllMerchant can return list is not null")
    void whenfindAll_thenReturnAllMerchant(){
        Iterable<Merchant> merchants = merchantService.findAll();
        assertThat(merchants).isNotNull();
    }

    @Test
    @DisplayName("saveMerchantToRegister")
    void whensaveMerchantToRegister_thenReturnMerchant(){
      merchantService.saveMerchantToRegister("HN","avatar","Merchant1", "09452323","pending", 1L);
      verify(merchantRepository, times(1)).saveMerchantToRegister("HN","avatar","Merchant1", "09452323","pending", 1L);
    }

    @Test
    @DisplayName("remove")
    void remove(){
        merchantService.remove(1L);
        verify(merchantRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("findAllContrainer can return list is merchant has name like Nha hang ngan sao")
    void whenFindAllContainer_thenReturnListMerchant(){
        List<Merchant> merchantList = merchantService.findAllContainer("Merchant");

        assertThat(merchantList.get(0).getName().equals("Merchant"));
    }

    @Test
    @DisplayName("updateMarchant can return merchant")
    void whenupdateMerchant_thenReturnMerchant(){
        Merchant merchant = new Merchant();
        merchant.setName("Nha hang bien");
        merchantService.updateMerchant(2L, merchant);
        verify(merchantRepository, times(1)).save(merchant);
    }

}
