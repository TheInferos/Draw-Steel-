import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Save, ArrowLeft, Dice6, Shield, Zap, Brain, Heart, Eye } from 'lucide-react';

interface CharacterForm {
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

const CharacterCreator: React.FC = () => {
  const navigate = useNavigate();
  const [currentStep, setCurrentStep] = useState(1);
  const [formData, setFormData] = useState<CharacterForm>({
    name: '',
    description: '',
    level: 1,
    strength: 10,
    dexterity: 10,
    constitution: 10,
    intelligence: 10,
    wisdom: 10,
    charisma: 10,
    background: '',
    personality: '',
    equipment: '',
    notes: ''
  });

  const handleInputChange = (field: keyof CharacterForm, value: string | number) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }));
  };

  const rollAbilityScore = () => {
    // Simulate 4d6 drop lowest
    const rolls = Array.from({ length: 4 }, () => Math.floor(Math.random() * 6) + 1);
    rolls.sort((a, b) => b - a);
    return rolls.slice(0, 3).reduce((sum, roll) => sum + roll, 0);
  };

  const rollAllAbilities = () => {
    setFormData(prev => ({
      ...prev,
      strength: rollAbilityScore(),
      dexterity: rollAbilityScore(),
      constitution: rollAbilityScore(),
      intelligence: rollAbilityScore(),
      wisdom: rollAbilityScore(),
      charisma: rollAbilityScore()
    }));
  };

  const getModifier = (score: number): string => {
    const modifier = Math.floor((score - 10) / 2);
    return modifier >= 0 ? `+${modifier}` : `${modifier}`;
  };

  const nextStep = () => {
    if (currentStep < 2) setCurrentStep(currentStep + 1);
  };

  const prevStep = () => {
    if (currentStep > 1) setCurrentStep(currentStep - 1);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: Send to backend API
    console.log('Creating character:', formData);
    navigate('/');
  };

  const renderStep1 = () => (
    <div className="space-y-6">
      <h3 className="text-xl font-fantasy font-semibold text-secondary-900">Basic Information</h3>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Character Name</label>
          <input
            type="text"
            value={formData.name}
            onChange={(e) => handleInputChange('name', e.target.value)}
            className="input-field"
            placeholder="Enter character name"
            required
          />
        </div>
        
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Level</label>
          <input
            type="number"
            min="1"
            max="20"
            value={formData.level}
            onChange={(e) => handleInputChange('level', parseInt(e.target.value))}
            className="input-field"
          />
        </div>
        
        <div className="md:col-span-2">
          <label className="block text-sm font-medium text-secondary-700 mb-2">Description</label>
          <input
            type="text"
            value={formData.description}
            onChange={(e) => handleInputChange('description', e.target.value)}
            className="input-field"
            placeholder="Brief description of your character"
          />
        </div>
      </div>
    </div>
  );

  const renderStep2 = () => (
    <div className="space-y-6">
      <h3 className="text-xl font-fantasy font-semibold text-secondary-900">Ability Scores</h3>
      
      <div className="text-center mb-6">
        <button
          type="button"
          onClick={rollAllAbilities}
          className="btn-primary flex items-center space-x-2 mx-auto"
        >
          <Dice6 className="h-5 w-5" />
          <span>Roll All Abilities (4d6 drop lowest)</span>
        </button>
      </div>
      
      <div className="grid grid-cols-2 md:grid-cols-3 gap-4">
        {[
          { key: 'strength', label: 'Strength', icon: Shield, color: 'text-red-600' },
          { key: 'dexterity', label: 'Dexterity', icon: Zap, color: 'text-green-600' },
          { key: 'constitution', label: 'Constitution', icon: Heart, color: 'text-orange-600' },
          { key: 'intelligence', label: 'Intelligence', icon: Brain, color: 'text-blue-600' },
          { key: 'wisdom', label: 'Wisdom', icon: Eye, color: 'text-purple-600' },
          { key: 'charisma', label: 'Charisma', icon: Heart, color: 'text-pink-600' }
        ].map(({ key, label, icon: Icon, color }) => (
          <div key={key} className="text-center">
            <Icon className={`h-8 w-8 ${color} mx-auto mb-2`} />
            <label className="block text-sm font-medium text-secondary-700 mb-2">{label}</label>
            <input
              type="number"
              min="3"
              max="20"
              value={formData[key as keyof CharacterForm] as number}
              onChange={(e) => handleInputChange(key as keyof CharacterForm, parseInt(e.target.value))}
              className="input-field text-center text-lg font-bold"
            />
            <div className="text-sm text-secondary-600 mt-1">
              {getModifier(formData[key as keyof CharacterForm] as number)}
            </div>
          </div>
        ))}
      </div>

      <div className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Background</label>
          <textarea
            value={formData.background}
            onChange={(e) => handleInputChange('background', e.target.value)}
            className="input-field"
            rows={3}
            placeholder="Describe your character's background..."
          />
        </div>
        
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Personality</label>
          <textarea
            value={formData.personality}
            onChange={(e) => handleInputChange('personality', e.target.value)}
            className="input-field"
            rows={2}
            placeholder="What makes your character unique?"
          />
        </div>
        
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Equipment</label>
          <textarea
            value={formData.equipment}
            onChange={(e) => handleInputChange('equipment', e.target.value)}
            className="input-field"
            rows={2}
            placeholder="What equipment does your character have?"
          />
        </div>
        
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Notes</label>
          <textarea
            value={formData.notes}
            onChange={(e) => handleInputChange('notes', e.target.value)}
            className="input-field"
            rows={2}
            placeholder="Any additional notes about your character..."
          />
        </div>
      </div>
    </div>
  );

  return (
    <div className="max-w-4xl mx-auto">
      <div className="flex items-center space-x-4 mb-8">
        <button
          onClick={() => navigate('/')}
          className="btn-secondary flex items-center space-x-2"
        >
          <ArrowLeft className="h-5 w-5" />
          <span>Back</span>
        </button>
        <h2 className="text-3xl font-fantasy font-bold text-secondary-900">
          Create New Character
        </h2>
      </div>

      {/* Progress Steps */}
      <div className="flex items-center justify-center mb-8">
        {[1, 2].map((step) => (
          <div key={step} className="flex items-center">
            <div className={`w-8 h-8 rounded-full flex items-center justify-center text-sm font-medium ${
              step <= currentStep 
                ? 'bg-primary-600 text-white' 
                : 'bg-secondary-200 text-secondary-600'
            }`}>
              {step}
            </div>
            {step < 2 && (
              <div className={`w-16 h-1 mx-2 ${
                step < currentStep ? 'bg-primary-600' : 'bg-secondary-200'
              }`} />
            )}
          </div>
        ))}
      </div>

      <form onSubmit={handleSubmit} className="card">
        {currentStep === 1 && renderStep1()}
        {currentStep === 2 && renderStep2()}

        <div className="flex justify-between pt-6 border-t border-secondary-200">
          <button
            type="button"
            onClick={prevStep}
            disabled={currentStep === 1}
            className={`btn-secondary ${currentStep === 1 ? 'opacity-50 cursor-not-allowed' : ''}`}
          >
            Previous
          </button>
          
          <div className="flex space-x-4">
            {currentStep < 2 ? (
              <button
                type="button"
                onClick={nextStep}
                className="btn-primary"
              >
                Next
              </button>
            ) : (
              <button
                type="submit"
                className="btn-primary flex items-center space-x-2"
              >
                <Save className="h-5 w-5" />
                <span>Create Character</span>
              </button>
            )}
          </div>
        </div>
      </form>
    </div>
  );
};

export default CharacterCreator;
