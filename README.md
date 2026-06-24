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

**Response:**
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
```

### Update Playlist
```http
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

### Why MongoDB?
I chose MongoDB for this project because:

1. **Previous Experience:** I've used MongoDB in a previous project and found it intuitive for handling document-based data structures.

2. **Flexible Schema:** Playlists can have a variable number of songs. MongoDB's document model allows each playlist to store its songs as an embedded array, without needing a separate "songs" table.

3. **Embedded Documents:** The relationship between a playlist and its songs is a natural fit for embedding. This allows fetching an entire playlist (including all its songs) in a single database query, improving performance and reducing complexity.

4. **JSON-like Documents:** MongoDB's BSON format maps naturally to Java objects, simplifying the code and reducing ORM overhead.

### Playlist Collection
- Collection Name: `playlists`

**Document Structure:**
```json
{
    "_id": "ObjectId",
    "userId": "String",
    "name": "String",
    "description": "String",
    "createdAt": "ISODate",
    "updatedAt": "ISODate",
    "songs": [
        {
            "id": "UUID",
            "title": "String",
            "artist": "String",
            "album": "String",
            "duration": "Integer (seconds)"
        }
    ]
}
```

**Field Descriptions:**

| Field | Type | Description |
|-------|------|-------------|
| `_id` | ObjectId | Auto-generated unique identifier (MongoDB) |
| `userId` | String | ID of the user who owns the playlist |
| `name` | String | Playlist name (required) |
| `description` | String | Optional playlist description |
| `createdAt` | ISODate | Timestamp when the playlist was created |
| `updatedAt` | ISODate | Timestamp when the playlist was last updated |
| `songs` | Array | List of songs embedded in the playlist |

**Song Object Structure:**

| Field | Type | Description |
|-------|------|-------------|
| `id` | UUID | Unique identifier for the song |
| `title` | String | Song title (required) |
| `artist` | String | Artist name (required) |
| `album` | String | Album name (optional) |
| `duration` | Integer | Song duration in seconds |

**Why Songs Are Embedded:**
- A song only exists within the context of a playlist
- Allows retrieving the entire playlist with all songs in one query
- No need to query a separate "songs" table when fetching playlists
- Simplifies the data model and reduces application complexity

## How to Run

### Prerequisites
- Java 21
- MongoDB (local or Atlas)
- Gradle

### Platform Notes
- **Windows (CMD):** Use `gradlew.bat`
- **macOS / Linux / Git Bash:** Use `./gradlew`

### Steps

1. **Start MongoDB:**
   ```bash
   mongod
   ```
   Or using Docker:
   ```bash
   docker run -d -p 27017:27017 --name mongodb mongo:latest
   ```
   Or using MongoDB Compass:
   - Open MongoDB Compass
   - Connect to: `mongodb://localhost:27017`
   - Your database is now ready

2. **Build the project:**

   **Windows (CMD):**
   ```cmd
   gradlew.bat build
   ```

   **macOS / Linux / Git Bash:**
   ```bash
   ./gradlew build
   ```

3. **Run the application:**

   **Windows (CMD):**
   ```cmd
   gradlew.bat bootRun
   ```

   **macOS / Linux / Git Bash:**
   ```bash
   ./gradlew bootRun
   ```

4. **Access the API at:**
   ```
   http://localhost:8080/api/playlists
   ```

## Testing

### Platform Notes for curl Commands
- **Windows (CMD):** Use the commands as shown with `\"` escaping
- **PowerShell:** Replace `curl` with `curl.exe`
- **macOS / Linux / Git Bash:** Use single quotes `'` around JSON data

### Create a Playlist

**Windows (CMD):**
```cmd
curl -X POST http://localhost:8080/api/playlists -H "Content-Type: application/json" -d "{\"userId\":\"user123\",\"name\":\"My Favorites\",\"description\":\"Best songs\"}"
```

**macOS / Linux / Git Bash:**
```bash
curl -X POST http://localhost:8080/api/playlists \
  -H "Content-Type: application/json" \
  -d '{"userId":"user123","name":"My Favorites","description":"Best songs"}'
```

### Add a Song

**Windows (CMD):**
```cmd
curl -X POST http://localhost:8080/api/playlists/{playlistId}/songs -H "Content-Type: application/json" -d "{\"title\":\"Imagine\",\"artist\":\"John Lennon\",\"duration\":183}"
```

**macOS / Linux / Git Bash:**
```bash
curl -X POST http://localhost:8080/api/playlists/{playlistId}/songs \
  -H "Content-Type: application/json" \
  -d '{"title":"Imagine","artist":"John Lennon","duration":183}'
```

### Get User's Playlists

**All Platforms:**
```bash
curl http://localhost:8080/api/playlists/user/user123
```

### Update a Playlist

**Windows (CMD):**
```cmd
curl -X PUT http://localhost:8080/api/playlists/{playlistId} -H "Content-Type: application/json" -d "{\"userId\":\"user123\",\"name\":\"New Name\",\"description\":\"Updated description\"}"
```

**macOS / Linux / Git Bash:**
```bash
curl -X PUT http://localhost:8080/api/playlists/{playlistId} \
  -H "Content-Type: application/json" \
  -d '{"userId":"user123","name":"New Name","description":"Updated description"}'
```

### Delete a Playlist

**All Platforms:**
```bash
curl -X DELETE http://localhost:8080/api/playlists/{playlistId}
```

## Project Structure
```
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
Jana Abdelwahab Hassan

## License
This project was created as part of a technical assessment for Luftborn.
All code is for evaluation purposes only and is not intended for production use.