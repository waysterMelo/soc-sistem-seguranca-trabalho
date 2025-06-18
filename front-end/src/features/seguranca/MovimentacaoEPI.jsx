import React, { useState } from 'react';

import {

    Search,

    Trash2,

    Plus,

    Clock,

    Printer,

    RefreshCw,

    Paperclip,

    Bold,

    Italic,

    Underline,

    Strikethrough,

    AlignLeft,

    AlignCenter,

    AlignRight,

    List,

    Link2,

    Image as ImageIcon,

    ChevronLeft,

    ChevronRight,

    ChevronsLeft,

    ChevronsRight,

    X

} from 'lucide-react';



// --- Dados de Exemplo para o Modal ---

const equipamentosDisponiveis = [

    { id: 1, nome: 'Protetor Auricular Teste', ca: '50200', estoque: 0 },

    { id: 2, nome: 'Capacete de Segurança', ca: '12345', estoque: 15 },

    { id: 3, nome: 'Luva de Proteção', ca: '67890', estoque: 30 }

];





// --- Componentes Reutilizáveis ---



const FormField = ({ label, children, className = '' }) => (

    <div className={className}>

        <label className="text-sm font-medium text-gray-600">{label}</label>

        <div className="mt-1">{children}</div>

    </div>

);



const FormSection = ({ title, children, className = '' }) => (

    <div className={`bg-white p-6 rounded-lg shadow-md mb-6 ${className}`}>

        {title && <h3 className="text-lg font-semibold text-gray-700 border-b border-gray-200 pb-4 mb-6">{title}</h3>}

        {children}

    </div>

);



const InputWithActions = ({ placeholder, value, actions }) => (

    <div className="relative flex items-center">

        <input

            type="text"

            placeholder={placeholder}

            defaultValue={value}

            className="w-full py-2 pl-4 pr-20 border border-gray-300 rounded-md focus:outline-none transition-colors bg-white focus:ring-2 focus:ring-blue-500"

        />

        <div className="absolute right-0 flex h-full">

            <div className="flex border-l h-full">

                {actions}

            </div>

        </div>

    </div>

);



const RichTextEditor = ({ initialContent, rows = 8 }) => (

    <div className="border border-gray-300 rounded-lg">

        <div className="flex flex-wrap items-center p-2 border-b border-gray-200 bg-gray-50 rounded-t-lg">

            <select className="text-sm border-gray-300 rounded-md mr-2"><option>Normal</option></select>

            <div className="flex items-center space-x-1">

                {[Bold, Italic, Underline, Strikethrough, AlignLeft, AlignCenter, AlignRight, List, Link2, ImageIcon].map((Icon, index) => (

                    <button key={index} type="button" className="p-2 text-gray-600 rounded-md hover:bg-gray-200"><Icon size={16}/></button>

                ))}

            </div>

            <div className="flex items-center ml-auto space-x-1">

                <button type="button" className="p-2 text-blue-600 rounded-md hover:bg-gray-200"><Paperclip size={16}/></button>

                <button type="button" className="p-2 text-green-600 rounded-md hover:bg-gray-200"><RefreshCw size={16}/></button>

            </div>

        </div>

        <textarea defaultValue={initialContent} rows={rows} className="w-full p-4 focus:outline-none rounded-b-lg"></textarea>

    </div>

);





// --- Sub-componente Modal para "Nova Retirada" ---



