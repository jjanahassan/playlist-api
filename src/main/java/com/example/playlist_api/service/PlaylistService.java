package com.example.playlist_api.service;

import com.example.playlist_api.*;
import com.example.playlist_api.dto.PlaylistRequestDTO;
import com.example.playlist_api.dto.PlaylistResponseDTO;
import com.example.playlist_api.dto.SongDTO;
import com.example.playlist_api.dto.SongRequestDTO;
import com.example.playlist_api.model.Playlist;
import com.example.playlist_api.model.Song;
import com.example.playlist_api.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistResponseDTO createPlaylist(PlaylistRequestDTO request){

        log.info("Creating playlist for user: {}", request.getUserId());
        Playlist playlist= Playlist.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .description(request.getDescription())
                .songs(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Playlist saved= playlistRepository.save(playlist);
        return mapToResponseDTO(saved);

    }

    public PlaylistResponseDTO addSong(String playlistId, SongRequestDTO songDTO){

        log.info("Adding song to playlist: {}", playlistId);

        Playlist playlist= playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found with id: "+ playlistId));

        Song song = Song.builder()
                .id(UUID.randomUUID().toString())
                .title(songDTO.getTitle())
                .artist(songDTO.getArtist())
                .album(songDTO.getAlbum())
                .duration(songDTO.getDuration())
                .build();

        playlist.getSongs().add(song);
        playlist.setUpdatedAt(LocalDateTime.now());

        Playlist updated= playlistRepository.save(playlist);
        return mapToResponseDTO(updated);
    }

    public List<PlaylistResponseDTO> getUserPlaylists(String userId){

        log.info("Fetching playlists for user: {}", userId);

        return playlistRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public PlaylistResponseDTO updatePlaylist(String playlistId, PlaylistRequestDTO request){

        Playlist playlist= playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setUpdatedAt(LocalDateTime.now());

        return mapToResponseDTO(playlistRepository.save(playlist));
    }

    public void deletePlaylist(String playlistId){

        if (!playlistRepository.existsById(playlistId))
            throw new RuntimeException("Playlist not found");

        playlistRepository.deleteById(playlistId);
    }

    private PlaylistResponseDTO mapToResponseDTO(Playlist playlist){

        return PlaylistResponseDTO.builder()
                .id(playlist.getId())
                .userId(playlist.getUserId())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .songCount(playlist.getSongs() !=null ? playlist.getSongs().size() : 0)
                .songs(playlist.getSongs() !=null ?
                        playlist.getSongs().stream()
                            .map(song -> SongDTO.builder()
                                         .id(song.getId())
                                         .title(song.getTitle())
                                         .artist(song.getArtist())
                                         .album(song.getAlbum())
                                         .duration(song.getDuration())
                                         .build())
                            .collect(Collectors.toList())  :
                        new ArrayList<>())
                .createdAt(playlist.getCreatedAt())
                .updatedAt(playlist.getUpdatedAt())
                .build();

    }
}