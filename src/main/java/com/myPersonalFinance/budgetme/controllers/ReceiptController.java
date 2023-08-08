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

import java.util.Optional;


@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;
//    @Autowired
//    private TagRepository tagRepository;


    @PostMapping(path="/add", consumes = "application/json")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
    public ResponseEntity<String> saveReceipts(@RequestBody Receipt receipt) {

        try {

            receiptRepository.save(receipt);

            return ResponseEntity.ok("Receipt added");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding receipt");
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity<Receipt> getReceiptByTitle(@PathVariable String title, @RequestBody Receipt receipt) {

        receiptRepository.findByTitle(title);

        if (receipt != null) {
            return ResponseEntity.ok(receipt);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Receipt> displayReceipts (@PathVariable Integer receiptId, @RequestBody Receipt receipt) {

        receiptRepository.findAll();
        receiptRepository.findById(receiptId);


        if (receipt != null) {
            return ResponseEntity.ok(receipt);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    public ResponseEntity<Receipt> editReceipt(){
//
//    }
//
//    @GetMapping("/delete")
//    public String displayDeleteReceipt(Model model){
//        model.addAllAttributes("receipt", Receipt.getAll());
//            return "receipt/delete-receipt";
//    }
}
