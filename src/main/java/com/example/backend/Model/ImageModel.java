package com.example.backend.Model;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "images")
@Builder
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imageData", columnDefinition="LONGBLOB")
    private byte[] imageData;
}



