import React, { useState } from 'react';
import {
    Search,
    Trash2,
    Plus,
    Copy,
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
    Image as ImageIcon
} from 'lucide-react';

// --- Componentes Reutilizáveis ---

// Botão de Aba
const TabButton = ({ label, isActive, onClick }) => (
    <button
        type="button"
        onClick={onClick}
        className={`px-4 py-3 -mb-px text-sm font-semibold whitespace-nowrap transition-colors border-b-2
            ${isActive
            ? 'border-blue-600 text-blue-600'
            : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'
        }`
        }
    >
        {label}
    </button>
);

// Input com botões de ação
const InputWithActions = ({ placeholder, value, actions }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            defaultValue={value}
            className="w-full py-2 pl-4 pr-20 border border-gray-300 rounded-md focus:outline-none transition-colors bg-white focus:ring-2 focus:ring-blue-500"
        />
        <div className="absolute right-0 flex">
            {actions}
        </div>
    </div>
);

// Simulação de um Editor de Texto Rico
const RichTextEditor = ({ initialContent = '' }) => (
    <div className="border border-gray-300 rounded-lg">
        <div className="flex flex-wrap items-center p-2 border-b border-gray-200 bg-gray-50 rounded-t-lg">
            <select className="text-sm border-gray-300 rounded-md mr-2">
                <option>Normal</option>
                <option>Heading 1</option>
            </select>
            <div className="flex items-center space-x-1">
                {[Bold, Italic, Underline, Strikethrough, AlignLeft, AlignCenter, AlignRight, List, Link2, ImageIcon].map((Icon, index) => (
                    <button key={index} type="button" className="p-2 text-gray-600 rounded-md hover:bg-gray-200">
                        <Icon size={16}/>
                    </button>
                ))}
            </div>
            <div className="flex items-center ml-auto space-x-1">
                <button type="button" className="p-2 text-blue-600 rounded-md hover:bg-gray-200"><Paperclip size={16}/></button>
                <button type="button" className="p-2 text-green-600 rounded-md hover:bg-gray-200"><RefreshCw size={16}/></button>
            </div>
        </div>
        <textarea
            defaultValue={initialContent}
            rows="8"
            className="w-full p-4 focus:outline-none rounded-b-lg"
        ></textarea>
    </div>
);


// --- Componentes para o Conteúdo de Cada Aba ---

const TabCapa = () => (
    <div className="space-y-6">
        <p className="text-sm text-gray-600">Personalize a capa do seu documento preenchendo o campo abaixo</p>
        <RichTextEditor />

    </div>
);

const TabTermoValidacao = () => (
    <div className="space-y-6">
        <p className="text-sm text-gray-600">Personalize o termo de validação do seu documento preenchendo o campo abaixo</p>
        <RichTextEditor initialContent="Os profissionais abaixo assinados reconhecem o teor de todas as páginas contidas neste documento por meio de sua assinatura de próprio punho e/ou digitalizada."/>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
                <label className="text-sm font-medium text-gray-600">Engenheiro Responsável</label>
                <InputWithActions placeholder="Nenhum engenheiro selecionado" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
            </div>
            <div>
                <label className="text-sm font-medium text-gray-600">Técnico Responsável</label>
                <div className="flex gap-2">
                    <InputWithActions placeholder="Nenhum técnico selecionado" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                    <button type="button" className="flex-shrink-0 flex items-center gap-2 text-sm bg-blue-600 text-white px-3 py-2 rounded-md hover:bg-blue-700 whitespace-nowrap"><Plus size={16}/> Novo Prestador de Serviço</button>
                </div>
            </div>
        </div>
    </div>
);

const TabDocumentoBase = () => (
    <div className="space-y-6">
        <p className="text-sm text-gray-600">Personalize o documento base preenchendo o campo abaixo</p>
        <RichTextEditor initialContent="INTRODUÇÃO



O PGR - PROGRAMA DE GESTÃO DE RISCOS -, está regulamentado pela NR01 (PORTARIA 6.730 de 9 DE MARÇO DE 2020). Tem por objetivo estabelecer as disposições gerais, o campo de aplicação, os termos e as definições comuns às Normas Regulamentadoras - NR - relativas à segurança e saúde no trabalho; às diretrizes e aos requisitos para o gerenciamento de riscos ocupacionais e às medidas de prevenção em Segurança e Saúde no Trabalho - SST.



ABRANGÊNCIA



Este programa abrange as instalações, os processos de trabalho e as respectivas atividades e operações desenvolvidas na empresa, e, conforme NR-01, devem ser considerados os seguintes riscos: Físico, Químico, Biológico, Ergonômico, Acidental."/>
    </div>
);

