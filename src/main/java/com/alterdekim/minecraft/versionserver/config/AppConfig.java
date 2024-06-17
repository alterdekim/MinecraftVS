package com.alterdekim.minecraft.versionserver.config;

import com.alterdekim.minecraft.versionserver.dto.VersionsDTO;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {
    private VersionsDTO versionsDTO;
}
