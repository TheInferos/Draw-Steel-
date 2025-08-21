import React from 'react';
import { Link } from 'react-router-dom';
import { Sword } from 'lucide-react';

interface EmptyStateProps {
  title: string;
  description: string;
  actionText: string;
  actionLink: string;
}

const EmptyState: React.FC<EmptyStateProps> = ({ 
  title, 
  description, 
  actionText, 
  actionLink 
}) => {
  return (
    <div className="text-center py-12">
      <Sword className="h-16 w-16 text-secondary-400 mx-auto mb-4" />
      <h3 className="text-xl font-medium text-secondary-600 mb-2">
        {title}
      </h3>
      <p className="text-secondary-500 mb-6">
        {description}
      </p>
      <Link to={actionLink} className="btn-primary">
        {actionText}
      </Link>
    </div>
  );
};

export default EmptyState;
