package com.alterdekim.minecraft.versionserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "../spring-conf/minevs/storage";
}
