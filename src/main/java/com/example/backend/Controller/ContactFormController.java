package com.example.backend.Controller;

import com.example.backend.Model.ContactFormModel;
import com.example.backend.Repository.ContactFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactFormController {

    @Autowired
    private ContactFormRepo contactRepository;

    @PostMapping("/contacts")
    public ResponseEntity<Map<String, String>> submitContact(@RequestBody ContactFormModel contact) {
        contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Contact saved successfully"));
    }

    @GetMapping("/contactinfo")
    public ResponseEntity<List<ContactFormModel>> getAllContacts() {
        List<ContactFormModel> contacts = contactRepository.findAll();
        return ResponseEntity.ok(contacts);
    }
}

