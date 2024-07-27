package com.example.backend.Service;

import com.example.backend.Model.NewsModel;
import com.example.backend.Repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepo newsRepository;

    @Override
    public List<NewsModel> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public NewsModel findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public NewsModel save(NewsModel post) {
        return newsRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public NewsModel update(Long id, NewsModel post) {
        return newsRepository.findById(id).map(existingPost -> {
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            // Update other fields as necessary
            return newsRepository.save(existingPost);
        }).orElse(null);
    }
}
