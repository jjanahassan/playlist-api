package com.example.playlist_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PlaylistRequestDTO {

    @NotBlank(message= "UserId is required")
    private String userId;
    @NotBlank(message= "Playlist name is required")
    @Size(max =100, message= "Name must be less than 100 characters")
    private String name;
    private String description;
}
