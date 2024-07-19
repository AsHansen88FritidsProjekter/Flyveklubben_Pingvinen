package com.example.backend.Controller;

import com.example.backend.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.MediaType;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

    @RestController
    @RequestMapping("/api/image")
    @RequiredArgsConstructor
    @CrossOrigin("http://localhost:63342")
    public class ImageController {


        private final ImageService imageService;

        @PostMapping
        public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
            String uploadImage = imageService.uploadImage(file);
            return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
        }

        @GetMapping("/{fileName}")
        public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
            try {
                // Decode the URL-encoded filename
                String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.toString());
                byte[] imageData = imageService.downloadImage(decodedFileName);
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.IMAGE_PNG) // Set the appropriate media type
                        .body(imageData);
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name");
            }
        }

        @GetMapping("/images")
        public ResponseEntity<List<String>> listImages() {
            List<String> imageFiles = imageService.listImages();
            return ResponseEntity.status(HttpStatus.OK).body(imageFiles);
        }

        @DeleteMapping("/{fileName}")
        public ResponseEntity<?> deleteImage(@PathVariable String fileName) {
            try {
                // Decode the URL-encoded filename
                String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.toString());
                boolean isDeleted = imageService.deleteImage(decodedFileName);
                if (isDeleted) {
                    return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
                }
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name");
            }
        }
    }







