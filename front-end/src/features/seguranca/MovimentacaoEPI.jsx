import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";

// Componente para o ícone de busca
const SearchIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5">
        <path strokeLinecap="round" strokeLinejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
    </svg>
);

// Componente para o ícone de limpar
const ClearIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-5 h-5">
        <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
    </svg>
);

// Componente para o cabeçalho
const Header = () => (
    <header className="mb-8">
        <h1 className="text-2xl font-bold text-gray-800">Movimentação de EPI</h1>
    </header>
);

// Componente para a seção de informações do profissional (Atualizado)
const InfoSection = () => (
    <section className="mb-8 p-6 border border-gray-200 rounded-lg">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">Informações do Registro Profissional</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-x-6 gap-y-4">
            <div>
                <label htmlFor="empresa" className="block text-sm font-medium text-gray-600 mb-1">Empresa</label>
                <div className="flex items-center gap-2">
                    <input type="text" id="empresa" defaultValue="MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA" className="flex-grow w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
                    <button className="p-2 text-gray-700 bg-green-500 rounded-md hover:bg-green-400 flex-shrink-0 shadow-sm border border-gray-200">
                        <SearchIcon />
                    </button>
                    <button className="p-2 text-white bg-red-500 rounded-md hover:bg-green-400 flex-shrink-0 shadow-sm">
                        <ClearIcon />
                    </button>
                </div>
            </div>
            <div>
                <label htmlFor="registro" className="block text-sm font-medium text-gray-600 mb-1">Registro Profissional</label>
                <div className="flex items-center gap-2">
                    <input type="text" id="registro" defaultValue="Marina Garcia Lopes - Comercial e Projetos - Gerente Comercial e de Projetos" className="flex-grow w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
                    <button className="p-2 text-gray-700 bg-green-500 rounded-md hover:bg-green-400 flex-shrink-0 shadow-sm border border-gray-200">
                        <SearchIcon />
                    </button>
                    <button className="p-2 text-white bg-red-500 rounded-md hover:bg-red-600 flex-shrink-0 shadow-sm">
                        <ClearIcon />
                    </button>
                </div>
            </div>
        </div>
    </section>
);

// Componente para a seção de equipamentos
const EquipmentSection = ({ onOpenModal }) => (
    <section className="mb-8 p-6 border border-gray-200 rounded-lg">
        <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
            <div>
                <h3 className="text-md font-semibold text-gray-700 mb-2">Estoque a movimentar</h3>
                <div className="flex items-center space-x-4">
                    <button className="bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium">Clínica</button>
                    <button className="bg-gray-200 text-gray-700 px-4 py-2 rounded-md text-sm font-medium">Empresa</button>
                </div>
            </div>
        </div>

        <h3 className="text-lg font-semibold text-gray-700 mb-4">Equipamentos em uso neste Registro Profissional</h3>
        <div className="overflow-x-auto">
            <table className="w-full text-sm text-left text-gray-500">
                <thead className="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" className="px-6 py-3">Nome do equipamento</th>
                    <th scope="col" className="px-6 py-3">Certificado de Avaliação</th>
                    <th scope="col" className="px-6 py-3">Quantidade em uso</th>
                    <th scope="col" className="px-6 py-3">Ações</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colSpan="4" className="px-6 py-10 text-center text-gray-500">
                        Nenhum registro encontrado!
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div className="flex justify-end mt-4 space-x-3">
            <button onClick={onOpenModal} className="btn-hover bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg shadow-md hover:bg-blue-700 flex items-center space-x-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                </svg>
                <span>Nova Retirada</span>
            </button>
            <button className="btn-hover bg-amber-500 text-white font-semibold py-2 px-4 rounded-lg shadow-md hover:bg-amber-600 flex items-center space-x-2">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6-2.292m0 0V21M12 6.042A8.967 8.967 0 0 1 18 3.75m-6 2.292V3.75m0 14.25V18m0 2.25v-2.25" />
                </svg>
                <span>Histórico de Movimentações</span>
            </button>
        </div>
    </section>
);

