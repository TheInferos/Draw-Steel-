import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import CharacterList from './components/CharacterList';
import CharacterCreator from './components/CharacterCreator';
import CharacterView from './components/CharacterView';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App min-h-screen bg-secondary-50">
        <Header />
        <main className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/" element={<CharacterList />} />
            <Route path="/create" element={<CharacterCreator />} />
            <Route path="/character/:id" element={<CharacterView />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
