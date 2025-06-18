import React, { useState } from 'react';
import {
    Search,
    Trash2,
    ChevronDown,
    Check,
    Upload,
    Plus
} from 'lucide-react';

// --- Dados de Exemplo ---
const riscosPGRData = [
    { grupo: 1, descricao: 'Vibrações' },
    { grupo: 1, descricao: 'Ruídos' },
    { grupo: 1, descricao: 'Radiações ionizantes' },
];

const agentesNocivosData = [
    { codigo: '01.03.001', descricao: 'Benzeno e seus compostos tóxicos (exceto os abaixo especificados, que constam expressamente no Anexo IV do Decreto 3.048/1999)' }
];

const examesPCMSOData = [
    { grupo: '1', exame: 'Acuidade Visual', tipos: 'Admissional, Periódico, Demissional' },
    { grupo: '1', exame: 'Audiometria', tipos: 'Admissional, Periódico' },
];

const profissionaisData = [
    { nome: 'MEDICO TESTE WAYSTER' }
];

// --- Componentes Reutilizáveis ---

// Secção do formulário com título
const FormSection = ({ title, children }) => (
    <div className="bg-white p-6 rounded-lg shadow-md mb-6">
        {title && <h3 className="text-lg font-semibold text-gray-700 border-b border-gray-200 pb-3 mb-6">{title}</h3>}
        {children}
    </div>
);

// Campo de formulário (label + input)
const FormField = ({ label, required, children, className = '' }) => (
    <div className={`flex flex-col space-y-1 ${className}`}>
        <label className="text-sm font-medium text-gray-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
    </div>
);

// Input com botões de ação
const InputWithActions = ({ placeholder, value, actions, className='' }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            defaultValue={value}
            className={`w-full py-2 pl-4 pr-20 border border-gray-300 rounded-md focus:outline-none transition-colors bg-white focus:ring-2 focus:ring-blue-500 ${className}`}
        />
        <div className="absolute right-0 flex">
            {actions}
        </div>
    </div>
);

// Botão de Aba com estilo atualizado
const TabButton = ({ label, isActive, onClick }) => (
    <button
        type="button"
        onClick={onClick}
        className={`px-4 py-3 -mb-px text-sm font-semibold transition-colors border-b-2
            ${isActive
            ? 'border-blue-600 text-blue-600'
            : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'
        }`
        }
    >
        {label}
    </button>
);


// --- Componente Principal ---

