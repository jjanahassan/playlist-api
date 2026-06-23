package com.example.playlist_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SongRequestDTO {

    @NotBlank(message= "Song title is required")
    private String title;
    @NotBlank(message= "Artist is required")
    private String artist;
    private String album;
    @Min(value=1, message= "Duration must be at least 1 second")
    private Integer duration;
}
