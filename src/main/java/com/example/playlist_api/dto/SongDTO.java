package com.example.playlist_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SongDTO {

    private String id;
    private String title;
    private String artist;
    private String album;
    private Integer duration;
}
