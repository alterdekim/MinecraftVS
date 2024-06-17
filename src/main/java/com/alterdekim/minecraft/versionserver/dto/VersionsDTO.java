package com.alterdekim.minecraft.versionserver.dto;

import lombok.*;

import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VersionsDTO {
    private LatestVersion latest;
    private List<Version> versions;
}