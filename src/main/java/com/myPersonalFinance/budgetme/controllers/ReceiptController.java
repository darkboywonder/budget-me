package com.myPersonalFinance.budgetme.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.myPersonalFinance.budgetme.data.ReceiptRepository;

import com.myPersonalFinance.budgetme.models.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;

//    @Autowired
//    private TagRepository tagRepository;

    @PostMapping(path = "/add", consumes = "application/json")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
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

            if (result.isEmpty()) {
                return ResponseEntity.notFound().build();


            } else {
                Receipt receipt = result.get();
               return ResponseEntity.ok(Arrays.asList(receipt));
            }
        }

    }


}
