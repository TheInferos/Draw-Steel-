const API_BASE_URL = 'http://localhost:8080/api';

export interface Ancestry {
  id: string;
  name: string;
  description?: string;
}

export interface Culture {
  id: string;
  name: string;
  description?: string;
}

export interface Career {
  id: string;
  name: string;
  description?: string;
}

export interface Kit {
  id: string;
  name: string;
  description?: string;
}

export interface CharacterClass {
  id: string;
  name: string;
  description?: string;
}

export interface Ability {
  id: string;
  name: string;
  description?: string;
}

export interface Complication {
  id: string;
  name: string;
  description?: string;
}

export interface Character {
  id: string;
  name: string;
  description?: string;
  level: number;
  ancestry: Ancestry;
  culture: Culture;
  career: Career;
  kit: Kit;
  characterClass: CharacterClass;
  might: number;
  agility: number;
  reason: number;
  intuition: number;
  presence: number;
  speed: number;
  stability: number;
  abilities: Ability[];
  complications: Complication[];
}

export interface CreateCharacterRequest {
  name: string;
  description?: string;
  level: number;
  ancestryId: string;
  cultureId: string;
  careerId: string;
  kitId: string;
  characterClassId: string;
  might: number;
  agility: number;
  reason: number;
  intuition: number;
  presence: number;
  speed: number;
  stability: number;
  abilityIds: string[];
  complicationIds: string[];
}

export interface UpdateCharacterRequest extends Partial<CreateCharacterRequest> {
  id: string;
}

class ApiService {
  private async request<T>(endpoint: string, options?: RequestInit): Promise<T> {
    const url = `${API_BASE_URL}${endpoint}`;
    const response = await fetch(url, {
      headers: {
        'Content-Type': 'application/json',
        ...options?.headers,
      },
      ...options,
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    return response.json();
  }

  // Character API calls
  async getCharacters(): Promise<Character[]> {
    return this.request<Character[]>('/characters');
  }

  async getCharacter(id: string): Promise<Character> {
    return this.request<Character>(`/characters/${id}`);
  }

  async createCharacter(character: CreateCharacterRequest): Promise<Character> {
    return this.request<Character>('/characters', {
      method: 'POST',
      body: JSON.stringify(character),
    });
  }

  async updateCharacter(character: UpdateCharacterRequest): Promise<Character> {
    return this.request<Character>(`/characters/${character.id}`, {
      method: 'PUT',
      body: JSON.stringify(character),
    });
  }

  async deleteCharacter(id: string): Promise<void> {
    return this.request<void>(`/characters/${id}`, {
      method: 'DELETE',
    });
  }
}

export const apiService = new ApiService();
