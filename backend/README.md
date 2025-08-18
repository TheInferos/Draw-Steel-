# Draw Steel Backend

A Spring Boot backend for the Draw Steel game system.

## 🚀 Quick Start

### Prerequisites
- Java 24+
- Docker and Docker Compose
- Gradle

### 1. Start PostgreSQL Database
```bash
docker-compose up -d
```

### 2. Verify Database is Running
```bash
docker-compose ps
```

### 3. Start the Application
```bash
./gradlew bootRun
```

## 🗄️ Database Configuration

- **Database**: PostgreSQL 15
- **Host**: localhost:5432
- **Database**: drawsteeldb
- **Username**: drawsteel
- **Password**: drawsteel123

## 📡 API Endpoints

- **Characters**: `/api/characters`
- **Ancestries**: `/api/ancestries`
- **Traits**: `/api/traits`
- **Test**: `/api/test`

## 🐳 Docker Commands

### Start Database
```bash
docker-compose up -d
```

### Stop Database
```bash
docker-compose down
```

### View Logs
```bash
docker-compose logs postgres
```

### Reset Database
```bash
docker-compose down -v
docker-compose up -d
```

## 🔧 Development

### Database Schema
The application will automatically create the database schema on startup with `spring.jpa.hibernate.ddl-auto=update`.

### UUID Generation
All entities use UUID primary keys with automatic generation.

### Docker Naming
- **Container**: `drawsteel-postgres`
- **Volume**: `drawsteel_postgres_data`
- **Network**: `drawsteel_default`

## 🧪 Testing

The application includes H2 database for testing purposes, configured separately from the main PostgreSQL database.
