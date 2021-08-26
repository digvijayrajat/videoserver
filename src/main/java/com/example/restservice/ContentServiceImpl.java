package com.example.restservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository repository;
    private final AdMediaRepository adRepository;

    @Override
    public Content saveTodo(String title, String description, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String fileName = String.format("%s", file.getOriginalFilename());

        String rootPath = System.getProperty("user.dir");
        File dir = new File(rootPath + File.separator + "digiwebapp"+File.separator+"res"+File.separator+"img");
        if (!dir.exists())
            dir.mkdirs();


        File f1 = new File(dir+"/"+file.getOriginalFilename());
        try {
            file.transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Content.ContentBuilder contentBuilder= Content.builder();
        Content todo = contentBuilder
                .description(description)
                .title(title)
                .imagePath("path")
                .imageFileName(fileName)
                .build();
        repository.save(todo);
        return repository.findById(todo.getId()).orElse(null);
    }

    @Override
    public byte[] downloadTodoImage(Long id) {
        Content content = repository.findById(id).get();
        return download(content.getImageFileName());
    }


    public byte[] download(String path){
        String rootPath = System.getProperty("user.dir");

        File dir = new File(rootPath + File.separator + "digiwebapp"+File.separator+"res"+File.separator+"img");

        File file = new File(dir+"/"+path);

        byte[] bytes = new byte[(int) file.length()];

        // funny, if can use Java 7, please uses Files.readAllBytes(path)
        try(FileInputStream fis = new FileInputStream(file)){
            fis.read(bytes);
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public List<Content> getAllTodos() {
        List<Content> todos = new ArrayList<>();
        repository.findAll().forEach(todos::add);
        return todos;
    }

    @Override
    public AdMedia addMedia(Long id, Long timestamp,String title, String link, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String fileName = String.format("%s", file.getOriginalFilename());

        String rootPath = System.getProperty("user.dir");
        File dir = new File(rootPath + File.separator + "digiwebapp"+File.separator+"res"+File.separator+"img");
        if (!dir.exists())
            dir.mkdirs();


        File f1 = new File(dir+"/"+file.getOriginalFilename());
        try {
            file.transferTo(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdMedia.AdMediaBuilder adMediaBuilder= AdMedia.builder();
        AdMedia adMedia = adMediaBuilder
                .videoId(id)
                .Title(title)
                .timestamp(timestamp)
                .link(link)
                .filePath("")
                .fileName(fileName)
                .build();
        adRepository.save(adMedia);
        return adRepository.findById(adMedia.getId()).orElse(null);
    }

    @Override
    public List<AdMedia> getAllMedia(Long videoId) {
        return adRepository.findAllByVideoId(videoId);
    }
    @Override
    public byte[] downloadMedia(Long id) {
        AdMedia adMedia = adRepository.findById(id).get();
        return download(adMedia.getFileName());
    }

}