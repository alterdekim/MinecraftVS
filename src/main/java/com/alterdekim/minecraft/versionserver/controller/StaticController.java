package com.alterdekim.minecraft.versionserver.controller;

import com.alterdekim.minecraft.versionserver.config.AppConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class StaticController {

    @Autowired
    private AppConfig config;

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
}