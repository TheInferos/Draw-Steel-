import React, { useState } from 'react';
import { Save, Database, AlertCircle, CheckCircle } from 'lucide-react';
import culturesData from '../data/cultures.json';
import careersData from '../data/careers.json';
import ancestriesData from '../data/ancestries.json';
import traitsData from '../data/traits.json';

interface SeedData {
  ancestries: Array<{ name: string; description?: string }>;
  cultures: Array<{ 
    name: string; 
    description: string;
    skillGroups: string[];
    quickBuild: string;
  }>;
  careers: Array<{ name: string; description?: string, skills?: string[], skillGroups?: string[], quickBuild?: string[], incitingIncidents?: string[], renown?: number, wealth?: number, projectPoints?: number }>;
  traits: Array<{ name: string; description?: string; cost?: number; signatureToggle?: boolean }>;
  kits: Array<{ name: string; description?: string }>;
  characterClasses: Array<{ name: string; description?: string }>;
  abilities: Array<{ name: string; description?: string }>;
  complications: Array<{ name: string; description?: string }>;
}

const DataSeeder: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [results, setResults] = useState<{ [key: string]: { success: boolean; message: string } }>({});

  // Sample data for Draw Steel RPG - customize this with your actual game data
  const seedData: SeedData = {
    ancestries: ancestriesData,
    cultures: culturesData,
    careers: careersData,
    traits: traitsData,
    kits: [
      { name: 'Soldier', description: 'Military training and equipment.' },
      { name: 'Scholar', description: 'Academic tools and research materials.' },
      { name: 'Trader', description: 'Merchant supplies and trade goods.' },
      { name: 'Craftsman', description: 'Tools and materials for crafting.' },
      { name: 'Explorer', description: 'Survival gear and mapping tools.' }
    ],
    characterClasses: [
      { name: 'Fighter', description: 'Masters of martial combat and physical prowess.' },
      { name: 'Mage', description: 'Wielders of arcane magic and mystical knowledge.' },
      { name: 'Rogue', description: 'Skilled in stealth, deception, and precision.' },
      { name: 'Cleric', description: 'Divine servants who channel spiritual power.' },
      { name: 'Ranger', description: 'Wilderness experts who blend combat and nature magic.' }
    ],
    abilities: [
      { name: 'Combat Mastery', description: 'Superior skill in armed and unarmed combat.' },
      { name: 'Arcane Insight', description: 'Deep understanding of magical principles.' },
      { name: 'Shadow Step', description: 'Ability to move unseen through shadows.' },
      { name: 'Divine Favor', description: 'Blessed with supernatural protection and guidance.' },
      { name: 'Wild Empathy', description: 'Natural connection with animals and nature.' }
    ],
    complications: [
      { name: 'Haunted Past', description: 'Troubled history that occasionally resurfaces.' },
      { name: 'Social Stigma', description: 'Viewed with suspicion by certain groups.' },
      { name: 'Physical Limitation', description: 'A permanent injury or condition.' },
      { name: 'Addiction', description: 'Dependency on a substance or behavior.' },
      { name: 'Family Obligation', description: 'Duties that conflict with personal goals.' }
    ]
  };

  const seedCulturesOnly = async () => {
    setLoading(true);
    setResults({});

    try {
      await seedCultures();
      setResults({
        overall: { success: true, message: 'Cultures seeded successfully!' }
      });
    } catch (error) {
      console.error('Cultures seeding failed:', error);
      setResults({
        overall: { success: false, message: `Cultures seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

  const seedCareersOnly = async () => {
    setLoading(true);
    setResults({});

    try {
      await seedCareers();
      setResults({
        overall: { success: true, message: 'Careers seeded successfully!' }
      });
    } catch (error) {
      console.error('Careers seeding failed:', error);
      setResults({
        overall: { success: false, message: `Careers seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

  const seedTraitsOnly = async () => {
    setLoading(true);
    setResults({});

    try {
      await seedTraits();
      setResults({
        overall: { success: true, message: 'Traits seeded successfully!' }
      });
    } catch (error) {
      console.error('Traits seeding failed:', error);
      setResults({
        overall: { success: false, message: `Traits seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

  const seedDataToDatabase = async () => {
    setLoading(true);
    setResults({});

    try {
      // Seed each data type
      const seedPromises = [
        seedAncestries(),
        seedCultures(),
        seedCareers(),
        seedTraits(),
        seedKits(),
        seedCharacterClasses(),
        seedAbilities(),
        seedComplications()
      ];

      await Promise.all(seedPromises);
      setResults({
        overall: { success: true, message: 'All data seeded successfully!' }
      });
    } catch (error) {
      console.error('Seeding failed:', error);
      setResults({
        overall: { success: false, message: `Seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

  const seedAncestries = async () => {
    try {
      for (const ancestry of seedData.ancestries) {
        await fetch('/api/ancestries', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(ancestry)
        });
      }
      setResults(prev => ({ ...prev, ancestries: { success: true, message: 'Ancestries seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, ancestries: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedCultures = async () => {
    try {
      for (const culture of seedData.cultures) {
        await fetch('/api/cultures', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(culture)
        });
      }
      setResults(prev => ({ ...prev, cultures: { success: true, message: 'Cultures seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, cultures: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedTraits = async () => {
    try {
      for (const trait of seedData.traits) {
        await fetch('/api/traits', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(trait)
        });
      }
      setResults(prev => ({ ...prev, traits: { success: true, message: 'Traits seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, traits: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedCareers = async () => {
    try {
      for (const career of seedData.careers) {
        await fetch('/api/careers', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(career)
        });
      }
      setResults(prev => ({ ...prev, careers: { success: true, message: 'Careers seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, careers: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedKits = async () => {
    try {
      for (const kit of seedData.kits) {
        await fetch('/api/kits', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(kit)
        });
      }
      setResults(prev => ({ ...prev, kits: { success: true, message: 'Kits seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, kits: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedCharacterClasses = async () => {
    try {
      for (const charClass of seedData.characterClasses) {
        await fetch('/api/character-classes', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(charClass)
        });
      }
      setResults(prev => ({ ...prev, characterClasses: { success: true, message: 'Character Classes seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, characterClasses: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedAbilities = async () => {
    try {
      for (const ability of seedData.abilities) {
        await fetch('/api/abilities', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(ability)
        });
      }
      setResults(prev => ({ ...prev, abilities: { success: true, message: 'Abilities seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, abilities: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const seedComplications = async () => {
    try {
      for (const complication of seedData.complications) {
        await fetch('/api/complications', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(complication)
        });
      }
      setResults(prev => ({ ...prev, complications: { success: true, message: 'Complications seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, complications: { success: false, message: `Failed: ${error}` } }));
      throw error;
    }
  };

  const renderResult = (key: string, result: { success: boolean; message: string }) => (
    <div key={key} className={`flex items-center space-x-2 p-2 rounded ${
      result.success ? 'bg-green-50 text-green-700' : 'bg-red-50 text-red-700'
    }`}>
      {result.success ? (
        <CheckCircle className="h-4 w-4" />
      ) : (
        <AlertCircle className="h-4 w-4" />
      )}
      <span className="text-sm font-medium">{key}: {result.message}</span>
    </div>
  );

  return (
    <div className="max-w-4xl mx-auto p-6">
      <div className="card">
        <div className="flex items-center space-x-3 mb-6">
          <Database className="h-8 w-8 text-primary-600" />
          <h2 className="text-2xl font-fantasy font-bold text-secondary-900">
            Database Seeder
          </h2>
        </div>

        <div className="mb-6">
          <div className="flex space-x-4 mb-4">
            <button
              onClick={seedCulturesOnly}
              disabled={loading}
              className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
            >
              {loading ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  <span>Seeding Cultures...</span>
                </>
              ) : (
                <>
                  <Database className="h-4 w-4" />
                  <span>Seed Cultures Only</span>
                </>
              )}
            </button>

            <button
              onClick={seedCareersOnly}
              disabled={loading}
              className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
            >
              {loading ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  <span>Seeding Careers...</span>
                </>
              ) : (
                <>
                  <Database className="h-4 w-4" />
                  <span>Seed Careers Only</span>
                </>
              )}
            </button>

            <button
              onClick={seedTraitsOnly}
              disabled={loading}
              className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
            >
              {loading ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  <span>Seeding Traits...</span>
                </>
              ) : (
                <>
                  <Database className="h-4 w-4" />
                  <span>Seed Traits Only</span>
                </>
              )}
            </button>
          </div>
        </div>

        <div className="mb-6">
          <p className="text-secondary-600 mb-4">
            This tool will populate your database with starting data for the Draw Steel RPG system. 
            Make sure your backend API is running before proceeding.
          </p>
          
          <div className="bg-blue-50 border border-blue-200 rounded-lg p-4">
            <h3 className="font-semibold text-blue-900 mb-2">What will be seeded:</h3>
            <ul className="text-sm text-blue-800 space-y-1">
              <li>• {seedData.ancestries.length} Ancestries (Human, Dwarf, Elf, etc.)</li>
              <li>• {seedData.cultures.length} Cultures (Nomadic, Rural, Wilderness, etc.)</li>
              <li>• {seedData.careers.length} Careers (Warrior, Scholar, Merchant, etc.)</li>
              <li>• {seedData.traits.length} Traits (Silver Tongue, Barbed Tail, Beast Legs, etc.)</li>
              <li>• {seedData.kits.length} Kits (Soldier, Scholar, Trader, etc.)</li>
              <li>• {seedData.characterClasses.length} Character Classes (Fighter, Mage, Rogue, etc.)</li>
              <li>• {seedData.abilities.length} Abilities (Combat Mastery, Arcane Insight, etc.)</li>
              <li>• {seedData.complications.length} Complications (Haunted Past, Social Stigma, etc.)</li>
            </ul>
          </div>
        </div>

        <button
          onClick={seedDataToDatabase}
          disabled={loading}
          className="btn-primary w-full flex items-center justify-center space-x-2 py-3"
        >
          {loading ? (
            <>
              <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white"></div>
              <span>Seeding Database...</span>
            </>
          ) : (
            <>
              <Save className="h-5 w-5" />
              <span>Seed Database</span>
            </>
          )}
        </button>

        {Object.keys(results).length > 0 && (
          <div className="mt-6 space-y-2">
            <h3 className="font-semibold text-secondary-900">Results:</h3>
            {Object.entries(results).map(([key, result]) => renderResult(key, result))}
          </div>
        )}

        <div className="mt-6 p-4 bg-yellow-50 border border-yellow-200 rounded-lg">
          <h3 className="font-semibold text-yellow-900 mb-2">Important Notes:</h3>
          <ul className="text-sm text-yellow-800 space-y-1">
            <li>• This will only work if your backend API endpoints exist and accept POST requests</li>
            <li>• The data is designed for the Draw Steel RPG system - customize as needed</li>
            <li>• Remove this component from production builds</li>
            <li>• Consider adding duplicate checking if you plan to run this multiple times</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default DataSeeder;

