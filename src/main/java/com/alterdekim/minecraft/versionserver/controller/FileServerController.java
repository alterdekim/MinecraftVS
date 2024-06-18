package com.alterdekim.minecraft.versionserver.controller;

import com.alterdekim.minecraft.versionserver.config.StorageProperties;
import com.alterdekim.minecraft.versionserver.exception.StorageFileNotFoundException;
import com.alterdekim.minecraft.versionserver.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileServerController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageProperties storageProperties;

    @GetMapping("/static/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(storageProperties.getLocation() + "/" + filename);
        MediaType mt = filename.endsWith(".json") ? MediaType.APPLICATION_JSON : MediaType.asMediaType(MimeType.valueOf("application/java-archive"));
        return ResponseEntity.ok().contentType(mt).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}

