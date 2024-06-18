package com.alterdekim.minecraft.versionserver.component;

import com.alterdekim.minecraft.versionserver.config.AppConfig;
import com.alterdekim.minecraft.versionserver.config.ServerConfig;
import com.alterdekim.minecraft.versionserver.dto.LatestVersion;
import com.alterdekim.minecraft.versionserver.dto.Version;
import com.alterdekim.minecraft.versionserver.dto.VersionsDTO;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
public class StartupListener {

    @Autowired
    private AppConfig config;

    @Autowired
    private ServerConfig serverConfig;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            List<String[]> l = readAllLines(Path.of(serverConfig.getCsvPath()));
            List<Version> vers = l.stream()
                    .map(d -> new Version(d[0], d[1], d[2], d[3], d[4]))
                    .toList();
            VersionsDTO dto = new VersionsDTO();
            dto.setVersions(vers);
            dto.setLatest(new LatestVersion());
            config.setVersionsDTO(dto);
            config.setHttpPath(serverConfig.getHttpPath());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
