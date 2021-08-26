package com.example.restservice;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentService {
    Content saveTodo(String title, String description, MultipartFile file);

    byte[] downloadTodoImage(Long id);

    List<Content> getAllTodos();

    AdMedia addMedia(Long id, Long timestamp,String Title, String link, MultipartFile file);

    List<AdMedia> getAllMedia(Long id);

    byte[] downloadMedia(Long id);
}