package com.example.backend.Controller;

import com.example.backend.Model.ContactFormModel;
import com.example.backend.Repository.ContactFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactFormController {

    @Autowired
    private ContactFormRepo contactRepository;

    @PostMapping("/contacts")
    public ResponseEntity<String> submitContact(@RequestBody ContactFormModel contact) {
        contactRepository.save(contact);
        return new ResponseEntity<>("Contact saved successfully", HttpStatus.CREATED);
    }
}
