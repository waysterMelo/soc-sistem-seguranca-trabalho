import React, {useState} from 'react';
import {
    Ban,
    Check,
    ChevronLeft,
    ChevronRight,
    ChevronsLeft,
    ChevronsRight,
    ChevronsUpDown,
    Plus,
    X
} from 'lucide-react';

// --- Dados de Exemplo ---
const aparelhosData = [
    {
        descricao: 'Aparelho teste',
        modelo: 'cd222222',
        calibracao: '07/06/2025',
        status: 'Ativo'
    }
];


// --- Sub-componente Modal ---
const AdicionarAparelhoModal = ({ isOpen, onClose }) => {
    if (!isOpen) return null;

    return (
        <div
            className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50"
            onClick={onClose} // Fecha ao clicar no fundo
        >
            <div
                className="bg-white rounded-lg shadow-xl w-full max-w-3xl m-4"
                onClick={e => e.stopPropagation()} // Impede que o clique no modal feche-o
            >
                {/* Cabeçalho do Modal */}
                <div className="flex justify-between items-center p-4 border-b">
                    <h2 className="text-xl font-semibold text-gray-800">Adicionar Aparelho</h2>
                    <button onClick={onClose} className="text-gray-500 hover:text-gray-800">
                        <X size={24} />
                    </button>
                </div>

                {/* Corpo do Modal (Formulário) */}
                <div className="p-6">
                    <form>
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Descrição *</label>
                                <input type="text" placeholder="Digite a descrição" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Modelo *</label>
                                <input type="text" placeholder="Digite o modelo" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Marca *</label>
                                <input type="text" placeholder="Digite a marca" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Calibração</label>
                                <input type="text" placeholder="Digite a calibração" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Data da Calibração *</label>
                                <input type="text" defaultValue="16/06/2025" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Validade da Calibração</label>
                                <input type="text" defaultValue="16/06/2025" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                            </div>
                            <div className="md:col-span-3">
                                <label className="block text-sm font-medium text-gray-700">Critérios de avaliação</label>
                                <textarea placeholder="Digite os critérios" rows="3" className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"></textarea>
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Status do aparelho</label>
                                <select className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 bg-white">
                                    <option>Ativo</option>
                                    <option>Inativo</option>
                                </select>
                            </div>
                            <div className="md:col-span-2">
                                <label className="block text-sm font-medium text-gray-700">Upload do Certificado de Calibragem dos Aparelhos</label>
                                <div className="mt-1 flex rounded-md shadow-sm">
                                    <div className="relative flex-grow focus-within:z-10">
                                        <span className='absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none text-gray-500 text-sm'>Selecione o arquivo para upload</span>
                                        <input type="file" className="opacity-0 w-full h-full" />
                                    </div>
                                    <button type="button" className="-ml-px relative inline-flex items-center space-x-2 px-4 py-2 border border-gray-300 text-sm font-medium rounded-r-md text-gray-700 bg-gray-50 hover:bg-gray-100">
                                        Browse
                                    </button>
                                </div>
                            </div>
                        </div>

                        {/* Rodapé do Modal */}
                        <div className="flex justify-end gap-4 pt-4 border-t mt-4">
                            <button type="button" onClick={onClose} className="bg-white border border-gray-300 text-gray-700 px-4 py-2 rounded-md text-sm font-medium hover:bg-gray-50">
                                Cancelar
                            </button>
                            <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-green-700 shadow-sm">
                                Salvar Aparelho
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

// --- Componentes Reutilizáveis (já existentes) ---

const TableHeader = ({ children }) => (
    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider whitespace-nowrap">
        <div className="flex items-center space-x-1 cursor-pointer hover:text-gray-700">
            <span>{children}</span>
            <ChevronsUpDown size={14} className="text-gray-400" />
        </div>
    </th>
);

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

export default function ListarAparelhos() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage, setEntriesPerPage] = useState(5);

    const filteredData = aparelhosData.filter(p =>
        p.descricao.toLowerCase().includes(searchTerm.toLowerCase()) ||
        p.modelo.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const indexOfLastEntry = currentPage * entriesPerPage;
    const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
    const currentEntries = filteredData.slice(indexOfFirstEntry, indexOfLastEntry);
    const totalPages = Math.ceil(filteredData.length / entriesPerPage);

    return (
        <>
            <AdicionarAparelhoModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} />

            <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
                <div className="container mx-auto">
                    {/* Cabeçalho e Botões de Ação */}
                    <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
                        <h1 className="text-3xl font-bold text-gray-900 mb-4 sm:mb-0">Aparelho</h1>
                        <div className="flex flex-wrap gap-2">
                            <button
                                onClick={() => setIsModalOpen(true)}
                                className="flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors shadow-sm">
                                <Plus size={16} />
                                <span>Adicionar Aparelho</span>
                            </button>
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

                        {/* Tabela de Aparelhos */}
                        <div className="overflow-x-auto">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                <tr>
                                    <TableHeader>Descrição</TableHeader>
                                    <TableHeader>Modelo</TableHeader>
                                    <TableHeader>Data da Calibração</TableHeader>
                                    <TableHeader>Status</TableHeader>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                                </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                {currentEntries.map((aparelho, index) => (
                                    <tr key={index} className="hover:bg-gray-50">
                                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{aparelho.descricao}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{aparelho.modelo}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{aparelho.calibracao}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm"><StatusBadge status={aparelho.status} /></td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm">
                                            <div className="flex items-center space-x-3">
                                                <button className="text-red-600 hover:text-red-800"><Ban size={18} /></button>
                                                <button className="text-green-600 hover:text-green-800"><Check size={18} /></button>
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
        </>
    );
}
