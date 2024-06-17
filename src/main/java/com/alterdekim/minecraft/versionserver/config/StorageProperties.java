package com.alterdekim.minecraft.versionserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class StorageProperties {
    @Value("${minevs.storage_path}") private String location;
}
