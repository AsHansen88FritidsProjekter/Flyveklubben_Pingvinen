package com.example.backend.Repository;

import com.example.backend.Model.ContactFormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepo extends JpaRepository<ContactFormModel, Long> {
}
