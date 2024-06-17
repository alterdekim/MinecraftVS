package com.alterdekim.minecraft.versionserver.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Version {
    private String id;
    private String type;
    private String url;
    private String time;
    private String releaseTime;
}
