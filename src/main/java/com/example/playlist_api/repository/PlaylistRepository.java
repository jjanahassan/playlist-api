package com.example.playlist_api.repository;

import com.example.playlist_api.model.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {

    List<Playlist> findByUserId(String userId);

}
