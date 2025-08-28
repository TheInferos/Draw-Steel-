import React, { useState } from 'react';
import { Save, Database, AlertCircle, CheckCircle } from 'lucide-react';
import culturesData from '../data/cultures.json';
import careersData from '../data/careers.json';
import ancestriesData from '../data/ancestries.json';
import abilitiesData from '../data/abilities.json';
import traitsData from '../data/traits.json';
import perksData from '../data/perks.json';
import kitsData from '../data/kits.json';
import complicationsData from '../data/complication.json';

interface SeedData {
  abilities: Array<{ name: string; description?: string }>;
  ancestries: Array<{ name: string; description?: string }>;
  cultures: Array<{ 
    name: string; 
    description: string;
    skillGroups: string[];
    quickBuild: string;
  }>;
  careers: Array<{ name: string; description?: string, skills?: string[], skillGroups?: string[], quickBuild?: string[], incitingIncidents?: string[], renown?: number, wealth?: number, projectPoints?: number }>;
  traits: Array<{ name: string; description?: string; cost?: number; signatureToggle?: boolean }>;
  perks: Array<{ name: string; description?: string; type?: string }>;
  kits: Array<{ 
    name: string; 
    description: string; 
    stamina: number; 
    speed: number; 
    meleeDamage: { TIER1: number; TIER2: number; TIER3: number }; 
    disengage: number; 
    signatureAbility: { id: string }; 
    armor: string; 
    weapon: string 
  }>;
  characterClasses: Array<{ name: string; description?: string }>;
  complications: Array<{ 
    name: string; 
    description: string; 
    benefit: string; 
    drawback: string 
  }>;
}

