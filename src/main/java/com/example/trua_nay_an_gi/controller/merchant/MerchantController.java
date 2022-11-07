package com.example.trua_nay_an_gi.controller.merchant;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;

    @GetMapping
    public ResponseEntity<Iterable<Merchant>> findAllMerchant(){
        Iterable<Merchant> merchants = merchantService.findAll();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant (@PathVariable Long id,@RequestBody Merchant merchant){
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if(!merchantOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        merchant.setId(merchantOptional.get().getId());
        merchant.setAccount(merchantOptional.get().getAccount());
        return new ResponseEntity<>(merchantService.save(merchant),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> findById(@PathVariable Long id) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(merchantOptional.get(), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Merchant> updateMerchant(@PathVariable Long id, @RequestBody Merchant newMerchant) {
//        Optional<Merchant> merchantOptional = merchantService.findById(id);
//        if (!merchantOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        newMerchant.setId(id);
//        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
//    }
//
    @PutMapping("/editMerchant/{id}")
    public ResponseEntity<Merchant> updateInformationMerchant(@PathVariable Long id, @RequestBody Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant newMerchant = merchantOptional.get();
        newMerchant.setId(id);
        newMerchant.setName(merchant.getName());
//        newMerchant.setDescription(merchant.getDescription());
        newMerchant.setAddress(merchant.getAddress());
        newMerchant.setPhone(merchant.getPhone());
        newMerchant.setOpenTime(merchant.getOpenTime());
        newMerchant.setCloseTime(merchant.getCloseTime());
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Merchant> saveMerchant(@RequestBody Merchant merchant) {
        return new ResponseEntity<>(merchantService.save(merchant), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Merchant> deleteMerchant(@PathVariable Long id) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        merchantService.remove(id);
        return new ResponseEntity<>(merchantOptional.get(), HttpStatus.NO_CONTENT);
    }
}
