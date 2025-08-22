import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Save, ArrowLeft } from 'lucide-react';
import { apiService, CreateCharacterRequest, Ancestry, Culture, Career, Kit, CharacterClass } from '../services/api';

const CharacterCreator: React.FC = () => {
  const navigate = useNavigate();
  const [currentStep, setCurrentStep] = useState(1);
  const [loading, setLoading] = useState(false);
  const [formData, setFormData] = useState<CreateCharacterRequest>({
    name: '',
    description: '',
    level: 1,
    ancestryId: '',
    cultureId: '',
    careerId: '',
    kitId: '',
    characterClassId: '',
    might: 0,
    agility: 0,
    reason: 0,
    intuition: 0,
    presence: 0,
    speed: 5,
    stability: 0,
    abilityIds: [],
    complicationIds: []
  });

  // State for dropdown options
  const [ancestries, setAncestries] = useState<Ancestry[]>([]);
  const [cultures, setCultures] = useState<Culture[]>([]);
  const [careers, setCareers] = useState<Career[]>([]);
  const [kits, setKits] = useState<Kit[]>([]);
  const [characterClasses, setCharacterClasses] = useState<CharacterClass[]>([]);

  useEffect(() => {
    // Load dropdown options
    const loadOptions = async () => {
      try {
        const [ancestriesData, culturesData, careersData, kitsData, classesData] = await Promise.all([
          apiService.getAncestries(),
          apiService.getCultures(),
          apiService.getCareers(),
          apiService.getKits(),
          apiService.getCharacterClasses()
        ]);
        setAncestries(ancestriesData);
        setCultures(culturesData);
        setCareers(careersData);
        setKits(kitsData);
        setCharacterClasses(classesData);
      } catch (error) {
        console.error('Failed to load options:', error);
      }
    };

    loadOptions();
  }, []);

  const handleInputChange = (field: keyof CreateCharacterRequest, value: string | number | string[]) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }));
  };

  const nextStep = () => {
    if (currentStep < 2) setCurrentStep(currentStep + 1);
  };

  const prevStep = () => {
    if (currentStep > 1) setCurrentStep(currentStep - 1);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    
    try {
      await apiService.createCharacter(formData);
      navigate('/');
    } catch (error) {
      console.error('Failed to create character:', error);
      // TODO: Add proper error handling
    } finally {
      setLoading(false);
    }
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
      <h3 className="text-xl font-fantasy font-semibold text-secondary-900">Character Identity</h3>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Ancestry</label>
          <select
            value={formData.ancestryId}
            onChange={(e) => handleInputChange('ancestryId', e.target.value)}
            className="input-field"
          >
            <option value="">Select Ancestry</option>
            {ancestries.map(ancestry => (
              <option key={ancestry.id} value={ancestry.id}>{ancestry.name}</option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Culture</label>
          <select
            value={formData.cultureId}
            onChange={(e) => handleInputChange('cultureId', e.target.value)}
            className="input-field"
          >
            <option value="">Select Culture</option>
            {cultures.map(culture => (
              <option key={culture.id} value={culture.id}>{culture.name}</option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Career</label>
          <select
            value={formData.careerId}
            onChange={(e) => handleInputChange('careerId', e.target.value)}
            className="input-field"
          >
            <option value="">Select Career</option>
            {careers.map(career => (
              <option key={career.id} value={career.id}>{career.name}</option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Class</label>
          <select
            value={formData.characterClassId}
            onChange={(e) => handleInputChange('characterClassId', e.target.value)}
            className="input-field"
          >
            <option value="">Select Class</option>
            {characterClasses.map(charClass => (
              <option key={charClass.id} value={charClass.id}>{charClass.name}</option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-secondary-700 mb-2">Kit</label>
          <select
            value={formData.kitId}
            onChange={(e) => handleInputChange('kitId', e.target.value)}
            className="input-field"
          >
            <option value="">Select Kit</option>
            {kits.map(kit => (
              <option key={kit.id} value={kit.id}>{kit.name}</option>
            ))}
          </select>
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
                disabled={loading}
                className="btn-primary flex items-center space-x-2"
              >
                <Save className="h-5 w-5" />
                <span>{loading ? 'Creating...' : 'Create Character'}</span>
              </button>
            )}
          </div>
        </div>
      </form>
    </div>
  );
};

export default CharacterCreator;
