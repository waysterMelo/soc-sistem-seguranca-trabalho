import React, { useState } from 'react';
import {
    Upload,
    FileText,
    Plus,
    Search,
    ChevronsUpDown,
    Pencil,
    Trash2,
    ChevronLeft,
    ChevronRight
} from 'lucide-react';
import {Link} from "react-router-dom";

// Dados de exemplo para as empresas
const companiesData = [
    {
        razaoSocial: 'WAYSTER HENRIQUE CRUZ DE MELO',
        nomeFantasia: '',
        documento: '59.413.555/0001-08',
        status: 'Ativo',
    },
    {
        razaoSocial: 'MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA',
        nomeFantasia: 'EXCLUSIVE ARQUINDEX',
        documento: '44.008.207/0001-88',
        status: 'Ativo',
    },
    {
        razaoSocial: 'ABC LOGISTICA E TRANSPORTE LTDA',
        nomeFantasia: 'ABC LOG',
        documento: '12.345.678/0001-99',
        status: 'Inativo',
    },
    {
        razaoSocial: 'SOFTWARE HOUSE INOVADORA S.A.',
        nomeFantasia: 'TECH SOLUTIONS',
        documento: '98.765.432/0001-11',
        status: 'Em Revisão',
    }
];

// Componente para o selo de status
const StatusBadge = ({ status }) => {
    const baseClasses = "px-2.5 py-0.5 text-xs font-semibold rounded-full";
    const statusClasses = {
        'Ativo': 'bg-green-100 text-green-700',
        'Inativo': 'bg-red-100 text-red-700',
        'Em Revisão': 'bg-yellow-100 text-yellow-700',
    };
    return (
        <span className={`${baseClasses} ${statusClasses[status] || 'bg-gray-100 text-gray-700'}`}>
            {status}
        </span>
    );
};

// Componente para o cabeçalho da tabela
const TableHeader = ({ children }) => (
    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
        <div className="flex items-center space-x-1 cursor-pointer">
            <span>{children}</span>
            <ChevronsUpDown size={14} className="text-gray-400" />
        </div>
    </th>
);

export default function ListarEmpresas() {
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage, setEntriesPerPage] = useState(5);

    // Filtra as empresas com base no termo de busca
    const filteredCompanies = companiesData.filter(company =>
        company.razaoSocial.toLowerCase().includes(searchTerm.toLowerCase()) ||
        company.nomeFantasia.toLowerCase().includes(searchTerm.toLowerCase()) ||
        company.documento.includes(searchTerm)
    );

    // Lógica de paginação
    const indexOfLastEntry = currentPage * entriesPerPage;
    const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
    const currentEntries = filteredCompanies.slice(indexOfFirstEntry, indexOfLastEntry);
    const totalPages = Math.ceil(filteredCompanies.length / entriesPerPage);

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                {/* Cabeçalho e Botões de Ação */}
                <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
                    <h1 className="text-3xl font-bold text-gray-900 mb-4 sm:mb-0">Empresas</h1>
                    <div className="flex space-x-2">
                        <button className="flex items-center space-x-2 bg-white border border-gray-300 text-gray-700 px-4 py-2 rounded-md text-sm font-medium hover:bg-gray-50 transition-colors">
                            <Upload size={16} />
                            <span>Importar Empresa</span>
                        </button>
                        <button className="flex items-center space-x-2 bg-yellow-400 text-gray-800 px-4 py-2 rounded-md text-sm font-medium hover:bg-yellow-500 transition-colors">
                            <FileText size={16} />
                            <span>Gerar Relatório</span>
                        </button>
                        <Link
                            to="/cadastros/nova-empresa"
                            className="flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors"
                        >
                            <Plus size={16} />
                            <span>Nova Empresa</span>
                        </Link>
                    </div>
                </div>

                {/* Filtros e Busca */}
                <div className="bg-white p-4 rounded-lg shadow-md">
                    <div className="flex flex-col sm:flex-row justify-between items-center mb-4">
                        <div className="relative w-full sm:w-auto sm:max-w-xs">
                            <Search size={18} className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400" />
                            <input
                                type="text"
                                placeholder="Procure por algum registro..."
                                className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                            />
                        </div>
                        <select
                            className="w-full sm:w-auto mt-2 sm:mt-0 border border-gray-300 rounded-md px-3 py-2 focus:outline-none"
                            value={entriesPerPage}
                            onChange={(e) => {
                                setEntriesPerPage(Number(e.target.value));
                                setCurrentPage(1); // Reset page on changing entries
                            }}
                        >
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                        </select>
                    </div>

                    {/* Tabela de Empresas */}
                    <div className="overflow-x-auto">
                        <table className="min-w-full divide-y divide-gray-200">
                            <thead className="bg-gray-50">
                            <tr>
                                <TableHeader>Razão Social</TableHeader>
                                <TableHeader>Nome Fantasia</TableHeader>
                                <TableHeader>Documento</TableHeader>
                                <TableHeader>Status</TableHeader>
                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                            </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                            {currentEntries.length > 0 ? (
                                currentEntries.map((company, index) => (
                                    <tr key={index} className="hover:bg-gray-50">
                                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{company.razaoSocial}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{company.nomeFantasia || '--'}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{company.documento}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                            <StatusBadge status={company.status} />
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                            <div className="flex items-center space-x-3">
                                                <button className="text-blue-600 hover:text-blue-800"><Pencil size={18} /></button>
                                                <button className="text-red-600 hover:text-red-800"><Trash2 size={18} /></button>
                                            </div>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="5" className="px-6 py-4 text-center text-gray-500">Nenhum registro encontrado.</td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </div>

                    {/* Paginação */}
                    <div className="flex flex-col sm:flex-row justify-between items-center pt-4 border-t border-gray-200">
                        <p className="text-sm text-gray-700 mb-2 sm:mb-0">
                            Mostrando de <span className="font-medium">{indexOfFirstEntry + 1}</span> até <span className="font-medium">{Math.min(indexOfLastEntry, filteredCompanies.length)}</span> de <span className="font-medium">{filteredCompanies.length}</span> registros
                        </p>
                        <div className="flex items-center space-x-1">
                            <button
                                onClick={() => setCurrentPage(1)}
                                disabled={currentPage === 1}
                                className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                <ChevronLeft size={16} />
                                <ChevronLeft size={16} className="-ml-3"/>
                            </button>
                            <button
                                onClick={() => setCurrentPage(prev => Math.max(prev - 1, 1))}
                                disabled={currentPage === 1}
                                className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                <ChevronLeft size={16} />
                            </button>
                            <span className="px-4 py-2 text-sm font-medium bg-blue-600 text-white rounded-md">{currentPage}</span>
                            <button
                                onClick={() => setCurrentPage(prev => Math.min(prev + 1, totalPages))}
                                disabled={currentPage === totalPages || currentEntries.length === 0}
                                className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                <ChevronRight size={16} />
                            </button>
                            <button
                                onClick={() => setCurrentPage(totalPages)}
                                disabled={currentPage === totalPages || currentEntries.length === 0}
                                className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                <ChevronRight size={16} />
                                <ChevronRight size={16} className="-ml-3"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}