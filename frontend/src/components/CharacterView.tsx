import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ArrowLeft, Edit, Download, Share2, Shield, Zap, Heart, Brain, Eye, Heart as HeartIcon } from 'lucide-react';

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
  background: string;
  personality: string;
  equipment: string;
  notes: string;
}

const CharacterView: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [character, setCharacter] = useState<Character | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // TODO: Replace with actual API call
    // For now, using mock data
    const mockCharacter: Character = {
      id: parseInt(id || '1'),
      name: "Thorin Ironfist",
      description: "A brave warrior",
      level: 5,
      strength: 16,
      dexterity: 12,
      constitution: 18,
      intelligence: 10,
      wisdom: 14,
      charisma: 8,
      background: "A proud warrior who has spent years defending his homeland from various threats. His family has a long tradition of military service.",
      personality: "Stubborn and proud, but fiercely loyal to friends and allies. Thorin values honor above all else and never backs down from a challenge.",
      equipment: "Battleaxe, Chain Mail, Shield, Backpack, Bedroll, Rations (5 days), Waterskin, 50 feet of rope, Tinderbox, Torches (10)",
      notes: "Thorin has a distinctive scar across his left cheek from a battle with an orc warband. He carries a small stone from his homeland as a good luck charm."
    };
    
    setTimeout(() => {
      setCharacter(mockCharacter);
      setLoading(false);
    }, 500);
  }, [id]);

  const getModifier = (score: number): string => {
    const modifier = Math.floor((score - 10) / 2);
    return modifier >= 0 ? `+${modifier}` : `${modifier}`;
  };

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
            Level {character.level}
          </p>
          {character.description && (
            <p className="text-lg text-secondary-700 mt-2">
              {character.description}
            </p>
          )}
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
                { key: 'strength', label: 'STR', icon: Shield, color: 'text-red-600' },
                { key: 'dexterity', label: 'DEX', icon: Zap, color: 'text-green-600' },
                { key: 'constitution', label: 'CON', icon: Heart, color: 'text-orange-600' },
                { key: 'intelligence', label: 'INT', icon: Brain, color: 'text-blue-600' },
                { key: 'wisdom', label: 'WIS', icon: Eye, color: 'text-purple-600' },
                { key: 'charisma', label: 'CHA', icon: HeartIcon, color: 'text-pink-600' }
              ].map(({ key, label, icon: Icon, color }) => (
                <div key={key} className="text-center p-3 bg-secondary-50 rounded-lg">
                  <Icon className={`h-6 w-6 ${color} mx-auto mb-2`} />
                  <div className="text-sm text-secondary-600">{label}</div>
                  <div className="text-2xl font-bold text-secondary-900">
                    {character[key as keyof Character] as number}
                  </div>
                  <div className="text-sm text-secondary-600">
                    {getModifier(character[key as keyof Character] as number)}
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Combat Stats */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Combat</h3>
            <div className="space-y-3">
              <div className="flex justify-between">
                <span className="text-secondary-600">Proficiency Bonus:</span>
                <span className="font-semibold">+{getProficiencyBonus(character.level)}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-secondary-600">Armor Class:</span>
                <span className="font-semibold">16</span>
              </div>
              <div className="flex justify-between">
                <span className="text-secondary-600">Initiative:</span>
                <span className="font-semibold">{getModifier(character.dexterity)}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-secondary-600">Speed:</span>
                <span className="font-semibold">25 ft</span>
              </div>
            </div>
          </div>
        </div>

        {/* Center Column - Background & Personality */}
        <div className="space-y-6">
          {/* Background */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Background</h3>
            <p className="text-secondary-700 leading-relaxed">
              {character.background || "No background information available."}
            </p>
          </div>

          {/* Personality */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Personality</h3>
            <p className="text-secondary-700">
              {character.personality || "No personality traits defined."}
            </p>
          </div>
        </div>

        {/* Right Column - Equipment & Notes */}
        <div className="space-y-6">
          {/* Equipment */}
          <div className="card">
            <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Equipment</h3>
            <div className="bg-secondary-50 p-4 rounded-lg">
              <p className="text-secondary-700 whitespace-pre-line">
                {character.equipment || "No equipment listed."}
              </p>
            </div>
          </div>

          {/* Notes */}
          {character.notes && (
            <div className="card">
              <h3 className="text-xl font-fantasy font-semibold text-secondary-900 mb-4">Notes</h3>
              <div className="bg-secondary-50 p-4 rounded-lg">
                <p className="text-secondary-700 whitespace-pre-line">
                  {character.notes}
                </p>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default CharacterView;
