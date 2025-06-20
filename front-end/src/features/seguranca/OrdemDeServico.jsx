
// --- ÍCONES ---
// Reutilizando ícones definidos anteriormente e adicionando novos.

import {Link} from "react-router-dom";

const SearchIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5">
        <path strokeLinecap="round" strokeLinejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
    </svg>
);

const ClearIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-5 h-5">
        <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
    </svg>
);

const SortIcon = () => (
    <svg className="w-3 h-3 ml-1.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 24 24">
        <path d="M8.574 11.024h6.852a2.075 2.075 0 0 0 1.847-1.086 1.9 1.9 0 0 0-.11-1.986L13.736 2.9a2.122 2.122 0 0 0-3.472 0L6.837 7.952a1.9 1.9 0 0 0-.11 1.986 2.074 2.074 0 0 0 1.847 1.086Zm6.852 1.952H8.574a2.075 2.075 0 0 0-1.847 1.087 1.9 1.9 0 0 0 .11 1.985l3.426 5.05a2.122 2.122 0 0 0 3.472 0l3.427-5.05a1.9 1.9 0 0 0 .11-1.985 2.074 2.074 0 0 0-1.847-1.087Z"/>
    </svg>
);

const OrdemServicoHeader = () => (
    <header className="flex justify-between items-center mb-8">
        <h1 className="text-2xl font-bold text-gray-800">Ordem de Serviço de Segurança e Saúde no Trabalho</h1>
        <Link to={'/seguranca/nova-ordem'} className="btn-hover bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg shadow-md hover:bg-blue-700 flex items-center space-x-2">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-5 h-5">
                <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
            </svg>
            <span>Nova Ordem de Serviço</span>
        </Link>
    </header>
);


const OrdemServicoFilters = () => (
    <div className="mb-6 space-y-4">
        {/* Filtro por Registros Profissionais */}
        <div>
            <label className="block text-sm font-medium text-gray-600 mb-1">Filtrar por Registros Profissionais</label>
            <div className="flex items-center gap-2">
                <input
                    type="text"
                    placeholder="Selecione um registro profissional para filtrar"
                    className="flex-grow w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
                <button className="p-2 text-white bg-green-500 rounded-md hover:bg-green-600 flex-shrink-0 shadow-sm">
                    <SearchIcon />
                </button>
                <button className="p-2 text-white bg-red-500 rounded-md hover:bg-red-600 flex-shrink-0 shadow-sm">
                    <ClearIcon />
                </button>
            </div>
        </div>

        {/* Filtro de procura geral */}
        <div className="flex items-center gap-4">
            <input
                type="text"
                placeholder="Procure por algum registro..."
                className="flex-grow w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
            <select className="p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500 bg-white">
                <option>5</option>
                <option>10</option>
                <option>25</option>
                <option>50</option>
            </select>
        </div>
    </div>
);

const ServiceOrderTable = () => (
    <div className="overflow-x-auto">
        <table className="w-full text-sm text-left text-gray-700">
            <thead className="text-xs text-gray-800 uppercase bg-gray-100 border-b">
            <tr>
                {['Funcionário', 'Função', 'Data de Elaboração', 'Revisão', 'Ações'].map((header, index) => (
                    <th key={header} scope="col" className={`px-6 py-3 ${index === 4 ? 'text-right' : ''}`}>
                        <div className="flex items-center">
                            {header}
                            {index < 4 && <SortIcon />}
                        </div>
                    </th>
                ))}
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colSpan="5" className="px-6 py-10 text-center text-gray-500 bg-white">
                    Nenhum registro encontrado!
                </td>
            </tr>
            </tbody>
        </table>
    </div>
);


function ListarOrdemDeServico() {
    return (
        <div className="p-4 sm:p-6 md:p-8 bg-white min-h-screen">
            <div className="max-w-full mx-auto">
                <OrdemServicoHeader />
                <main>
                    <OrdemServicoFilters />
                    <ServiceOrderTable />
                </main>
            </div>
        </div>
    );
}

// Componente App para renderização
export default function ListarOrdemServico() {
    return (
        <div style={{ fontFamily: "'Inter', sans-serif" }}>
            <ListarOrdemDeServico />
        </div>
    );
}
