package com.example.backend.Service;

import com.example.backend.Model.NewsModel;

import java.util.List;

public interface NewsService {
    List<NewsModel> findAll();
    NewsModel findById(Long id);
    NewsModel save(NewsModel post);
    void deleteById(Long id);
}
