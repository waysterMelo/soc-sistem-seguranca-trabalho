import React from 'react';

export function Card({ children, className = '' }) {
    return (
        <div className={`bg-white rounded-2xl p-5 shadow-sm ${className}`}>
            {children}
        </div>
    );
}