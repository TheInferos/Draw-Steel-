import React from 'react';
import { Link } from 'react-router-dom';
import { Eye, Edit, Trash2 } from 'lucide-react';
import { Character } from '../services/api';

interface CharacterCardProps {
  character: Character;
  onEdit: (character: Character) => void;
  onDelete: (characterId: number) => void;
}

const CharacterCard: React.FC<CharacterCardProps> = ({ 
  character, 
  onEdit, 
  onDelete 
}) => {
  const attributes = [
    { name: 'Might', score: character.might },
    { name: 'Agility', score: character.agility },
    { name: 'Reason', score: character.reason },
    { name: 'Intuition', score: character.intuition },
    { name: 'Presence', score: character.presence }
  ];

  const highestAttribute = attributes.reduce((highest, current) => 
    current.score > highest.score ? current : highest
  );

  return (
    <div className="card hover:shadow-lg transition-shadow duration-200">
      <div className="flex items-start justify-between mb-4">
        <div>
          <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-1">
            {character.name}
          </h3>
          <p className="text-secondary-600">
            Level {character.level} {character.characterClass?.name}
          </p>
          <p className="text-sm text-secondary-500">
            {character.ancestry?.name} • {character.culture?.name}
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
            {highestAttribute.name} {highestAttribute.score}
          </div>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-2 mb-4">
        {attributes.map(({ name, score }) => (
          <div key={name} className="ability-score">
            <div className="text-xs text-secondary-500">{name}</div>
            <div className="text-lg font-bold">{score}</div>
          </div>
        ))}
      </div>

      <div className="flex space-x-2">
        <Link
          to={`/character/${character.id}`}
          className="flex-1 btn-secondary flex items-center justify-center space-x-2"
        >
          <Eye className="h-4 w-4" />
          <span>View</span>
        </Link>
        <button 
          onClick={() => onEdit(character)}
          className="flex-1 btn-secondary flex items-center justify-center space-x-2"
        >
          <Edit className="h-4 w-4" />
          <span>Edit</span>
        </button>
        <button 
          onClick={() => onDelete(character.id)}
          className="flex-1 btn-secondary flex items-center justify-center space-x-2 text-red-600 hover:bg-red-100"
        >
          <Trash2 className="h-4 w-4" />
          <span>Delete</span>
        </button>
      </div>
    </div>
  );
};

export default CharacterCard;
