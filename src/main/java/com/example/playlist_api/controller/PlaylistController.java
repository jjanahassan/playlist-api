package com.example.playlist_api.controller;

import com.example.playlist_api.*;
import com.example.playlist_api.dto.PlaylistRequestDTO;
import com.example.playlist_api.dto.PlaylistResponseDTO;
import com.example.playlist_api.dto.SongRequestDTO;
import com.example.playlist_api.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
@Slf4j

public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistResponseDTO> createPlaylist(@Valid @RequestBody PlaylistRequestDTO request){

        log.info("POST /api/playlists - Creating playlist");

        PlaylistResponseDTO response= playlistService.createPlaylist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<PlaylistResponseDTO> addSong(@PathVariable String playlistId, @Valid @RequestBody SongRequestDTO songDTO){

        log.info("POST /api/playlists/{}/songs - Adding song", playlistId);

        PlaylistResponseDTO response = playlistService.addSong(playlistId, songDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlaylistResponseDTO>> getUserPlaylists(@PathVariable String userId){

        log.info("GET /api/playlists/user/{} - Fetching playlists", userId);
        List<PlaylistResponseDTO> playlists = playlistService.getUserPlaylists(userId);
        return ResponseEntity.ok(playlists);
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<PlaylistResponseDTO> updatePlaylist(@PathVariable String playlistId, @Valid @RequestBody PlaylistRequestDTO request){

        log.info("PUT /api/playlists/{} - Updating playlist", playlistId);
        PlaylistResponseDTO response= playlistService.updatePlaylist(playlistId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String playlistId){

        log.info("DELETE /api/playlists/{} - Deleting playlist", playlistId);

        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}