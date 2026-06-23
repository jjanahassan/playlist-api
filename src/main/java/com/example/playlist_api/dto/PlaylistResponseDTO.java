package com.example.playlist_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PlaylistResponseDTO {

    private String id;
    private String userId;
    private String name;
    private String description;
    private Integer songCount;
    private List<SongDTO> songs;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
