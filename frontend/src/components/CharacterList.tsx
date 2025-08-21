import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Plus } from 'lucide-react';
import { apiService, Character } from '../services/api';
import CharacterCard from './CharacterCard';
import LoadingSpinner from './LoadingSpinner';
import EmptyState from './EmptyState';

const CharacterList: React.FC = () => {
  const [characters, setCharacters] = useState<Character[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCharacters = async () => {
      try {
        setLoading(true);
        const data = await apiService.getCharacters();
        setCharacters(data);
      } catch (error) {
        console.error('Error fetching characters:', error);
        // You might want to show an error message to the user here
      } finally {
        setLoading(false);
      }
    };

    fetchCharacters();
  }, []);

  const handleEdit = (character: Character) => {
    // TODO: Implement edit functionality
    console.log('Edit character:', character);
  };

  const handleDelete = (characterId: string) => {
    // TODO: Implement delete functionality
    console.log('Delete character:', characterId);
  };
  if (loading) {
    return <LoadingSpinner />;
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
        <EmptyState
          title="No characters yet"
          description="Create your first character to begin your adventure"
          actionText="Create Your First Character"
          actionLink="/create"
        />
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {characters.map((character) => (
            <CharacterCard
              key={character.id}
              character={character}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default CharacterList;
