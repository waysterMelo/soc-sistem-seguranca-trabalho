import React, { useState } from 'react';
import {
    Plus,
    Settings,
    Printer,
    Trash2,
    Pencil,
    ChevronLeft,
    ChevronRight,
    ChevronsLeft,
    ChevronsRight,
    ChevronsUpDown,
    Search,
    ChevronDown
} from 'lucide-react';
import {Link} from "react-router-dom";

// --- Dados de Exemplo ---
const ltipData = [
    {
        empresa: 'WAYSTER HENRIQUE CRUZ DE MELO',
        dataLevantamento: '02/06/2025',
        situacao: 'O laudo irá vencer em 9 dia(s).',
        revisao: 'O Laudo ainda não foi revisado.'
    }
];

// --- Componentes Reutilizáveis ---
const TableHeader = ({ children }) => (
    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider whitespace-nowrap">
        <div className="flex items-center space-x-1 cursor-pointer hover:text-gray-700">
            <span>{children}</span>
            <ChevronsUpDown size={14} className="text-gray-400" />
        </div>
    </th>
);

const SituacaoBadge = ({ text }) => (
    <span className="inline-block px-2.5 py-0.5 text-xs font-semibold rounded-full bg-yellow-100 text-yellow-800">
        {text}
    </span>
);

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
export default function ListarLTIP() {
    const [activeTab, setActiveTab] = useState('LTIP');
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage, setEntriesPerPage] = useState(5);

    const filteredData = ltipData.filter(p =>
        p.empresa.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const indexOfLastEntry = currentPage * entriesPerPage;
    const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
    const currentEntries = filteredData.slice(indexOfFirstEntry, indexOfLastEntry);
    const totalPages = Math.ceil(filteredData.length / entriesPerPage);

    const renderContent = () => {
        if (currentEntries.length === 0) {
            let message = 'Ainda não existem LPs cadastrados.';
            if (activeTab === 'LI') message = 'Ainda não existem LIs cadastrados.';
            if (activeTab === 'LTIP' && searchTerm) message = 'Nenhum registro encontrado!';
            else if (activeTab === 'LTIP' && !searchTerm) return null; // Não mostra mensagem se a tabela tiver dados

            return (
                <div className="text-center py-12 px-6 bg-blue-50 text-blue-700 rounded-lg">
                    <p>{message}</p>
                </div>
            )
        }

        return (
            <div className="overflow-x-auto">
                <table className="min-w-full divide-y divide-gray-200">
                    <thead className="bg-gray-50">
                    <tr>
                        <TableHeader>Empresa</TableHeader>
                        <TableHeader>Data do Levantamento</TableHeader>
                        <TableHeader>Situação do documento</TableHeader>
                        <TableHeader>Revisão</TableHeader>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                    {currentEntries.map((item, index) => (
                        <tr key={index} className="hover:bg-gray-50">
                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{item.empresa}</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{item.dataLevantamento}</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm"><SituacaoBadge text={item.situacao} /></td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{item.revisao}</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm">
                                <div className="flex items-center space-x-3">
                                    <button className="text-red-600 hover:text-red-800"><Trash2 size={18} /></button>
                                    <button className="text-blue-600 hover:text-blue-800"><Pencil size={18} /></button>
                                    <button className="text-blue-600 hover:text-blue-800"><Printer size={18} /></button>
                                </div>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        );
    }


    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                {/* Cabeçalho */}
                <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
                    <h1 className="text-3xl font-bold text-gray-900 mb-4 sm:mb-0">
                        {activeTab === 'LTIP' && 'LTIP - Laudo Técnico de Insalubridade e Periculosidade'}
                        {activeTab === 'LI' && 'Laudos De Insalubridade'}
                        {activeTab === 'LP' && 'Laudos De Periculosidade'}
                    </h1>
                </div>

                {/* Filtros e Tabela */}
                <div className="bg-white p-4 rounded-lg shadow-md">
                    <div className="flex flex-wrap justify-between items-center mb-4 gap-4">
                        <div className="flex flex-wrap gap-2">
                            <div className="relative inline-flex">
                                <Link to={'/seguranca/novo-ltip'}
                                    className="flex items-center space-x-2 bg-blue-600 text-white pl-4
                                pr-2 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors shadow-sm">
                                    <Plus size={16} />
                                    <span>Novo {activeTab}</span>
                                    <span className="border-l border-blue-500 pl-2"><ChevronDown size={16}/></span>
                                </Link>
                            </div>
                        </div>
                    </div>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                        <div>
                            <label className="text-sm font-medium text-gray-600">Empresa</label>
                            <InputWithActions placeholder="Nenhuma Empresa selecionada" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                        </div>
                        <div>
                            <label className="text-sm font-medium text-gray-600">Unidade Operacional</label>
                            <InputWithActions placeholder="Nenhuma Unidade Operacional selecionada" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                        </div>
                        <div>
                            <label className="text-sm font-medium text-gray-600">Setores *</label>
                            <InputWithActions placeholder="Nenhum Setor selecionado" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                        </div>
                        <div>
                            <label className="text-sm font-medium text-gray-600">Funções *</label>
                            <InputWithActions placeholder="Nenhuma Função selecionada" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                        </div>
                    </div>

                    <div className="flex flex-col sm:flex-row justify-between items-center mb-4 gap-2">
                        <div className="flex-grow">
                            <div className="flex border-b">
                                {['LTIP', 'LI', 'LP'].map(tab => (
                                    <button
                                        key={tab}
                                        onClick={() => setActiveTab(tab)}
                                        className={`px-4 py-2 text-sm font-medium -mb-px border-b-2 ${activeTab === tab ? 'border-blue-600 text-blue-600' : 'border-transparent text-gray-500 hover:text-gray-700'}`}
                                    >{tab}</button>
                                ))}
                            </div>
                        </div>
                    </div>

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

                    {renderContent()}

                    {/* Paginação */}
                    {currentEntries.length > 0 && (
                        <div className="flex flex-col sm:flex-row justify-between items-center pt-4 border-t border-gray-200">
                            <p className="text-sm text-gray-700 mb-2 sm:mb-0">
                                Mostrando de <span className="font-medium">{indexOfFirstEntry + 1}</span> até <span className="font-medium">{Math.min(indexOfLastEntry, filteredData.length)}</span> de <span className="font-medium">{filteredData.length}</span> registros
                            </p>
                            <div className="flex items-center space-x-1">
                                <button onClick={() => setCurrentPage(1)} disabled={currentPage === 1} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronsLeft size={18} /></button>
                                <button onClick={() => setCurrentPage(prev => Math.max(prev - 1, 1))} disabled={currentPage === 1} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronLeft size={18} /></button>
                                <span className="px-4 py-2 text-sm font-medium bg-blue-600 text-white rounded-md">{currentPage}</span>
                                <button onClick={() => setCurrentPage(prev => Math.min(prev + 1, totalPages))} disabled={currentPage === totalPages} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronRight size={18} /></button>
                                <button onClick={() => setCurrentPage(totalPages)} disabled={currentPage === totalPages} className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronsRight size={18} /></button>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}
