import React, { useState } from 'react';
import {
    Plus,
    Search,
    Trash2,
    ChevronsUpDown
} from 'lucide-react';
import {Link} from "react-router-dom";

// --- Componentes Reutilizáveis ---

// Componente para o cabeçalho da tabela com ícone de ordenação
const TableHeader = ({ children }) => (
    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
        <div className="flex items-center space-x-1 cursor-pointer hover:text-gray-700">
            <span>{children}</span>
            <ChevronsUpDown size={14} className="text-gray-400" />
        </div>
    </th>
);

// Componente para o selo de status
const StatusBadge = ({ status }) => {
    const baseClasses = "px-2.5 py-0.5 text-xs font-semibold rounded-full";
    const statusClasses = {
        'Ativo': 'bg-green-100 text-green-700',
        'Inativo': 'bg-red-100 text-red-700',
    };
    return (
        <span className={`${baseClasses} ${statusClasses[status] || 'bg-gray-100 text-gray-700'}`}>
            {status}
        </span>
    );
};

// --- Componente Principal ---

export default function ListarUnidades() {
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage, setEntriesPerPage] = useState(5);

    // Como não há dados, a lista de entradas estará vazia
    const currentEntries = [];

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                {/* Cabeçalho e Botão de Ação Principal */}
                <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
                    <h1 className="text-3xl font-bold text-gray-900 mb-4 sm:mb-0">Unidades Operacionais</h1>
                    <Link
                        to="/cadastros/nova-unidade"
                        className="flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors"
                    >
                        <Plus size={16} />
                        <span>Nova Unidade</span>
                    </Link>
                </div>

                {/* Filtros e Tabela */}
                <div className="bg-white p-4 rounded-lg shadow-md">
                    {/* Filtro por Empresa */}
                    <div className="mb-4">
                        <label className="text-sm font-medium text-gray-600">Filtrar por empresa</label>
                        <div className="relative flex items-center mt-1">
                            <input
                                type="text"
                                placeholder="Nenhuma Empresa selecionada"
                                className="w-full py-2 px-3 border border-gray-300 rounded-md bg-gray-50 focus:outline-none focus:ring-2 focus:ring-blue-500 transition-colors"
                                disabled
                            />
                            <div className="absolute right-0 flex">
                                <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600">
                                    <Search size={18}/>
                                </button>
                                <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600">
                                    <Trash2 size={18}/>
                                </button>
                            </div>
                        </div>
                    </div>

                    {/* Barra de Busca e Entradas por Página */}
                    <div className="flex flex-col sm:flex-row justify-between items-center mb-4">
                        <div className="relative w-full sm:w-auto sm:flex-grow sm:max-w-md">
                            <input
                                type="text"
                                placeholder="Procure por algum registro..."
                                className="w-full pl-4 pr-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                            />
                        </div>
                        <select
                            className="w-full sm:w-auto mt-2 sm:mt-0 border border-gray-300 rounded-md px-3 py-2 focus:outline-none"
                            value={entriesPerPage}
                            onChange={(e) => setEntriesPerPage(Number(e.target.value))}
                        >
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                        </select>
                    </div>

                    {/* Tabela de Unidades */}
                    <div className="overflow-x-auto">
                        <table className="min-w-full divide-y divide-gray-200">
                            <thead className="bg-gray-50">
                            <tr>
                                <TableHeader>Unidade</TableHeader>
                                <TableHeader>Empresa</TableHeader>
                                <TableHeader>Total de Setores Vinculados</TableHeader>
                                <TableHeader>Status</TableHeader>
                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                            </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                            {currentEntries.length > 0 ? (
                                currentEntries.map((unidade, index) => (
                                    <tr key={index} className="hover:bg-gray-50">
                                        {/* Células da tabela com dados */}
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="5" className="px-6 py-12 text-center text-gray-500">
                                        Nenhum registro encontrado!
                                    </td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </div>

                    {/* Paginação (será exibida se houver dados) */}
                    {currentEntries.length > 0 && (
                        <div className="flex flex-col sm:flex-row justify-between items-center pt-4 border-t border-gray-200">
                            {/* Lógica de Paginação aqui */}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}
