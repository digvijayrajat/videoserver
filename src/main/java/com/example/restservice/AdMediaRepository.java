package com.example.restservice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdMediaRepository extends CrudRepository<AdMedia, Long> {
    Optional<AdMedia> findById(Long id);

    List<AdMedia> findAllByVideoId(Long videoId);
}