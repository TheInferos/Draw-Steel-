# Draw Steel RPG Data Files

This directory contains JSON data files for seeding the Draw Steel RPG database.

## File Structure

### `ancestries.json`
Contains all available ancestries (races) for character creation.
- **Format**: Array of objects with `name` property
- **Example**: `{ "name": "Human" }`

### `cultures.json`
Contains all available cultures with detailed descriptions and skill information.
- **Format**: Array of objects with:
  - `name`: Culture name
  - `description`: Full description from the RPG source material
  - `skillGroups`: Array of skill group options (e.g., `["EXPLORATION", "INTERPERSONAL"]`)
  - `quickBuild`: Recommended skill for quick character builds

### `careers.json`
Contains all available careers with detailed information including inciting incidents.
- **Format**: Array of objects with:
  - `name`: Career name
  - `description`: Career description and questions for character development
  - `skills`: Array of specific skills (optional)
  - `skillGroups`: Array of skill group options
  - `quickBuild`: Array of recommended skills for quick builds
  - `incitingIncident`: Array of story hooks for character background

## Benefits of JSON Structure

1. **Maintainability**: Easy to edit and update without touching component code
2. **Reusability**: Data can be imported by other components or systems
3. **Version Control**: Clear tracking of data changes
4. **Separation of Concerns**: Data logic separated from UI logic
5. **Backend Integration**: JSON format easily maps to database schemas

## Adding New Data

To add new entries:
1. Edit the appropriate JSON file
2. Follow the existing format and structure
3. Use UPPERCASE for enum values (e.g., skill groups, skill names)
4. Test the seeder to ensure data loads correctly

## Usage in Components

```typescript
import culturesData from '../data/cultures.json';
import careersData from '../data/careers.json';
import ancestriesData from '../data/ancestries.json';

// Use in your component
const seedData = {
  ancestries: ancestriesData,
  cultures: culturesData,
  careers: careersData,
  // ... other data
};
```

## 📋 **Base Data Structures**

### **Ancestries Example:**
```json
{
  "name": "Human"
},
```

### **Careers Example:**
```json
{ 
    "name": "", 
    "description":"",
    "skills": [],
    "skillGroups": ["",],
    "quickBuild": [""],
    "renown": 0,
    "wealth": 0,
    "incitingIncidents":[
      ""
    ]
  },
```
### **Cultures Base:**
```json
{
  "name": "",
  "description": "",
  "skillGroups": [""],
  "quickBuild": ""
},
```
### **Traits Base:**
```json
{
    "name": "",
    "description": "",
    "cost": 0,
    "signatureToggle": false,
    "traitType":""
},
```


## 🔧 **Data Validation Rules**

- **Names**: Use proper capitalization (e.g., "Nomadic", "Agent")
- **Skill Groups**: Use UPPERCASE enum values (e.g., "EXPLORATION", "INTERPERSONAL")
- **Skills**: Use UPPERCASE enum values (e.g., "SNEAK", "LIE")
- **Descriptions**: Include full text from RPG source material
- **Arrays**: Use proper JSON array syntax with square brackets
