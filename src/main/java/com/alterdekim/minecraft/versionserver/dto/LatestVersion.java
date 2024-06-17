package com.alterdekim.minecraft.versionserver.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LatestVersion {
    private String release;
    private String snapshot;
}