package com.alterdekim.minecraft.versionserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ServerConfig {
    @Value("${minevs.csv_path}") private String csvPath;
    @Value("${minevs.http_path}") private String httpPath;
}