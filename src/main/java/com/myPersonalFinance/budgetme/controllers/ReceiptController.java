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

//    @Autowired
//    private TagRepository tagRepository;

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
//    @GetMapping("/add-tag")
//    public ResponseEntity<String> addTagToReceipt(@RequestParam(required = false) Integer receiptId, @RequestParam(required = false) Integer tagId) {
//        try {
//            Optional<Receipt> receiptResult = receiptRepository.findById(receiptId);
//            Receipt receipt = receiptResult.orElseThrow(); // Assuming receipt must exist
//
//            Optional<Tag> tagResult = tagRepository.findById(tagId);
//            Tag tag = tagResult.orElseThrow(); // Assuming tag must exist
//
//            if (!receipt.getTags().contains(tag)) {
//                receipt.addTag(tag);
//                receiptRepository.save(receipt);
//            }
//
//            return ResponseEntity.ok("Tag added to receipt");
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding tag to receipt");
//        }
//    }
}