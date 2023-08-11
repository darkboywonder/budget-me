package com.myPersonalFinance.budgetme.controllers;

import com.myPersonalFinance.budgetme.data.ReceiptRepository;
import com.myPersonalFinance.budgetme.models.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> addReceipts(@RequestBody Receipt receipt) {

        try {

            receiptRepository.save(receipt);

            return ResponseEntity.ok("Receipt added");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding receipt");
        }
    }


    @GetMapping("/view")
    public ResponseEntity<List<Receipt>> viewReceipts(@RequestParam(required = false) Integer receiptId) {

        if (receiptId == null) {

           List<Receipt> allReceipts = receiptRepository.findAll();
           return ResponseEntity.ok(allReceipts);

        } else {
            Optional<Receipt> result = receiptRepository.findById(receiptId);
            //storing the Optional object that is returned from the data repository when .findById is called
            if (result.isEmpty()) {
                return ResponseEntity.notFound().build();


            } else {
                Receipt receipt = result.get();
               return ResponseEntity.ok(Arrays.asList(receipt));
            }
        }

    }

    @PostMapping(path = "/view", consumes = "application/json")
    public ResponseEntity<String> deleteReceipt(@RequestBody Integer id) {
        try {
            Optional<Receipt> receiptOptional = receiptRepository.findById(id);
            if (receiptOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Receipt receipt = receiptOptional.get();
            receiptRepository.delete(receipt);
            receiptRepository.deleteById(id);

            return ResponseEntity.ok("Receipt deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting receipt");
        }




//        try {
//
//            Optional<Receipt> receiptOptional = receiptRepository.findById(id);
//            if (receiptOptional.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            Receipt receipt = receiptOptional.get();
//            receiptRepository.delete(receipt);
//
//
//            return ResponseEntity.ok("Receipt deleted");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting receipt");
//        }
    }
}