const DataSeeder: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [results, setResults] = useState<{ [key: string]: { success: boolean; message: string } }>({});

  // Sample data for Draw Steel RPG - customize this with your actual game data
  const seedData: SeedData = {
    abilities: abilitiesData,
    ancestries: ancestriesData,
    cultures: culturesData,
    careers: careersData,
    traits: traitsData,
    perks: perksData,
    characterClasses: [
      { name: 'Fighter', description: 'Masters of martial combat and physical prowess.' },
      { name: 'Mage', description: 'Wielders of arcane magic and mystical knowledge.' },
      { name: 'Rogue', description: 'Skilled in stealth, deception, and precision.' },
      { name: 'Cleric', description: 'Divine servants who channel spiritual power.' },
      { name: 'Ranger', description: 'Wilderness experts who blend combat and nature magic.' }
    ],
    complications: complicationsData,
    kits: kitsData
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

  const seedPerksOnly = async () => {
    setLoading(true);
    setResults({});

    try {
      await seedPerks();
      setResults({
        overall: { success: true, message: 'Perks seeded successfully!' }
      });
    } catch (error) {
      console.error('Perks seeding failed:', error);
      setResults({
        overall: { success: false, message: `Perks seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

  const seedAbilitiesOnly = async () => {
    setLoading(true);
    setResults({});

    try {
      await seedAbilities();
      setResults({
        overall: { success: true, message: 'Abilities seeded successfully!' }
      });
    } catch (error) {
      console.error('Abilities seeding failed:', error);
      setResults({
        overall: { success: false, message: `Abilities seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

  const seedKitsOnly = async () => {
    setLoading(true);
    setResults({});

    try {
      await seedKits();
      setResults({
        overall: { success: true, message: 'Kits seeded successfully!' }
      });
    } catch (error) {
      console.error('Kits seeding failed:', error);
      setResults({
        overall: { success: false, message: `Kits seeding failed: ${error}` }
      });
    } finally {
      setLoading(false);
    }
  };

     const seedComplicationsOnly = async () => {
     setLoading(true);
     setResults({});

     try {
       await seedComplications();
       setResults({
         overall: { success: true, message: 'Complications seeded successfully!' }
       });
     } catch (error) {
       console.error('Complications seeding failed:', error);
       setResults({
         overall: { success: false, message: `Complications seeding failed: ${error}` }
       });
     } finally {
       setLoading(false);
     }
   };

   const seedAncestriesOnly = async () => {
     setLoading(true);
     setResults({});

     try {
       await seedAncestries();
       setResults({
         overall: { success: true, message: 'Ancestries seeded successfully!' }
       });
     } catch (error) {
       console.error('Ancestries seeding failed:', error);
       setResults({
         overall: { success: false, message: `Ancestries seeding failed: ${error}` }
       });
     } finally {
       setLoading(false);
     }
   };

  const seedDataToDatabase = async () => {
    setLoading(true);
    setResults({});

    try {
      console.log('Starting to seed all data in dependency order...');
      
      // Sort groups by dependencies (topological sort)
      const sortedGroups = sortGroupsByDependencies(seedingGroups);
      
      for (const group of sortedGroups) {
        console.log(`Seeding group: ${group.name} (${group.id})`);
        
        // Run all seed functions in this group concurrently
        const groupPromises = group.seedFunctions.map(seedFunc => seedFunc());
        await Promise.all(groupPromises);
        
        console.log(`Completed seeding group: ${group.name}`);
        
        // Small delay between groups to ensure database consistency
        if (group.id !== sortedGroups[sortedGroups.length - 1].id) {
          await new Promise(resolve => setTimeout(resolve, 500));
        }
      }
      
      console.log('All seeding completed successfully');
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

  // Helper function to sort groups by dependencies
  const sortGroupsByDependencies = (groups: typeof seedingGroups) => {
    const result: typeof seedingGroups = [];
    const visited = new Set<string>();
    const visiting = new Set<string>();

    const visit = (groupId: string) => {
      if (visiting.has(groupId)) {
        throw new Error(`Circular dependency detected: ${groupId}`);
      }
      if (visited.has(groupId)) return;

      visiting.add(groupId);
      const group = groups.find(g => g.id === groupId);
      if (group) {
        for (const dep of group.dependencies) {
          visit(dep);
        }
        visiting.delete(groupId);
        visited.add(groupId);
        result.push(group);
      }
    };

    for (const group of groups) {
      if (!visited.has(group.id)) {
        visit(group.id);
      }
    }

    return result;
  };

  // Function to seed specific groups
  const seedSpecificGroups = async (groupIds: string[]) => {
    setLoading(true);
    setResults({});

    try {
      console.log(`Starting to seed specific groups: ${groupIds.join(', ')}`);
      
      // Filter groups to only include requested ones and their dependencies
      const requiredGroups = new Set<string>();
      const addGroupAndDeps = (groupId: string) => {
        requiredGroups.add(groupId);
        const group = seedingGroups.find(g => g.id === groupId);
        if (group) {
          for (const dep of group.dependencies) {
            addGroupAndDeps(dep);
          }
        }
      };
      
      groupIds.forEach(addGroupAndDeps);
      
      const groupsToSeed = seedingGroups.filter(g => requiredGroups.has(g.id));
      const sortedGroups = sortGroupsByDependencies(groupsToSeed);
      
      for (const group of sortedGroups) {
        console.log(`Seeding group: ${group.name} (${group.id})`);
        
        const groupPromises = group.seedFunctions.map(seedFunc => seedFunc());
        await Promise.all(groupPromises);
        
        console.log(`Completed seeding group: ${group.name}`);
        
        if (group.id !== sortedGroups[sortedGroups.length - 1].id) {
          await new Promise(resolve => setTimeout(resolve, 500));
        }
      }
      
      console.log('Selected groups seeded successfully');
      setResults({
        overall: { success: true, message: `Groups ${groupIds.join(', ')} seeded successfully!` }
      });
    } catch (error) {
      console.error('Group seeding failed:', error);
      setResults({
        overall: { success: false, message: `Group seeding failed: ${error}` }
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

  const seedPerks = async () => {
    try {
      for (const perk of seedData.perks) {
        await fetch('/api/perks', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(perk)
        });
      }
      setResults(prev => ({ ...prev, perks: { success: true, message: 'Perks seeded' } }));
    } catch (error) {
      setResults(prev => ({ ...prev, perks: { success: false, message: `Failed: ${error}` } }));
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
        const response = await fetch('/api/kits', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(kit)
        });
        if (!response.ok) {
          const errorText = await response.text();
          console.error('Kit seeding failed with status:', response.status, errorText);
          throw new Error(`HTTP ${response.status}: ${errorText}`);
        }
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

  // Define seeding groups with dependencies
  const seedingGroups = [
    {
      id: 'base',
      name: 'Base Data',
      description: 'Core game data that other groups depend on',
      seedFunctions: [seedCultures, seedCareers, seedTraits, seedPerks, seedCharacterClasses, seedComplications],
      dependencies: []
    },
    {
      id: 'abilities',
      name: 'Abilities',
      description: 'Character abilities and powers',
      seedFunctions: [seedAbilities],
      dependencies: ['base']
    },
    {
      id: 'kits',
      name: 'Kits',
      description: 'Character kits that reference abilities',
      seedFunctions: [seedKits],
      dependencies: ['abilities']
    },
    {
      id: 'ancestries',
      name: 'Ancestries',
      description: 'Character ancestries/races',
      seedFunctions: [seedAncestries],
      dependencies: ['base', 'traits']
    }
  ];

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

            <button
              onClick={seedPerksOnly}
              disabled={loading}
              className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
            >
              {loading ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  <span>Seeding Perks...</span>
                </>
              ) : (
                <>
                  <Database className="h-4 w-4" />
                  <span>Seed Perks Only</span>
                </>
              )}
            </button>

            <button
              onClick={seedAbilitiesOnly}
              disabled={loading}
              className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
            >
              {loading ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  <span>Seeding Abilities...</span>
                </>
              ) : (
                <>
                  <Database className="h-4 w-4" />
                  <span>Seed Abilities Only</span>
                </>
              )}
            </button>

            <button
              onClick={seedKitsOnly}
              disabled={loading}
              className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
            >
              {loading ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  <span>Seeding Kits...</span>
                </>
              ) : (
                <>
                  <Database className="h-4 w-4" />
                  <span>Seed Kits Only</span>
                </>
              )}
            </button>

                         <button
               onClick={seedComplicationsOnly}
               disabled={loading}
               className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
             >
               {loading ? (
                 <>
                   <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                   <span>Seeding Complications...</span>
                 </>
               ) : (
                 <>
                   <Database className="h-4 w-4" />
                   <span>Seed Complications Only</span>
                 </>
               )}
             </button>

             <button
               onClick={seedAncestriesOnly}
               disabled={loading}
               className="btn-primary flex items-center justify-center space-x-2 py-2 px-4"
             >
               {loading ? (
                 <>
                   <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                   <span>Seeding Ancestries...</span>
                 </>
               ) : (
                 <>
                   <Database className="h-4 w-4" />
                   <span>Seed Ancestries Only</span>
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
              <li>• {seedData.perks.length} Perks (Brawny, Camouflage Hunter, Danger Sense, etc.)</li>
              <li>• {seedData.kits.length} Kits (Soldier, Scholar, Trader, etc.)</li>
              <li>• {seedData.characterClasses.length} Character Classes (Fighter, Mage, Rogue, etc.)</li>
              <li>• {seedData.abilities.length} Abilities (Combat Mastery, Arcane Insight, etc.)</li>
              <li>• {seedData.complications.length} Complications (Haunted Past, Social Stigma, etc.)</li>
            </ul>
          </div>
        </div>

        <div className="mb-6">
          <h3 className="font-semibold text-secondary-900 mb-3">Group-based Seeding:</h3>
          <div className="grid grid-cols-2 gap-3">
            {seedingGroups.map((group) => (
              <button
                key={group.id}
                onClick={() => seedSpecificGroups([group.id])}
                disabled={loading}
                className="btn-secondary flex flex-col items-start p-3 text-left"
              >
                <span className="font-semibold">{group.name}</span>
                <span className="text-sm text-secondary-600">{group.description}</span>
                {group.dependencies.length > 0 && (
                  <span className="text-xs text-blue-600 mt-1">
                    Depends on: {group.dependencies.join(', ')}
                  </span>
                )}
              </button>
            ))}
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
              <span>Seed All Groups</span>
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

