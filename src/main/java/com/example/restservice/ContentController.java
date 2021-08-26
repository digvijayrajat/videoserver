package com.example.restservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/content")
@AllArgsConstructor
@CrossOrigin("*")
public class ContentController {
    ContentService service;

   @GetMapping
    public ResponseEntity<List<Content>> getTodos() {
        return new ResponseEntity<>(service.getAllTodos(), HttpStatus.OK);
    }

    @PostMapping(
            path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Content> saveTodo(@RequestParam("title") String title,
                                            @RequestParam("description") String description,
                                            @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.saveTodo(title, description, file), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/download")
    public byte[] downloadTodoImage(@PathVariable("id") Long id) {
        return service.downloadTodoImage(id);
    }


    @PostMapping(value = "{id}/media",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdMedia> addMedia(
                                        @PathVariable("id") Long id,
                                        @RequestParam("title") String title,
                                        @RequestParam("timestamp") Long timestamp,
                                        @RequestParam("link") String link,
                                        @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.addMedia(id,timestamp,title ,link, file), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/media")
    public ResponseEntity<List<AdMedia>> getAllMedia(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getAllMedia(id), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/media/download")
    public byte[] downloadMedia(@PathVariable("id") Long id) {
        return service.downloadMedia(id);
    }

}