package com.alterdekim.minecraft.versionserver.controller;

import com.alterdekim.minecraft.versionserver.config.AppConfig;
import com.alterdekim.minecraft.versionserver.config.StorageProperties;
import com.alterdekim.minecraft.versionserver.service.StorageService;
import com.alterdekim.minecraft.versionserver.util.HashUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;

@Slf4j
@Controller
public class StaticController {

    @Autowired
    private AppConfig config;

    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageProperties storageProperties;

    @GetMapping("/version_manifest.json")
    public ResponseEntity<String> getVersions() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(config.getVersionsDTO()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/config/{ver}.json")
    public ResponseEntity<String> getVersion(@PathVariable String ver) {
        String data = new String(storageService.loadAsByteArray(storageProperties.getLocation() + "/" + ver + ".json"));
        data = data.replace("${jar_hash}", HashUtil.getSHA(new File(storageProperties.getLocation() + "/" + ver + ".jar")));
        data = data.replace("${jar_size}", new File(storageProperties.getLocation() + "/" + ver + ".jar").length()+"");
        data = data.replace("${jar_url}", config.getHttpPath() + ver + ".jar");
        MediaType mt = MediaType.APPLICATION_JSON;
        return ResponseEntity.ok().contentType(mt).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + ver + ".json\"").body(data);
    }
}