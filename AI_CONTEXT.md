# AI Chat Context for Playlist API Project

## AI Model Used
- **Model:** DeepSeek (Web Interface)
- **Date Range:** June 22-24, 2026

## How AI Was Used
AI was used as a development assistant to:
- Clarify project requirements and structure
- Suggest design patterns and best practices
- Troubleshoot build and configuration issues
- Review code structure and suggest improvements
- Help with documentation and README formatting
- Provide cross-platform testing commands

---

## Summary of Key Interactions

### 1. Project Setup & Configuration
- Choosing the right technologies (Spring Boot 4.1.0, Java 21, MongoDB)
- Setting up Gradle and dependencies
- Configuring `application.properties` for MongoDB
- Resolving Java version compatibility issues
- Fixing IntelliJ configuration for Gradle and JDK

### 2. Database Design
- Deciding between MongoDB and relational databases
- Modeling Playlist-Song relationship using embedded documents
- Justifying MongoDB choice based on previous experience
- Structuring the database schema with appropriate fields and types

### 3. Code Architecture
- Following Controller-Service-Repository pattern
- Using DTOs for request/response handling
- Implementing DTOs for cleaner API design
- Organizing packages (model, repository, service, controller, dto, exception)
- Using Lombok to reduce boilerplate code

### 4. Troubleshooting
- Resolving "ClassNotFoundException" when running the application
- Fixing JAVA_HOME and Gradle configuration issues
- Resolving file lock issues during Gradle builds
- Understanding and fixing 405 Method Not Allowed errors
- Fixing curl command syntax for Windows CMD and PowerShell

### 5. REST API Design
- Mapping endpoints for CRUD operations:
    - `POST /api/playlists` – Create playlist
    - `POST /api/playlists/{id}/songs` – Add song
    - `GET /api/playlists/user/{userId}` – Get user's playlists
    - `PUT /api/playlists/{id}` – Update playlist (bonus)
    - `DELETE /api/playlists/{id}` – Delete playlist (bonus)
- Using `@Valid` for request validation

### 6. Exception Handling
- Implementing `@ControllerAdvice` for global exception handling
- Handling `RuntimeException` with custom error responses
- Validating input with meaningful error messages

### 7. Documentation
- Writing a comprehensive README with:
    - API endpoint documentation with examples
    - Database schema and design justifications
    - Cross-platform setup instructions (Windows, macOS, Linux)
    - Testing commands for both Windows CMD and Bash
- Adding platform-specific notes for compatibility

### 8. Git and Version Control
- Structuring commits with conventional commit messages
- Best practices for commit frequency and granularity
- Connecting local repository to GitHub remote

## How AI Was Applied
The AI was used to:
- Answer specific technical questions
- Provide code snippets for implementation guidance
- Suggest improvements and alternative approaches
- Explain concepts (e.g., DTOs, `@Builder.Default`, `orElseThrow`)
- Help with troubleshooting and debugging
- Review and suggest improvements for documentation

## Transparency Note
All code in the repository was implemented and tested by the developer. AI was used as a learning and reference tool to:
- Understand best practices
- Clarify concepts
- Troubleshoot issues
- Suggest structure and patterns

The final implementation reflects the developer's understanding and application of the concepts discussed.