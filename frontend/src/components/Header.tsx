import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Sword, Users, Plus, Database } from 'lucide-react';

const Header: React.FC = () => {
  const location = useLocation();

  const isActive = (path: string) => {
    return location.pathname === path;
  };

  return (
    <header className="bg-white shadow-md border-b border-secondary-200">
      <div className="container mx-auto px-4">
        <div className="flex items-center justify-between h-16">
          <Link to="/" className="flex items-center space-x-2">
            <Sword className="h-8 w-8 text-primary-600" />
            <h1 className="text-2xl font-fantasy font-bold text-primary-800">
              Draw Steel
            </h1>
          </Link>
          
          <nav className="flex items-center space-x-6">
            <Link
              to="/"
              className={`flex items-center space-x-2 px-3 py-2 rounded-md transition-colors duration-200 ${
                isActive('/')
                  ? 'bg-primary-100 text-primary-700'
                  : 'text-secondary-600 hover:text-primary-600 hover:bg-primary-50'
              }`}
            >
              <Users className="h-5 w-5" />
              <span>Characters</span>
            </Link>
            
            <Link
              to="/create"
              className={`flex items-center space-x-2 px-3 py-2 rounded-md transition-colors duration-200 ${
                isActive('/create')
                  ? 'bg-primary-100 text-primary-700'
                  : 'text-secondary-600 hover:text-primary-600 hover:bg-primary-50'
              }`}
            >
              <Plus className="h-5 w-5" />
              <span>Create</span>
            </Link>
            
            <Link
              to="/database"
              className={`flex items-center space-x-2 px-3 py-2 rounded-md transition-colors duration-200 ${
                isActive('/database')
                  ? 'bg-primary-100 text-primary-700'
                  : 'bg-yellow-100 text-yellow-700 hover:bg-yellow-200'
              }`}
            >
              <Database className="h-5 w-5" />
              <span>Database</span>
            </Link>
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;
