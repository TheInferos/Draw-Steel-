# Draw Steel Backend

A Spring Boot application for the Draw Steel tabletop RPG system.

## Features

- **UUID-based IDs**: All entities use UUIDs for their primary keys
- **JPA/Hibernate**: Full database persistence with PostgreSQL
- **RESTful APIs**: Complete CRUD operations for all game entities
- **Character Management**: Basic character CRUD operations with UUID-based IDs

## Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Gradle

## Quick Start

### 1. Start the Database

```bash
docker-compose up -d postgres
```

Wait for the database to be ready (check with `docker-compose ps`).

### 2. Run the Application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080/api`

### 3. Test the API

```bash
# Get all abilities
curl http://localhost:8080/api/abilities

# Create a character
curl -X POST http://localhost:8080/api/characters \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Character",
    "description": "A test character"
  }'
```

## API Endpoints

### Characters
- `GET /characters` - Get all characters
- `GET /characters/{id}` - Get character by UUID
- `POST /characters` - Create new character
- `PUT /characters/{id}` - Update character
- `DELETE /characters/{id}` - Delete character


### Abilities
- `GET /abilities` - Get all abilities
- `GET /abilities/{id}` - Get ability by UUID
- `POST /abilities` - Create new ability
- `PUT /abilities/{id}` - Update ability
- `DELETE /abilities/{id}` - Delete ability

### Other Entities
Similar endpoints exist for: Ancestry, Career, Culture, Kit, Language, Perk, Trait, Complication

## UUID Implementation

All entities in the system use UUIDs as their primary keys:

- **BaseModel**: Abstract base class with `@GeneratedValue(strategy = GenerationType.UUID)`
- **All Models**: Extend BaseModel and inherit UUID-based IDs
- **Controllers**: Accept UUID path parameters
- **Services**: Use UUIDs for all operations
- **Repositories**: Extend JpaRepository<Entity, UUID>

## Troubleshooting

### Database Connection Issues

1. Ensure PostgreSQL is running:
   ```bash
   docker-compose ps postgres
   ```

2. Check database logs:
   ```bash
   docker-compose logs postgres
   ```

3. Test connection manually:
   ```bash
   docker exec -it drawsteel-postgres psql -U drawsteel -d drawsteeldb
   ```

### UUID Generation Issues

The application is configured to use PostgreSQL's native UUID generation. If you encounter issues:

1. Check that PostgreSQL is running and accessible
2. Verify the database schema is properly created
3. Check application logs for detailed error messages

### Common Errors


- **Connection refused**: PostgreSQL container is not running
- **Authentication failed**: Check username/password in application.yml

## Development

### Database Schema

The application uses Hibernate's `ddl-auto: update` for development. This means:
- Tables are created/updated automatically on startup
- Data is preserved between application restarts
- For production, change to `ddl-auto: validate` or `ddl-auto: none`

### Adding New Entities

1. Create a model class extending `BaseModel`
2. Add `@Entity` annotation
3. Create a repository extending `JpaRepository<Entity, UUID>`
4. Create a service class
5. Create a controller with REST endpoints

## Production Considerations

- Change `ddl-auto` to `validate` or `none`
- Use proper database credentials
- Configure connection pooling
- Set appropriate logging levels
- Use environment variables for configuration
