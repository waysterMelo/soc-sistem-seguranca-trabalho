import React, { useState } from 'react';
import {
    Search,
    Trash2,
    Plus,
    Check,
    AlertCircle
} from 'lucide-react';

// --- Dados de Exemplo ---
const funcionariosParticipantes = [
    {
        id: 1,
        nome: 'Marina Garcia Lopes',
        empresa: 'MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA',
        funcao: 'Gerente Comercial e de Projetos',
        setor: 'Comercial e Projetos',
        concluiu: true,
        fezAnterior: false
    }
];

const profissionaisResponsaveis = [
    {
        id: 1,
        nome: 'MEDICO TESTE WAYSTER',
        detalhes: 'Oficial da Aeronáutica - RMS - 123456 - AM'
    }
]


// --- Componentes Reutilizáveis ---

const FormSection = ({ title, children, className = '' }) => (
    <div className={`bg-white p-6 rounded-lg shadow-md mb-8 ${className}`}>
        <h3 className="text-lg font-semibold text-gray-700 border-b border-gray-200 pb-4 mb-6">{title}</h3>
        {children}
    </div>
);

const FormField = ({ label, required, children, className = '', error }) => (
    <div className={`flex flex-col space-y-1.5 ${className}`}>
        <label className="text-sm font-medium text-gray-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
        {error && <p className="text-xs text-red-500 flex items-center mt-1"><AlertCircle size={14} className="mr-1"/>{error}</p>}
    </div>
);

const InputWithActions = ({ placeholder, value, actions, valid, error }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            defaultValue={value}
            className={`w-full py-2 pl-4 pr-24 border ${error ? 'border-red-500' : (valid ? 'border-green-500' : 'border-gray-300')} rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500`}
        />
        <div className="absolute right-0 flex items-center h-full">
            {error && <AlertCircle size={18} className="text-red-500 mx-2" />}
            <div className="flex border-l h-full">
                {actions}
            </div>
        </div>
    </div>
);


// --- Componente Principal ---

export default function CadastrarTreinamento() {
    const [formErrors, setFormErrors] = useState({ treinamento: "O campo Selecione o Treinamento/Capacitação é obrigatório"});

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Novo Vínculo de Treinamento</h1>
                </header>

                <form>
                    {/* Seção Informações Básicas */}
                    <FormSection title="Informações Básicas">
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-2 gap-6">
                            <FormField label="Empresa" required>
                                <InputWithActions
                                    value="MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA"
                                    valid
                                    actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-gray-500 hover:text-red-600 rounded-r-md"><Trash2 size={18}/></button></>}
                                />
                            </FormField>
                            <FormField label="Selecione o Treinamento/Capacitação" required error={formErrors.treinamento}>
                                <InputWithActions
                                    placeholder="Selecione um Treinamento..."
                                    error={formErrors.treinamento}
                                    actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-gray-500 hover:text-red-600 rounded-r-md"><Trash2 size={18}/></button></>}
                                />
                            </FormField>
                        </div>
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-4 gap-6">
                            <FormField label="Tipo de Treinamento">
                                <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white"><option>Capacitação</option></select>
                            </FormField>
                            <FormField label="Modalidade">
                                <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white"><option>Presencial</option></select>
                            </FormField>
                            <FormField label="Tipo de Capacitação">
                                <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white"><option>Inicial</option></select>
                            </FormField>
                        </div>
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-4 gap-6">
                            <FormField label="Data de Início" required>
                                <div className="relative"><input type="text" defaultValue="05/06/2025" className="w-full py-2 px-3 border border-green-500 rounded-md"/><Check size={16} className="absolute right-3 top-1/2 -translate-y-1/2 text-green-500"/></div>
                            </FormField>
                            <FormField label="Data de Término" required><input type="text" defaultValue="05/06/2025" className="w-full py-2 px-3 border border-gray-300 rounded-md"/></FormField>
                            <FormField label="Validade"><input type="text" defaultValue="05/06/2025" className="w-full py-2 px-3 border border-gray-300 rounded-md"/></FormField>
                            <FormField label="Duração (em horas)"><input type="number" defaultValue="0" className="w-full py-2 px-3 border border-gray-300 rounded-md"/></FormField>
                        </div>
                        <div className="col-span-full">
                            <FormField label="Observações Sobre o Treinamento">
                                <textarea placeholder="Informe as observações" rows="3" className="w-full p-2 border border-gray-300 rounded-md"></textarea>
                            </FormField>
                        </div>
                    </FormSection>

                    <FormSection title="Funcionários Participantes">
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-2 gap-6">
                            <FormField label="Setor"><InputWithActions value="Comercial e Projetos" actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-gray-500 hover:text-red-600 rounded-r-md"><Trash2 size={18}/></button></>} /></FormField>
                            <FormField label="Função"><InputWithActions value="Gerente Comercial e de Projetos" actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-gray-500 hover:text-red-600 rounded-r-md"><Trash2 size={18}/></button></>} /></FormField>
                        </div>
                        <div className="col-span-full">
                            <FormField label="Funcionários">
                                <InputWithActions placeholder="Procure por um funcionário" actions={<button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600 rounded-r-md"><Search size={18}/></button>} />
                            </FormField>
                        </div>
                        <div className="col-span-full mt-2 space-y-2">
                            {funcionariosParticipantes.map(func => (
                                <div key={func.id} className="grid grid-cols-1 md:grid-cols-3 items-center gap-4 p-3 bg-gray-50 rounded-md border">
                                    <div className="md:col-span-2">
                                        <p className="font-semibold text-gray-800">{func.nome}</p>
                                        <p className="text-xs text-gray-500">{func.empresa} - {func.funcao} - {func.setor}</p>
                                    </div>
                                    <div className="flex items-center justify-between gap-4">
                                        <label className="flex items-center text-sm"><input type="checkbox" defaultChecked={func.concluiu} className="h-4 w-4 rounded" /> <span className="ml-2">Concluiu treinamento</span></label>
                                        <label className="flex items-center text-sm"><input type="checkbox" defaultChecked={func.fezAnterior} className="h-4 w-4 rounded" /> <span className="ml-2">Fez o treinamento anterior</span></label>
                                        <button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={18}/></button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </FormSection>

                    <FormSection title="Profissionais Responsáveis pelo Treinamento">
                        <div className="col-span-full">
                            <InputWithActions value="MEDICO TESTE WAYSTER" actions={<><button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button></>} />
                        </div>
                        <div className="col-span-full mt-2 space-y-2">
                            {profissionaisResponsaveis.map(prof => (
                                <div key={prof.id} className="flex justify-between items-center p-3 bg-gray-50 rounded-md border">
                                    <div>
                                        <p className="font-semibold text-gray-800">{prof.nome}</p>
                                        <p className="text-xs text-gray-500">{prof.detalhes}</p>
                                    </div>
                                    <button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={18}/></button>
                                </div>
                            ))}
                        </div>
                    </FormSection>

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
        </div>
    );
}