export default function CadastrarFuncao() {
    const [activeTab, setActiveTab] = useState('riscos');

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Funções</h1>
                </header>

                <form>
                    {/* Barra de Informações da Empresa */}
                    <div className="bg-white p-4 rounded-lg shadow-md mb-6 border-l-4 border-blue-600">
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 text-sm">
                            <div><span className="font-semibold">Razão Social:</span> WAYSTER HENRIQUE CRUZ DE MELO</div>
                            <div><span className="font-semibold">Setor:</span> SEGURANÇA DO TRABALHO</div>
                            <div><span className="font-semibold">Documento:</span> 59.413.555/0001-08</div>
                        </div>
                    </div>

                    {/* Secção Informações da Função */}
                    <FormSection title="Informações da Função">
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                            <FormField label="Empresa" required>
                                <InputWithActions value="WAYSTER HENRIQUE CRUZ DE MELO" actions={
                                    <><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button>
                                        <button type="button" className="p-2.5 text-gray-500 hover:text-red-600"><Trash2 size={18}/></button></>
                                }/>
                            </FormField>
                            <FormField label="Setor" required>
                                <InputWithActions value="SEGURANÇA DO TRABALHO" actions={
                                    <><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button>
                                        <button type="button" className="p-2.5 text-gray-500 hover:text-red-600"><Trash2 size={18}/></button></>
                                }/>
                            </FormField>
                            <FormField label="CBO" className="col-span-2">
                                <InputWithActions value="0101-05 - Oficial General da Aeronáutica" actions={
                                    <><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button>
                                        <button type="button" className="p-2.5 text-gray-500 hover:text-red-600"><Trash2 size={18}/></button></>
                                }/>
                            </FormField>
                            <FormField label="Nome" required>
                                <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                    <option>Oficial General da Aeronáutica</option>
                                </select>
                            </FormField>
                            <FormField label="Quantidade de funcionários">
                                <input type="number" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                            </FormField>
                            <FormField label="Descrição da Função" className="col-span-2">
                                <div className="relative">
                                    <textarea rows="5" defaultValue="As funções deste grupo consistem em manter, cumprir e defender a constituição federal dentro da sua esfera de atribuições; observar as leis, promover as condições de segurança necessárias para o desenvolvimento e o bem-estar geral, e defender a integridade territorial e a soberania do país..." className="w-full py-2 px-3 pr-10 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
                                    <button type="button" className="absolute top-2 right-2 p-1 text-green-500 hover:bg-gray-100 rounded-full"><Check size={18}/></button>
                                </div>
                            </FormField>
                            <FormField label="GFIP">
                                <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                    <option>Não exposto - sem adicional sobre o RAT</option>
                                </select>
                            </FormField>
                        </div>
                    </FormSection>

                    {/* Abas de Riscos */}
                    <div className="bg-white rounded-lg shadow-md">
                        <div className="border-b border-gray-200">
                            <nav className="flex space-x-4 px-6">
                                <TabButton label="RISCOS TRABALHISTAS (PGR)" isActive={activeTab === 'riscos'} onClick={() => setActiveTab('riscos')} />
                                <TabButton label="Agentes Nocivos (eSocial)" isActive={activeTab === 'agentes'} onClick={() => setActiveTab('agentes')} />
                                <TabButton label="Exames (PCMSO)" isActive={activeTab === 'exames'} onClick={() => setActiveTab('exames')} />
                            </nav>
                        </div>
                        <div className="p-6">
                            {/* Botões de Ação da Aba */}
                            <div className='flex justify-end gap-2 mb-4'>
                                <button type="button" className="flex items-center space-x-2 bg-yellow-400 text-gray-800 px-4 py-2 rounded-md text-sm font-medium hover:bg-yellow-500 transition-colors">
                                    <Upload size={16} />
                                    <span>Importar</span>
                                </button>
                                <button type="button" className="flex items-center space-x-2 bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors shadow-sm">
                                    <Plus size={16} />
                                    <span>Adicionar</span>
                                </button>
                            </div>

                            {/* Conteúdo da Aba: Riscos (PGR) */}
                            {activeTab === 'riscos' && (
                                <table className="min-w-full divide-y divide-gray-200">
                                    <thead className="bg-gray-50">
                                    <tr>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Grupo</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Descrição</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                                    </tr>
                                    </thead>
                                    <tbody className="bg-white divide-y divide-gray-200">
                                    {riscosPGRData.map((risco, index) => (
                                        <tr key={index}>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{risco.grupo}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{risco.descricao}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm"><button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={18}/></button></td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            )}

                            {/* Conteúdo da Aba: Agentes Nocivos (eSocial) */}
                            {activeTab === 'agentes' && (
                                <table className="min-w-full divide-y divide-gray-200">
                                    <thead className="bg-gray-50">
                                    <tr>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Código</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Descrição</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                                    </tr>
                                    </thead>
                                    <tbody className="bg-white divide-y divide-gray-200">
                                    {agentesNocivosData.map((agente, index) => (
                                        <tr key={index}>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{agente.codigo}</td>
                                            <td className="px-6 py-4 text-sm text-gray-900">{agente.descricao}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm"><button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={18}/></button></td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            )}

                            {/* Conteúdo da Aba: Exames (PCMSO) */}
                            {activeTab === 'exames' && (
                                <table className="min-w-full divide-y divide-gray-200">
                                    <thead className="bg-gray-50">
                                    <tr>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Grupo</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Exame</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tipos de Exame</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
                                    </tr>
                                    </thead>
                                    <tbody className="bg-white divide-y divide-gray-200">
                                    {examesPCMSOData.map((exame, index) => (
                                        <tr key={index}>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{exame.grupo}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{exame.exame}</td>
                                            <td className="px-6 py-4 text-sm text-gray-500">{exame.tipos}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm"><button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={18}/></button></td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            )}
                        </div>
                    </div>

                    {/* Campos Adicionais */}
                    <div className="mt-6 space-y-6">
                        <FormField label="Atividades Insalubres">
                            <input type="text" defaultValue="PINTAR FORNO" className="w-full py-2 px-3 border border-gray-300 bg-white rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                        </FormField>
                        <FormField label="Informações Complementares Referente a Registros Ambientais">
                            <input type="text" defaultValue="NAO INFORMADO" className="w-full py-2 px-3 border border-gray-300 bg-white rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                        </FormField>
                        <FormField label="Profissionais responsáveis pelos Registros Ambientais">
                            <InputWithActions placeholder="Selecione um ou mais profissionais" actions={
                                <><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button>
                                    <button type="button" className="p-2.5 text-gray-500 hover:text-blue-600"><Plus size={18}/></button></>
                            }/>
                            <div className="mt-2 space-y-2">
                                {profissionaisData.map((prof, index) => (
                                    <div key={index} className="flex justify-between items-center p-2 bg-gray-100 rounded-md">
                                        <span className="text-sm text-gray-700">{prof.nome}</span>
                                        <button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={16}/></button>
                                    </div>
                                ))}
                            </div>
                        </FormField>
                    </div>

                    {/* Botões de Ação */}
                    <div className="flex flex-wrap justify-end gap-4 mt-8">
                        <button type="button" className="bg-red-600 text-white px-6 py-2.5 rounded-md font-semibold hover:bg-red-700 transition-colors">
                            Cancelar
                        </button>
                        <button type="submit" className="bg-green-600 text-white px-6 py-2.5 rounded-md font-semibold hover:bg-green-700 transition-colors">
                            Salvar e Sair
                        </button>
                        <button type="submit" className="bg-green-600 text-white px-6 py-2.5 rounded-md font-semibold hover:bg-green-700 transition-colors">
                            Salvar e Continuar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
