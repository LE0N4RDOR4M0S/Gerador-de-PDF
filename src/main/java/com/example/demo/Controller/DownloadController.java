package com.example.demo.Controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadController {

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile() throws IOException {
        Path path = Paths.get("Nome do documento.pdf");
        byte[] data = Files.readAllBytes(path);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "Nome do documento.pdf");
        
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }
}
