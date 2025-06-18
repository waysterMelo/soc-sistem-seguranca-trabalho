import React, { useState } from 'react';
import {
    Plus,
    Upload,
    ChevronsUpDown,
    Pencil,
    Trash2,
    ChevronLeft,
    ChevronRight,
    ChevronsLeft,
    ChevronsRight,
} from 'lucide-react';
import {Link} from "react-router-dom";

// --- Dados de Exemplo ---
const prestadoresData = [
    {
        nome: 'MEDICO TESTE WAYSTER',
        cbo: '010205 - Oficial da Aeronáutica',
        documento: '119.824.196-95',
        status: 'Ativo'
    }
];

// --- Componentes Reutilizáveis ---

// Cabeçalho da tabela com ícone de ordenação
const TableHeader = ({ children }) => (
    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider whitespace-nowrap">
        <div className="flex items-center space-x-1 cursor-pointer hover:text-gray-700">
            <span>{children}</span>
            <ChevronsUpDown size={14} className="text-gray-400" />
        </div>
    </th>
);

// Selo de status colorido
const StatusBadge = ({ status }) => {
    const baseClasses = "inline-block px-2.5 py-0.5 text-xs font-semibold rounded-full";
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

export default function ListarPrestadores() {
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage, setEntriesPerPage] = useState(5);

    const filteredData = prestadoresData.filter(p =>
        p.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
        p.documento.includes(searchTerm)
    );

    const indexOfLastEntry = currentPage * entriesPerPage;
    const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
    const currentEntries = filteredData.slice(indexOfFirstEntry, indexOfLastEntry);
    const totalPages = Math.ceil(filteredData.length / entriesPerPage);

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                {/* Cabeçalho e Botões de Ação */}
                <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
                    <h1 className="text-3xl font-bold text-gray-900 mb-4 sm:mb-0">Prestadores de Serviços</h1>
                    <div className="flex flex-wrap gap-2">
                        <Link
                            to="/cadastros/novo-prestador"
                            className="flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors"
                        >
                            <Plus size={16} />
                            <span>Nova Prestador</span>
                        </Link>
                    </div>
                </div>

                {/* Filtros e Tabela */}
                <div className="bg-white p-4 rounded-lg shadow-md">
                    {/* Barra de Busca e Filtros da Tabela */}
                    <div className="flex flex-col sm:flex-row justify-between items-center mb-4 gap-2">
                        <input
                            type="text"
                            placeholder="Procure por algum registro..."
                            className="w-full sm:flex-grow pl-4 pr-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                        <div className='flex w-full sm:w-auto gap-2'>
                            <select
                                className="w-full sm:w-auto border border-gray-300 rounded-md px-3 py-2 focus:outline-none"
                                value={entriesPerPage}
                                onChange={(e) => setEntriesPerPage(Number(e.target.value))}
                            >
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                            </select>
                        </div>
                    </div>

                    {/* Tabela de Prestadores */}
                    <div className="overflow-x-auto">
                        <table className="min-w-full divide-y divide-gray-200">
                            <thead className="bg-gray-50">
                            <tr>
                                <TableHeader>Nome</TableHeader>
                                <TableHeader>CBO</TableHeader>
                                <TableHeader>Documento</TableHeader>
                                <TableHeader>Status</TableHeader>
                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                            </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                            {currentEntries.map((prestador, index) => (
                                <tr key={index} className="hover:bg-gray-50">
                                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{prestador.nome}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{prestador.cbo}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{prestador.documento}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm"><StatusBadge status={prestador.status} /></td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm">
                                        <div className="flex items-center space-x-3">
                                            <button className="text-green-600 hover:text-green-800"><Plus size={18} /></button>
                                            <button className="text-blue-600 hover:text-blue-800"><Pencil size={18} /></button>
                                            <button className="text-red-600 hover:text-red-800"><Trash2 size={18} /></button>
                                        </div>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>

                    {/* Paginação */}
                    <div className="flex flex-col sm:flex-row justify-between items-center pt-4 border-t border-gray-200">
                        <p className="text-sm text-gray-700 mb-2 sm:mb-0">
                            Mostrando de <span className="font-medium">{indexOfFirstEntry + 1}</span> até <span className="font-medium">{Math.min(indexOfLastEntry, filteredData.length)}</span> de <span className="font-medium">{filteredData.length}</span> registros
                        </p>
                        <div className="flex items-center space-x-1">
                            <button onClick={() => setCurrentPage(1)} disabled={currentPage === 1} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"><ChevronsLeft size={18} /></button>
                            <button onClick={() => setCurrentPage(prev => Math.max(prev - 1, 1))} disabled={currentPage === 1} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"><ChevronLeft size={18} /></button>
                            <span className="px-4 py-2 text-sm font-medium bg-blue-600 text-white rounded-md">{currentPage}</span>
                            <button onClick={() => setCurrentPage(prev => Math.min(prev + 1, totalPages))} disabled={currentPage === totalPages} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"><ChevronRight size={18} /></button>
                            <button onClick={() => setCurrentPage(totalPages)} disabled={currentPage === totalPages} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"><ChevronsRight size={18} /></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