// Componente para a seção de termos
const TermsSection = () => (
    <section className="mb-6 p-6 border border-gray-200 rounded-lg">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">Digite ou selecione o Termo de Ciência que constará na ficha de entrega de EPI</h2>
        <div className="mb-4">
            <textarea className="w-full h-48 p-3 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500" readOnly defaultValue={`- Declaro que recebi, gratuitamente, desta empresa, os Equipamentos de Proteção Individual abaixo relacionados e por mim conferidos, novos e em perfeitas condições de uso, nos termos do Art. 166 da Consolidação das\nLeis do Trabalho e item 6.3 da NR-6 da Portaria 3.214/1978.\n\n- Declaro estar ciente que, de acordo com o art. 158 da CLT e item 6.7.1 da NR-6 da mesma Portaria, devo usar obrigatoriamente esses equipamentos durante toda a execução do trabalho, responsabilizar-me pela guarda\ne conservação, comunicar qualquer alteração que os tornem parcial ou totalmente danificados, responsabilizar-me pela sua danificação, pelo uso inadequado, ou pelo seu extravio. Estou ciente de que em\ncaso de extravio ou danificação motivada por culpa ou dolo de minha parte, estarei obrigado a reembolsar à esta empresa o seu valor correspondente.\n\n- Fico ciente que, pela não utilização do EPI em serviço, estarei sujeito às sanções disciplinares cabíveis de acordo com a legislação vigente.\n\n- Declaro, ainda, que recebi treinamento com instruções de utilização e conservação dos EPIs.`} />
        </div>
        <div className="flex items-center">
            <input id="default-term" type="checkbox" className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" />
            <label htmlFor="default-term" className="ml-2 block text-sm text-gray-900">Utilizar padrão do sistema</label>
        </div>
    </section>
);

// Componente para o Modal de "Nova Retirada"
const RetiradaModal = ({ isOpen, onClose }) => {
    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center p-4 z-50">
            <div className="bg-white rounded-xl shadow-2xl w-full max-w-4xl transform transition-all animate-fade-in-up">
                <div className="p-6 border-b border-gray-200">
                    <h2 className="text-xl font-bold text-gray-800">Equipamentos cadastrados no sistema para retirada</h2>
                </div>
                <div className="p-6">
                    {/* Filtros do Modal */}
                    <div className="flex flex-col sm:flex-row gap-4 mb-6">
                        <input type="text" placeholder="Selecione um equipamento..." className="flex-grow p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
                        <select className="p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500">
                            <option>5</option>
                            <option>10</option>
                            <option>20</option>
                        </select>
                    </div>

                    {/* Tabela de Equipamentos do Modal */}
                    <div className="overflow-x-auto">
                        <table className="w-full text-sm text-left text-gray-500">
                            <thead className="text-xs text-gray-700 uppercase bg-gray-50">
                            <tr>
                                <th scope="col" className="p-4"><input type="checkbox" className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" /></th>
                                <th scope="col" className="px-6 py-3">Nome do equipamento</th>
                                <th scope="col" className="px-6 py-3">Certificado de Avaliação</th>
                                <th scope="col" className="px-6 py-3">Quantidade em Estoque</th>
                                <th scope="col" className="px-6 py-3">Quantidade a retirar</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr className="bg-white border-b hover:bg-gray-50">
                                <td className="p-4"><input type="checkbox" defaultChecked className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" /></td>
                                <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">Protetor Auricular Teste</th>
                                <td className="px-6 py-4">50200</td>
                                <td className="px-6 py-4">0</td>
                                <td className="px-6 py-4">
                                    <div className="flex items-center space-x-2 text-red-500">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-5 h-5">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="M6 18 18 6M6 6l12 12" />
                                        </svg>
                                        <span className="font-semibold">Esgotado</span>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    {/* Paginação */}
                    <div className="flex justify-between items-center mt-6 text-sm text-gray-600">
                        <span>Mostrando de 1 até 1 de 1 registros</span>
                        <div className="inline-flex -space-x-px rounded-md shadow-sm">
                            <button className="px-3 py-2 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700" disabled>&laquo;</button>
                            <button className="px-3 py-2 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700">1</button>
                            <button className="px-3 py-2 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700">&raquo;</button>
                        </div>
                    </div>
                </div>
                {/* Rodapé do Modal */}
                <div className="flex items-center justify-end p-6 space-x-3 bg-gray-50 border-t border-gray-200 rounded-b-xl">
                    <button onClick={onClose} className="btn-hover bg-gray-500 text-white font-semibold py-2 px-4 rounded-lg shadow-md hover:bg-gray-600">Voltar</button>
                    <button className="btn-hover bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg shadow-md hover:bg-blue-700 flex items-center space-x-2">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M6.72 13.829c-.24.03-.48.062-.72.096m.72-.096a42.415 42.415 0 0 1 10.56 0m-10.56 0L6.34 18m10.94-4.171c.24.03.48.062.72.096m-.72-.096L17.66 18m0 0 .229 2.523a1.125 1.125 0 0 1-1.12 1.227H7.231c-.662 0-1.18-.568-1.12-1.227L6.34 18m11.32 0c0-1.278-.568-2.43-1.437-3.154A4.5 4.5 0 0 0 12 11.25c-1.615 0-3.058.8-4.063 2.083A4.5 4.5 0 0 0 6.34 18m11.32 0h-1.437" />
                        </svg>
                        <span>Imprimir Ficha</span>
                    </button>
                </div>
            </div>
        </div>
    );
};

