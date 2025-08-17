import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Plus, Eye, Edit, Trash2, Sword } from 'lucide-react';

interface Character {
  id: number;
  name: string;
  description: string;
  level: number;
  strength: number;
  dexterity: number;
  constitution: number;
  intelligence: number;
  wisdom: number;
  charisma: number;
}

const CharacterList: React.FC = () => {
  const [characters, setCharacters] = useState<Character[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // TODO: Replace with actual API call
    // For now, using mock data
    const mockCharacters: Character[] = [
      {
        id: 1,
        name: "Thorin Ironfist",
        description: "A brave warrior",
        level: 5,
        strength: 16,
        dexterity: 12,
        constitution: 18,
        intelligence: 10,
        wisdom: 14,
        charisma: 8
      },
      {
        id: 2,
        name: "Elara Moonwhisper",
        description: "A skilled archer",
        level: 4,
        strength: 12,
        dexterity: 18,
        constitution: 14,
        intelligence: 14,
        wisdom: 16,
        charisma: 10
      }
    ];
    
    setTimeout(() => {
      setCharacters(mockCharacters);
      setLoading(false);
    }, 500);
  }, []);

  const getModifier = (score: number): string => {
    const modifier = Math.floor((score - 10) / 2);
    return modifier >= 0 ? `+${modifier}` : `${modifier}`;
  };

  const getHighestAbility = (character: Character): { name: string; score: number } => {
    const abilities = [
      { name: 'STR', score: character.strength },
      { name: 'DEX', score: character.dexterity },
      { name: 'CON', score: character.constitution },
      { name: 'INT', score: character.intelligence },
      { name: 'WIS', score: character.wisdom },
      { name: 'CHA', score: character.charisma }
    ];
    
    return abilities.reduce((highest, current) => 
      current.score > highest.score ? current : highest
    );
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h2 className="text-3xl font-fantasy font-bold text-secondary-900">
          Your Characters
        </h2>
        <Link
          to="/create"
          className="btn-primary flex items-center space-x-2"
        >
          <Plus className="h-5 w-5" />
          <span>Create Character</span>
        </Link>
      </div>

      {characters.length === 0 ? (
        <div className="text-center py-12">
          <Sword className="h-16 w-16 text-secondary-400 mx-auto mb-4" />
          <h3 className="text-xl font-medium text-secondary-600 mb-2">
            No characters yet
          </h3>
          <p className="text-secondary-500 mb-6">
            Create your first character to begin your adventure
          </p>
          <Link to="/create" className="btn-primary">
            Create Your First Character
          </Link>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {characters.map((character) => {
            const highestAbility = getHighestAbility(character);
            
            return (
              <div key={character.id} className="card hover:shadow-lg transition-shadow duration-200">
                <div className="flex items-start justify-between mb-4">
                  <div>
                    <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-1">
                      {character.name}
                    </h3>
                    <p className="text-secondary-600">
                      Level {character.level}
                    </p>
                    {character.description && (
                      <p className="text-sm text-secondary-500 mt-1">
                        {character.description}
                      </p>
                    )}
                  </div>
                  <div className="text-right">
                    <div className="text-sm text-secondary-500">Highest</div>
                    <div className="text-lg font-bold text-primary-600">
                      {highestAbility.name} {highestAbility.score}
                    </div>
                  </div>
                </div>

                <div className="grid grid-cols-3 gap-2 mb-4">
                  <div className="ability-score">
                    <div className="text-xs text-secondary-500">STR</div>
                    <div className="text-lg font-bold">{character.strength}</div>
                    <div className="text-sm text-secondary-600">{getModifier(character.strength)}</div>
                  </div>
                  <div className="ability-score">
                    <div className="text-xs text-secondary-500">DEX</div>
                    <div className="text-lg font-bold">{character.dexterity}</div>
                    <div className="text-sm text-secondary-600">{getModifier(character.dexterity)}</div>
                  </div>
                  <div className="ability-score">
                    <div className="text-xs text-secondary-500">CON</div>
                    <div className="text-lg font-bold">{character.constitution}</div>
                    <div className="text-sm text-secondary-600">{getModifier(character.constitution)}</div>
                  </div>
                </div>

                <div className="flex space-x-2">
                  <Link
                    to={`/character/${character.id}`}
                    className="flex-1 btn-secondary flex items-center justify-center space-x-2"
                  >
                    <Eye className="h-4 w-4" />
                    <span>View</span>
                  </Link>
                  <button className="flex-1 btn-secondary flex items-center justify-center space-x-2">
                    <Edit className="h-4 w-4" />
                    <span>Edit</span>
                  </button>
                  <button className="flex-1 btn-secondary flex items-center justify-center space-x-2 text-red-600 hover:bg-red-100">
                    <Trash2 className="h-4 w-4" />
                    <span>Delete</span>
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default CharacterList;