const NovaRetiradaModal = ({ isOpen, onClose }) => {

    const [retiradaData, setRetiradaData] = useState({});



    const handleQuantidadeChange = (id, value) => {

        const item = equipamentosDisponiveis.find(e => e.id === id);

        const quantidade = Math.min(Number(value), item.estoque); // Limita ao estoque

        setRetiradaData(prev => ({ ...prev, [id]: { ...prev[id], quantidade } }));

    };



    const handleCheckboxChange = (id, checked) => {

        setRetiradaData(prev => ({ ...prev, [id]: { ...prev[id], selecionado: checked } }));

    };



    const isAnySelected = Object.values(retiradaData).some(item => item.selecionado);





    if (!isOpen) return null;



    return (

        <div

            className="fixed inset-0 bg-black bg-opacity-60 flex justify-center items-center z-50 p-4"

            onClick={onClose}

        >

            <div

                className="bg-gray-50 rounded-lg shadow-xl w-full max-w-4xl"

                onClick={e => e.stopPropagation()}

            >

                <div className="p-6">

                    <h2 className="text-2xl font-bold text-gray-800 mb-6">Equipamentos cadastrados no sistema para retirada</h2>

                    <div className="flex flex-col sm:flex-row justify-between items-center mb-4 gap-2">

                        <input

                            type="text"

                            placeholder="Selecione um equipamento..."

                            className="w-full sm:flex-grow pl-4 pr-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"

                        />

                        <select className="w-full sm:w-auto border border-gray-300 rounded-md px-3 py-2 focus:outline-none">

                            <option value="5">5</option>

                            <option value="10">10</option>

                        </select>

                    </div>



                    <div className="overflow-x-auto border rounded-lg">

                        <table className="min-w-full divide-y divide-gray-200">

                            <thead className="bg-gray-100">

                            <tr>

                                <th className="p-4"><input type="checkbox" className="h-4 w-4 rounded" /></th>

                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Nome do equipamento</th>

                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Certificado de Avaliação</th>

                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Quantidade em Estoque</th>

                                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Quantidade a retirar</th>

                            </tr>

                            </thead>

                            <tbody className="bg-white divide-y divide-gray-200">

                            {equipamentosDisponiveis.map(item => {

                                const isEsgotado = item.estoque === 0;

                                const itemData = retiradaData[item.id] || {};



                                return (

                                    <tr key={item.id} className={isEsgotado ? 'bg-gray-100' : ''}>

                                        <td className="p-4">

                                            <input

                                                type="checkbox"

                                                className="h-4 w-4 rounded"

                                                disabled={isEsgotado}

                                                checked={itemData.selecionado || false}

                                                onChange={(e) => handleCheckboxChange(item.id, e.target.checked)}

                                            />

                                        </td>

                                        <td className="px-6 py-4 font-medium">{item.nome}</td>

                                        <td className="px-6 py-4">{item.ca}</td>

                                        <td className="px-6 py-4">{item.estoque}</td>

                                        <td className="px-6 py-4">

                                            {isEsgotado ? (

                                                <span className="flex items-center text-red-500 font-semibold"><X size={16} className="mr-1"/> Esgotado</span>

                                            ) : (

                                                <input

                                                    type="number"

                                                    className="w-24 border-gray-300 rounded-md p-1"

                                                    value={itemData.quantidade || ''}

                                                    onChange={(e) => handleQuantidadeChange(item.id, e.target.value)}

                                                    max={item.estoque}

                                                    min="0"

                                                />

                                            )}

                                        </td>

                                    </tr>

                                )

                            })}

                            </tbody>

                        </table>

                    </div>

                    <div className="flex justify-between items-center pt-4">

                        <p className="text-sm text-gray-700">Mostrando de 1 até {equipamentosDisponiveis.length} de {equipamentosDisponiveis.length} registros</p>

                        {/* Paginação do Modal */}

                        <div className="flex items-center space-x-1">

                            <button disabled className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronsLeft size={18} /></button>

                            <button disabled className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronLeft size={18} /></button>

                            <span className="px-4 py-2 text-sm font-medium bg-blue-600 text-white rounded-md">1</span>

                            <button disabled className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronRight size={18} /></button>

                            <button disabled className="p-2 rounded-md hover:bg-gray-100 disabled:opacity-50"><ChevronsRight size={18} /></button>

                        </div>

                    </div>

                </div>



                <div className="flex justify-between items-center gap-4 p-4 bg-gray-100 border-t rounded-b-lg">

                    <div>

                        {!isAnySelected && <p className="text-sm text-red-600">Nenhum equipamento selecionado para retirada.</p>}

                    </div>

                    <div className="flex gap-4">

                        <button type="button" onClick={onClose} className="bg-white border border-gray-300 text-gray-700 px-6 py-2 rounded-md font-semibold hover:bg-gray-100">

                            Voltar

                        </button>

                        <button type="button" disabled={!isAnySelected} className="flex items-center gap-2 bg-blue-600 text-white px-6 py-2 rounded-md font-semibold hover:bg-blue-700 disabled:bg-blue-400 disabled:cursor-not-allowed">

                            <Printer size={18}/> Imprimir Ficha

                        </button>

                    </div>

                </div>

            </div>

        </div>

    );

};





// --- Componente Principal ---