// Componente para as ações do rodapé
const FooterActions = () => (
    <div className="flex justify-end mt-6">
        <Link to={'/cadastros/epi-epc'} className="btn-hover bg-amber-500 text-white font-semibold py-2 px-4 rounded-lg shadow-md hover:bg-amber-600 flex items-center space-x-2">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-5 h-5">
                <path strokeLinecap="round" strokeLinejoin="round" d="M3.75 6A2.25 2.25 0 0 1 6 3.75h2.25A2.25 2.25 0 0 1 10.5 6v2.25a2.25 2.25 0 0 1-2.25 2.25H6a2.25 2.25 0 0 1-2.25-2.25V6ZM3.75 15.75A2.25 2.25 0 0 1 6 13.5h2.25a2.25 2.25 0 0 1 2.25 2.25V18a2.25 2.25 0 0 1-2.25 2.25H6A2.25 2.25 0 0 1 3.75 18v-2.25ZM13.5 6a2.25 2.25 0 0 1 2.25-2.25H18A2.25 2.25 0 0 1 20.25 6v2.25A2.25 2.25 0 0 1 18 10.5h-2.25A2.25 2.25 0 0 1 13.5 8.25V6ZM13.5 15.75a2.25 2.25 0 0 1 2.25-2.25H18a2.25 2.25 0 0 1 2.25 2.25V18A2.25 2.25 0 0 1 18 20.25h-2.25A2.25 2.25 0 0 1 13.5 18v-2.25Z" />
            </svg>
            <span>Equipamentos</span>
        </Link>
    </div>
);

// Componente principal da aplicação
export default function App() {
    const [isModalOpen, setModalOpen] = useState(false);

    const handleOpenModal = () => setModalOpen(true);
    const handleCloseModal = () => setModalOpen(false);

    // Adiciona um efeito para fechar o modal com a tecla 'Escape'
    useEffect(() => {
        const handleKeyDown = (event) => {
            if (event.key === 'Escape') {
                handleCloseModal();
            }
        };

        if (isModalOpen) {
            document.addEventListener('keydown', handleKeyDown);
            document.body.style.overflow = 'hidden';
        }

        // Função de limpeza
        return () => {
            document.removeEventListener('keydown', handleKeyDown);
            document.body.style.overflow = 'auto';
        };
    }, [isModalOpen]); // Roda o efeito sempre que isModalOpen mudar

    return (
        <div className="p-4 sm:p-6 md:p-8 bg-gray-100 min-h-screen" style={{ fontFamily: "'Inter', sans-serif" }}>
            <div className="max-w-7xl mx-auto bg-white p-6 rounded-xl shadow-lg">
                <Header />
                <main>
                    <InfoSection />
                    <EquipmentSection onOpenModal={handleOpenModal} />
                    <TermsSection />
                    <FooterActions />
                </main>
            </div>

            <RetiradaModal isOpen={isModalOpen} onClose={handleCloseModal} />
        </div>
    );
}
