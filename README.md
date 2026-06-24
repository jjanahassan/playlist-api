# Playlist API

A RESTful API for managing playlists and songs built with Spring Boot and MongoDB.

## Technologies
- Java 21
- Spring Boot 4.1.0
- MongoDB
- Gradle
- Lombok

## Features
- Create playlists
- Add songs to playlists
- Get user's playlists
- Update playlists (bonus)
- Delete playlists (bonus)
- Global exception handling
- MongoDB persistence

## API Endpoints

### Create Playlist
```http
POST /api/playlists
Content-Type: application/json

{
    "userId": "user123",
    "name": "My Favorites",
    "description": "Best songs ever"
}
```

### Response:
```json
{
    "id": "65f8a1b2c3d4e5f6g7h8i9j0",
    "userId": "user123",
    "name": "My Favorites",
    "description": "Best songs ever",
    "songCount": 0,
    "songs": [],
    "createdAt": "2026-06-23T13:24:21",
    "updatedAt": "2026-06-23T13:24:21"
}
```
### Add Song to Playlist
```http
POST /api/playlists/{playlistId}/songs
Content-Type: application/json

{
    "title": "Bohemian Rhapsody",
    "artist": "Queen",
    "album": "A Night at the Opera",
    "duration": 354
}
```

### Get User's Playlists
```http
GET /api/playlists/user/{userId}
Update Playlist
http
PUT /api/playlists/{playlistId}
Content-Type: application/json

{
    "userId": "user123",
    "name": "Updated Name",
    "description": "New description"
}
```

### Delete Playlist
```http
DELETE /api/playlists/{playlistId}
```


## Database Schema

### Playlist Collection
```json
{
    "_id": "65f8a1b2c3d4e5f6g7h8i9j0",
    "userId": "user123",
    "name": "My Favorites",
    "description": "Best songs ever",
    "createdAt": "2026-06-23T13:24:21",
    "updatedAt": "2026-06-23T13:24:21",
    "songs": [
        {
            "id": "550e8400-e29b-41d4-a716-446655440000",
            "title": "Bohemian Rhapsody",
            "artist": "Queen",
            "album": "A Night at the Opera",
            "duration": 354
        }
    ]
}
```


## How to Run

### Prerequisites
- Java 21
- MongoDB (local or Atlas)
- Gradle

### Steps
1. Start MongoDB:
    ```bash
    mongod
    ```
    Or using Docker:
    ```bash
    docker run -d -p 27017:27017 --name mongodb mongo:latest
    ```

2. Build the project:
    
    ```bash
    ./gradlew build
    ```

3. Run the application:
    
    ```bash
    ./gradlew bootRun
    ```

4. Access the API at:
    ```text
    http://localhost:8080/api/playlists
    ```

## Testing

### Create a Playlist
```bash
curl -X POST http://localhost:8080/api/playlists \
  -H "Content-Type: application/json" \
  -d '{"userId":"user123","name":"My Favorites","description":"Best songs"}'
```

### Add a Song
```bash
curl -X POST http://localhost:8080/api/playlists/{playlistId}/songs \
  -H "Content-Type: application/json" \
  -d '{"title":"Imagine","artist":"John Lennon","duration":183}'
```

### Get User's Playlists
```bash
curl http://localhost:8080/api/playlists/user/user123
```

### Update a Playlist
```bash
curl -X PUT http://localhost:8080/api/playlists/{playlistId} \
  -H "Content-Type: application/json" \
  -d '{"userId":"user123","name":"New Name","description":"Updated description"}'
```

### Delete a Playlist
```bash
curl -X DELETE http://localhost:8080/api/playlists/{playlistId}
```

### Project Structure
```text
playlist-api/
├── src/main/java/com/example/playlist_api/
│   ├── model/           # Entities (Playlist, Song)
│   ├── repository/      # MongoDB repositories
│   ├── dto/             # Data Transfer Objects
│   ├── service/         # Business logic
│   ├── controller/      # REST endpoints
│   └── exception/       # Global exception handling
├── src/main/resources/
│   └── application.properties
└── build.gradle
```

## Author
Jana Abdelwahab

## License
This project is for educational purposes.