export default function MovimentacaoEPI() {

    const [isModalOpen, setIsModalOpen] = useState(false);



    return (

        <>

            <NovaRetiradaModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} />



            <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">

                <div className="container mx-auto">

                    <header className="mb-6">

                        <h1 className="text-3xl font-bold text-gray-900">Movimentação de EPI</h1>

                    </header>



                    <form>

                        <FormSection title="Informações do Registro Profissional">

                            <div className="col-span-full grid grid-cols-1 md:grid-cols-2 gap-6">

                                <FormField label="Empresa">

                                    <InputWithActions value="MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA" actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-gray-500 hover:text-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />

                                </FormField>

                                <FormField label="Registro Profissional">

                                    <InputWithActions value="Marina Garcia Lopes - Comercial e Projetos - Gerente Comercial e de Projetos" actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-gray-500 hover:text-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />

                                </FormField>

                            </div>

                            <div className="col-span-full mt-4">

                                <label className="text-sm font-medium text-gray-600">Estoque a movimentar</label>

                                <div className="flex gap-2 mt-1">

                                    <button type="button" className="px-4 py-2 text-sm bg-blue-600 text-white rounded-md">Clínica</button>

                                    <button type="button" className="px-4 py-2 text-sm bg-white border rounded-md">Empresa</button>

                                </div>

                            </div>

                        </FormSection>



                        <FormSection title="Equipamentos em uso neste Registro Profissional">

                            <div className="col-span-full flex flex-col sm:flex-row justify-between items-center gap-2">

                                <input type="text" placeholder="Selecione um equipamento em uso..." className="w-full sm:flex-grow pl-4 pr-4 py-2 border border-gray-300 rounded-md"/>

                                <select className="w-full sm:w-auto border border-gray-300 rounded-md px-3 py-2">

                                    <option value="5">5</option>

                                </select>

                            </div>

                            <div className="col-span-full overflow-x-auto border rounded-lg">

                                <table className="min-w-full">

                                    <thead className="bg-gray-50">

                                    <tr>

                                        <th className="p-4"><input type="checkbox" className="h-4 w-4 rounded"/></th>

                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Nome do equipamento</th>

                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Certificado de Avaliação</th>

                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Quantidade em uso</th>

                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Ações</th>

                                    </tr>

                                    </thead>

                                    <tbody>

                                    <tr>

                                        <td colSpan="5" className="text-center py-10 text-gray-500">Nenhum registro encontrado!</td>

                                    </tr>

                                    </tbody>

                                </table>

                            </div>

                            <div className="col-span-full flex justify-end gap-2">

                                <button type="button" onClick={() => setIsModalOpen(true)} className="flex items-center gap-2 text-sm bg-blue-600 text-white px-3 py-2 rounded-md hover:bg-blue-700"><Plus size={16}/> Nova Retirada</button>

                                <button type="button" className="flex items-center gap-2 text-sm bg-yellow-400 text-gray-800 px-3 py-2 rounded-md hover:bg-yellow-500"><Clock size={16}/> Histórico de Movimentações</button>

                            </div>

                        </FormSection>



                        <FormSection title="Digite ou selecione o Termo de Ciência que constará na ficha de entrega de EPI">

                            <div className="col-span-full">

                                <RichTextEditor initialContent={`- Declaro que recebi, gratuitamente, desta empresa, os Equipamentos de Proteção Individual abaixo relacionados e por mim conferidos, novos e em perfeitas condições de uso, nos termos do Art. 166 da Consolidação das Leis do Trabalho e item 6.3 da NR-6 da Portaria 3.214/1978.\n\n- Declaro estar ciente que, de acordo com o art. 158 da CLT e item 6.7.1 da NR-6 da mesma Portaria, devo usar obrigatoriamente esses equipamentos durante toda a execução do trabalho, responsabilizar-me pela guarda e conservação, comunicar qualquer alteração que os tornem parcial ou totalmente danificados, responsabilizar-me pela sua danificação, pelo uso inadequado, ou pelo seu extravio. Estou ciente de que em caso de extravio ou danificação motivada por culpa ou dolo de minha parte, estarei obrigado a reembolsar à esta empresa o seu valor correspondente.\n\n- Fico ciente que, pela não utilização do EPI em serviço, estarei sujeito às sanções disciplinares cabíveis de acordo com a legislação vigente.\n\n- Declaro, ainda, que recebi treinamento com instruções de utilização e conservação dos EPIs.`} />

                                <label className="flex items-center mt-4"><input type="checkbox" className="h-4 w-4 rounded"/> <span className="ml-2 text-sm">Utilizar padrão do sistema</span></label>

                            </div>

                        </FormSection>



                        <div className="flex justify-end">

                            <button type="button" className="flex items-center gap-2 text-sm bg-yellow-400 text-gray-800 px-4 py-2 rounded-md hover:bg-yellow-500">Equipamentos</button>

                        </div>

                    </form>

                </div>

            </div>

        </>

    );

}