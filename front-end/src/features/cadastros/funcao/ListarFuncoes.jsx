import React, { useState } from 'react';
import {
    Plus,
    Search,
    Trash2,
    ChevronsUpDown,
    Pencil,
    ChevronLeft,
    ChevronRight,
    ChevronsLeft,
    ChevronsRight,
    MoreVertical,
    Upload,
    Copy
} from 'lucide-react';
import {Link} from "react-router-dom";

// --- Dados de Exemplo ---
const functionsData = [
    {
        nome: 'ASSISTENTE DE PRODUÇÃO',
        setor: 'SEGURANÇA DO TRABALHO',
        empresa: 'WAYSTER HENRIQUE CRUZ DE MELO',
        cbo: '000000-Não Informado',
        funcionarios: 50,
        status: 'Ativo'
    },
    {
        nome: 'TECNICO SEGURANÇA',
        setor: 'SEGURANÇA DO TRABALHO',
        empresa: 'WAYSTER HENRIQUE CRUZ DE MELO',
        cbo: '000000-Não Informado',
        funcionarios: 1,
        status: 'Ativo'
    },
    {
        nome: 'Gerente Comercial e de Projetos',
        setor: 'Comercial e Projetos',
        empresa: 'MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA',
        cbo: '000000-Não Informado',
        funcionarios: 1,
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

// Input com botões de ação
const InputWithActions = ({ placeholder, actions }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            className="w-full py-2 pl-4 pr-20 border border-gray-300 rounded-md focus:outline-none transition-colors bg-white focus:ring-2 focus:ring-blue-500"
        />
        <div className="absolute right-0 flex">
            {actions}
        </div>
    </div>
);


// --- Componente Principal ---

export default function ListarFuncoes() {
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage, setEntriesPerPage] = useState(5);

    const filteredFunctions = functionsData.filter(func =>
        func.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
        func.empresa.toLowerCase().includes(searchTerm.toLowerCase()) ||
        func.setor.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const indexOfLastEntry = currentPage * entriesPerPage;
    const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
    const currentEntries = filteredFunctions.slice(indexOfFirstEntry, indexOfLastEntry);
    const totalPages = Math.ceil(filteredFunctions.length / entriesPerPage);

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                {/* Cabeçalho e Botões de Ação */}
                <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
                    <h1 className="text-3xl font-bold text-gray-900 mb-4 sm:mb-0">Funções</h1>
                    <div className="flex flex-wrap gap-2">
                        <Link
                            to="/cadastros/funcao"
                            className="flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors"
                        >
                            <Plus size={16} />
                            <span>Nova Função</span>
                        </Link>
                    </div>
                </div>

                {/* Filtros e Tabela */}
                <div className="bg-white p-4 rounded-lg shadow-md">
                    {/* Filtros */}
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                        <div className="md:col-span-1">
                            <label className="text-sm font-medium text-gray-600">Filtrar por Empresa</label>
                            <InputWithActions
                                placeholder="Selecione uma empresa para filtrar"
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </div>
                        <div className="md:col-span-1">
                            <label className="text-sm font-medium text-gray-600">Filtrar por Setor</label>
                            <InputWithActions
                                placeholder="Selecione um setor para filtrar"
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </div>
                        <div className="md:col-span-1">
                            <label className="text-sm font-medium text-gray-600">Filtrar por Unidade Operacional</label>
                            <InputWithActions
                                placeholder="Nenhuma Unidade Operacional selecionada"
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </div>
                    </div>

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
                            <select className="w-full sm:w-auto border border-gray-300 rounded-md px-3 py-2 focus:outline-none">
                                <option value="ativos">Ativos</option>
                                <option value="inativos">Inativos</option>
                            </select>
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

                    {/* Tabela de Funções */}
                    <div className="overflow-x-auto">
                        <table className="min-w-full divide-y divide-gray-200">
                            <thead className="bg-gray-50">
                            <tr>
                                <TableHeader>Nome</TableHeader>
                                <TableHeader>Setor</TableHeader>
                                <TableHeader>Empresa</TableHeader>
                                <TableHeader>CBO</TableHeader>
                                <TableHeader>Total de Funcionários</TableHeader>
                                <TableHeader>Status</TableHeader>
                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                            </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                            {currentEntries.map((func, index) => (
                                <tr key={index} className="hover:bg-gray-50">
                                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{func.nome}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{func.setor}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{func.empresa}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{func.cbo}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{func.funcionarios}</td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm"><StatusBadge status={func.status} /></td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm">
                                        <div className="flex items-center space-x-2">
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
                            Mostrando de <span className="font-medium">{indexOfFirstEntry + 1}</span> até <span className="font-medium">{Math.min(indexOfLastEntry, filteredFunctions.length)}</span> de <span className="font-medium">{filteredFunctions.length}</span> registros
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
