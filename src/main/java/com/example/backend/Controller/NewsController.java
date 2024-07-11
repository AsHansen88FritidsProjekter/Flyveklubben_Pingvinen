package com.example.backend.Controller;

import com.example.backend.Model.NewsModel;
import com.example.backend.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;


    @GetMapping
    public List<NewsModel> findAll() {
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public NewsModel findById(@PathVariable Long id) {
        return newsService.findById(id);
    }

    @PostMapping
    public NewsModel save(@RequestBody NewsModel post) {
        return newsService.save(post);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }

}
