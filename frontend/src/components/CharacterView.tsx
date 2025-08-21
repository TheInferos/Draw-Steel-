import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ArrowLeft, Edit, Download, Share2, Shield, Zap, Heart, Brain, Eye, Heart as HeartIcon } from 'lucide-react';
import { apiService, Character } from '../services/api';

const CharacterView: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [character, setCharacter] = useState<Character | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchCharacter = async () => {
      if (!id) return;
      
      try {
        setLoading(true);
        const characterData = await apiService.getCharacter(id);
        setCharacter(characterData);
        setError(null);
      } catch (err) {
        console.error('Failed to fetch character:', err);
        setError('Failed to load character');
      } finally {
        setLoading(false);
      }
    };

    fetchCharacter();
  }, [id]);

  const getProficiencyBonus = (level: number): number => {
    return Math.floor((level - 1) / 4) + 2;
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center py-12">
        <h3 className="text-xl font-medium text-red-600 mb-2">
          {error}
        </h3>
        <button
          onClick={() => navigate('/')}
          className="btn-primary"
        >
          Back to Characters
        </button>
      </div>
    );
  }

  if (!character) {
    return (
      <div className="text-center py-12">
        <h3 className="text-xl font-medium text-secondary-600 mb-2">
          Character not found
        </h3>
        <button
          onClick={() => navigate('/')}
          className="btn-primary"
        >
          Back to Characters
        </button>
      </div>
    );
  }

  return (
    <div className="max-w-6xl mx-auto">
      {/* Header */}
      <div className="flex items-center justify-between mb-8">
        <button
          onClick={() => navigate('/')}
          className="btn-secondary flex items-center space-x-2"
        >
          <ArrowLeft className="h-5 w-5" />
          <span>Back to Characters</span>
        </button>
        
        <div className="flex space-x-3">
          <button className="btn-secondary flex items-center space-x-2">
            <Edit className="h-4 w-4" />
            <span>Edit</span>
          </button>
          <button className="btn-secondary flex items-center space-x-2">
            <Download className="h-4 w-4" />
            <span>Export</span>
          </button>
          <button className="btn-secondary flex items-center space-x-2">
            <Share2 className="h-4 w-4" />
            <span>Share</span>
          </button>
        </div>
      </div>

      {/* Character Header */}
      <div className="card mb-6">
        <div className="text-center">
          <h1 className="text-4xl font-fantasy font-bold text-primary-800 mb-2">
            {character.name}
          </h1>
          <p className="text-xl text-secondary-600">
            Level {character.level} {character.characterClass?.name}
          </p>
          {character.description && (
            <p className="text-lg text-secondary-700 mt-2">
              {character.description}
            </p>
          )}
          <div className="flex justify-center space-x-4 mt-3 text-sm text-secondary-600">
            {character.ancestry && (
              <span>{character.ancestry.name}</span>
            )}
            {character.culture && (
              <span>{character.culture.name}</span>
            )}
            {character.career && (
              <span>{character.career.name}</span>
            )}
          </div>
        </div>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Left Column - Basic Info & Abilities */}
        <div className="space-y-6">
          {/* Ability Scores */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Ability Scores</h3>
            <div className="grid grid-cols-2 gap-4">
              {[
                { key: 'might', label: 'Might', icon: Shield, color: 'text-red-600' },
                { key: 'agility', label: 'Agility', icon: Zap, color: 'text-green-600' },
                { key: 'reason', label: 'Reason', icon: Brain, color: 'text-blue-600' },
                { key: 'intuition', label: 'Intuition', icon: Eye, color: 'text-purple-600' },
                { key: 'presence', label: 'Presence', icon: HeartIcon, color: 'text-pink-600' },
                { key: 'speed', label: 'Speed', icon: Zap, color: 'text-yellow-600' }
              ].map(({ key, label, icon: Icon, color }) => (
                <div key={key} className="text-center p-3 bg-secondary-50 rounded-lg">
                  <Icon className={`h-6 w-6 ${color} mx-auto mb-2`} />
                  <div className="text-sm text-secondary-600">{label}</div>
                  <div className="text-2xl font-bold text-secondary-900">
                    {character[key as keyof Character] as number}
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Stamina */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Stamina</h3>
            <div className="text-center">
              <Heart className="h-12 w-12 text-red-600 mx-auto mb-2" />
              <div className="text-3xl font-bold text-secondary-900">
                {character.stability}
              </div>
              <div className="text-sm text-secondary-600">Stability Score</div>
            </div>
          </div>
          
        </div>

        {/* Center Column - Background & Abilities */}
        <div className="space-y-6">
          {/* Abilities */}
          {character.abilities && character.abilities.length > 0 && (
            <div className="card">
              <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Abilities</h3>
              <div className="space-y-3">
                {character.abilities.map((ability) => (
                  <div key={ability.id} className="bg-secondary-50 p-3 rounded-lg">
                    <h4 className="font-semibold text-secondary-900">{ability.name}</h4>
                    {ability.description && (
                      <p className="text-sm text-secondary-700 mt-1">{ability.description}</p>
                    )}
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Complications */}
          {character.complications && character.complications.length > 0 && (
            <div className="card">
              <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Complications</h3>
              <div className="space-y-3">
                {character.complications.map((complication) => (
                  <div key={complication.id} className="bg-secondary-50 p-3 rounded-lg">
                    <h4 className="font-semibold text-secondary-900">{complication.name}</h4>
                    {complication.description && (
                      <p className="text-sm text-secondary-700 mt-1">{complication.description}</p>
                    )}
                  </div>
                ))}
              </div>
            </div>
          )}
        </div>

        {/* Right Column - Kit & Notes */}
        <div className="space-y-6">
          {/* Kit */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Kit</h3>
            {character.kit ? (
              <div className="bg-secondary-50 p-4 rounded-lg">
                <h4 className="font-semibold text-secondary-900 mb-2">{character.kit.name}</h4>
                {character.kit.description && (
                  <p className="text-secondary-700">{character.kit.description}</p>
                )}
              </div>
            ) : (
              <div className="bg-secondary-50 p-4 rounded-lg">
                <p className="text-secondary-700">No kit assigned.</p>
              </div>
            )}
          </div>

          {/* Character Details */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Character Details</h3>
            <div className="space-y-3">
              {character.ancestry && (
                <div className="flex justify-between">
                  <span className="text-secondary-600">Ancestry:</span>
                  <span className="font-semibold">{character.ancestry.name}</span>
                </div>
              )}
              {character.culture && (
                <div className="flex justify-between">
                  <span className="text-secondary-600">Culture:</span>
                  <span className="font-semibold">{character.culture.name}</span>
                </div>
              )}
              {character.career && (
                <div className="flex justify-between">
                  <span className="text-secondary-600">Career:</span>
                  <span className="font-semibold">{character.career.name}</span>
                </div>
              )}
              {character.characterClass && (
                <div className="flex justify-between">
                  <span className="text-secondary-600">Class:</span>
                  <span className="font-semibold">{character.characterClass.name}</span>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CharacterView;