const TabDadosEmpresa = () => (
    <div className="space-y-6">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            <div>
                <label className="text-sm font-medium text-gray-600">Nome do Responsável da Empresa</label>
                <input type="text" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/>
            </div>
            <div>
                <label className="text-sm font-medium text-gray-600">Data do Documento *</label>
                <input type="text" placeholder="dd/mm/aaaa" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/>
            </div>
            <div>
                <label className="text-sm font-medium text-gray-600">Data de Revisão</label>
                <input type="text" placeholder="dd/mm/aaaa" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md bg-gray-100" disabled/>
            </div>
        </div>
        <p className="text-sm text-gray-600">Personalize o termo de ciência do responsável preenchendo o campo abaixo</p>
        <RichTextEditor initialContent="O Responsável pela Empresa declara ter ciência do conteúdo integral do presente documento e se compromete em prover todos os recursos necessários para que o PGR atinja seus objetivos em prol da segurança e saúde dos seus trabalhadores."/>
    </div>
);

const TabConsideracoesFinais = () => (
    <div className="space-y-6">
        <p className="text-sm text-gray-600">Personalize a conclusão preenchendo o campo abaixo</p>
        <RichTextEditor />
    </div>
);


// --- Componente Principal ---
export default function CadastrarPGR() {
    const [activeTab, setActiveTab] = useState('capa');

    const tabs = [
        { id: 'capa', label: 'Capa' },
        { id: 'termo', label: 'Termo de Validação' },
        { id: 'base', label: 'Documento Base' },
        { id: 'dados', label: 'Dados da Empresa' },
        { id: 'inventario', label: 'Inventário de Riscos Ocupacionais' },
        { id: 'mapa', label: 'Mapa de Risco' },
        { id: 'planejamento', label: 'Planejamento Anual' },
        { id: 'plano', label: 'Plano de Ação' },
        { id: 'finais', label: 'Considerações Finais' },
    ];

    const renderTabContent = () => {
        switch (activeTab) {
            case 'capa': return <TabCapa />;
            case 'termo': return <TabTermoValidacao />;
            case 'base': return <TabDocumentoBase />;
            case 'dados': return <TabDadosEmpresa />;
            case 'inventario': return <TabInventarioRiscos />;
            case 'plano': return <TabPlanoAcao />;
            case 'finais': return <TabConsideracoesFinais />;
            default: return <div className="p-6 text-center text-gray-500">Conteúdo para {activeTab}</div>;
        }
    }

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar PGR</h1>
                </header>

                <form>
                    <div className="bg-white p-6 rounded-lg shadow-md mb-8">
                        <label className="text-sm font-medium text-gray-600">Empresas que não possuem PGR *</label>
                        <div className="flex items-center gap-4">
                            <div className="flex-grow">
                                <InputWithActions
                                    value="WAYSTER HENRIQUE CRUZ DE MELO"
                                    actions={
                                        <>
                                            <button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button>
                                            <button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button>
                                        </>
                                    }
                                />
                            </div>
                        </div>
                    </div>

                    <div className="bg-white rounded-lg shadow-md">
                        <div className="border-b border-gray-200 overflow-x-auto">
                            <nav className="flex space-x-2 px-4">
                                {tabs.map(tab => (
                                    <TabButton key={tab.id} label={tab.label} isActive={activeTab === tab.id} onClick={() => setActiveTab(tab.id)} />
                                ))}
                            </nav>
                        </div>
                        <div className="p-6">
                            {renderTabContent()}
                        </div>
                    </div>

                    {/* Botões de Ação */}
                    <div className="flex flex-wrap justify-end gap-4 mt-8">
                        <button type="button" className="bg-red-600 text-white px-8 py-2.5 rounded-md font-semibold hover:bg-red-700 transition-colors">
                            Cancelar
                        </button>
                        <button type="submit" className="bg-green-600 text-white px-8 py-2.5 rounded-md font-semibold hover:bg-green-700 transition-colors">
                            Salvar
                        </button>
                    </div>
                </form>
            </div>
            <div className="p-4 border border-blue-500 rounded-lg bg-blue-50">
                <h4 className="font-semibold text-blue-800 mb-4">Riscos Trabalhistas</h4>
                <p className="text-sm text-gray-600 mb-4">Para cadastrar ou visualizar os riscos, escolha o setor e a função.</p>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <InputWithActions placeholder="Selecione um setor..." actions={<button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600 rounded-r-md"><Search size={18}/></button>} />
                    <InputWithActions placeholder="Selecione uma função..." actions={<button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600 rounded-r-md"><Search size={18}/></button>} />
                </div>
                <div className="flex flex-wrap gap-2 mt-4">
                    <button type="button" className="flex items-center gap-2 text-sm bg-yellow-400 text-gray-800 px-3 py-2 rounded-md hover:bg-yellow-500"><Copy size={16}/> Copiar Riscos</button>
                    <button type="button" className="flex items-center gap-2 text-sm bg-yellow-400 text-gray-800 px-3 py-2 rounded-md hover:bg-yellow-500"><Plus size={16}/> Riscos Personalizados</button>
                    <button type="button" className="flex items-center gap-2 text-sm bg-blue-600 text-white px-3 py-2 rounded-md hover:bg-blue-700"><Plus size={16}/> Adicionar Riscos</button>
                </div>
            </div>
        </div>

    );
}
