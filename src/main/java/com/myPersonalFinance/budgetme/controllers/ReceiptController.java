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

    @PutMapping(path = "/edit", consumes = "application/json")
    public ResponseEntity<String> editReceipt(@RequestBody Receipt receipt) {
        try {
            receiptRepository.save(receipt);
            System.out.println(receipt.getAmount());
            return ResponseEntity.ok("Receipt updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during receipt edit.");
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<List<Receipt>> deleteReceipts(@RequestBody List<Integer> receiptIds) {
        try {
            for (Integer receiptId : receiptIds) {
                receiptRepository.deleteById(receiptId);
            }

            List<Receipt> updatedReceipts = receiptRepository.findAll();
            return ResponseEntity.ok(updatedReceipts);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Receipt>> searchReceiptsByTag(@RequestParam String tag) {
        List<Receipt> matchingReceipts = receiptRepository.findByCategoryContaining(tag);
        return ResponseEntity.ok(matchingReceipts);
    }

}