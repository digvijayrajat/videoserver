package com.example.restservice;

import org.springframework.data.repository.CrudRepository;


public interface ContentRepository extends CrudRepository<Content, Long> {
    Content findByTitle(String title);
}