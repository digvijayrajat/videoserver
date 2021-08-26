package com.example.restservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AdMedia {
    @Id
    @GeneratedValue
    private Long id;
    private Long videoId;
    private Long timestamp;
    private String Title;
    private String link;
    private String filePath;
    private String fileName;

}