# Draw Steel - Character Creator

A modern web application for creating and managing characters, built with a Java Spring Boot backend and React TypeScript frontend.

## 🏗️ Project Structure

```
draw-steel/
├── backend/                    # Java Spring Boot backend
│   ├── src/main/java/
│   │   └── com/drawsteel/
│   │       ├── model/         # JPA entities
│   │       ├── repository/    # Data access layer
│   │       ├── service/       # Business logic
│   │       └── controller/    # REST API endpoints
│   ├── src/main/resources/
│   │   └── application.yml    # Configuration
│   └── build.gradle.kts       # Gradle build (Kotlin DSL)
├── frontend/                   # React TypeScript frontend
│   ├── src/
│   │   ├── components/        # React components
│   │   ├── App.tsx           # Main app component
│   │   └── index.tsx         # Entry point
│   ├── public/                # Static assets
│   ├── package.json           # Node dependencies
│   └── tailwind.config.js     # Tailwind CSS config
├── build.gradle.kts           # Root Gradle build
└── settings.gradle.kts        # Gradle project settings
```

## 🚀 Features

### Backend (Java + Spring Boot)
- **Character Management**: Full CRUD operations for characters
- **Data Models**: Simple character attributes and information
- **REST API**: RESTful endpoints for character operations
- **Database**: H2 in-memory database for development, PostgreSQL support for production
- **Security**: Spring Security with OAuth2 resource server
- **Validation**: Bean validation for data integrity

### Frontend (React + TypeScript)
- **Character List**: View all created characters with ability score summaries
- **Character Creator**: Two-step form for creating new characters
- **Character Viewer**: Detailed character sheet with all information
- **Modern UI**: Beautiful, responsive design with Tailwind CSS
- **Type Safety**: Full TypeScript support for better development experience

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (dev) / **PostgreSQL** (prod)
- **Gradle** (Kotlin DSL)

### Frontend
- **React 18**
- **TypeScript 4.9**
- **Tailwind CSS 3.3**
- **React Router 6**
- **Lucide React** (icons)
- **Axios** (HTTP client)

## 📋 Prerequisites

- **Java 17** or higher
- **Node.js 16** or higher
- **npm** or **yarn**

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd draw-steel
```

### 2. Backend Setup
```bash
# Navigate to backend directory
cd backend

# Run the Spring Boot application
./gradlew bootRun
```

The backend will start on `http://localhost:8080` with the API available at `/api`.

### 3. Frontend Setup
```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm start
```

The frontend will start on `http://localhost:3000`.

## 🎯 API Endpoints

### Characters
- `GET /api/characters` - List all characters
- `GET /api/characters/{id}` - Get character by ID
- `POST /api/characters` - Create new character
- `PUT /api/characters/{id}` - Update character
- `DELETE /api/characters/{id}` - Delete character

## 🎨 Character Features

### Core Attributes
- **Ability Scores**: Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma
- **Modifiers**: Automatic calculation of ability score modifiers
- **Proficiency Bonus**: Level-based proficiency bonus calculation

### Character Details
- **Basic Info**: Name, Description, Level
- **Background**: Character history and story
- **Personality**: Character traits and personality
- **Equipment**: Weapons, armor, and gear
- **Notes**: Additional character information

### Game Mechanics
- **Dice Rolling**: Built-in ability score rolling (4d6 drop lowest)
- **Validation**: Input validation for game rules compliance
- **Calculations**: Automatic modifier and bonus calculations

## 🔧 Development

### Backend Development
```bash
cd backend

# Run tests
./gradlew test

# Build JAR
./gradlew build

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### Frontend Development
```bash
cd frontend

# Run tests
npm test

# Build for production
npm run build

# Check for linting issues
npm run lint
```

## 🗄️ Database

### Development
- **H2 In-Memory Database**
- **Auto-created tables** on startup
- **H2 Console** available at `/h2-console`

### Production
- **PostgreSQL** database
- **Environment variables** for configuration
- **Flyway migrations** for schema management

## 🚀 Deployment

### Backend
```bash
cd backend
./gradlew build
java -jar build/libs/draw-steel-backend.jar
```

### Frontend
```bash
cd frontend
npm run build
# Serve the build folder with your web server
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🎮 Character System

This application provides a simple but flexible character creation system:
- Standard ability score ranges (3-20)
- Level progression (1-20)
- Ability score modifiers
- Proficiency bonus calculations
- Customizable character details

## 🔮 Future Enhancements

- [ ] Character leveling system
- [ ] Equipment and inventory management
- [ ] Character portraits and customization
- [ ] Export to PDF character sheets
- [ ] Multi-user support and character sharing
- [ ] Campaign management
- [ ] Dice rolling interface
- [ ] Character backup and sync

---

**Happy character building!** 🗡️⚔️🛡️
