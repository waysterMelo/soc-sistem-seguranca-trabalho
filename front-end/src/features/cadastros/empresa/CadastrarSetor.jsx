import React, { useState } from 'react';
import {
    Search,
    Trash2,
    AlertCircle,
    Info
} from 'lucide-react';

// --- Componentes Reutilizáveis ---

// Wrapper para seções do formulário
const FormSection = ({ title, children }) => (
    <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <h3 className="text-xl font-semibold text-gray-800 border-b border-gray-200 pb-4 mb-6">{title}</h3>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-6 gap-y-8">
            {children}
        </div>
    </div>
);

// Wrapper para campos de formulário (label + input)
const FormField = ({ label, required, children, className = '', error }) => (
    <div className={`flex flex-col space-y-1 ${className}`}>
        <label className="text-sm font-medium text-gray-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
        {error && <p className="text-xs text-red-500 flex items-center mt-1"><AlertCircle size={14} className="mr-1"/>{error}</p>}
    </div>
);

// Input com botões de ação (como busca e exclusão)
const InputWithActions = ({ placeholder, actions }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <div className="absolute right-0 flex">
            {actions}
        </div>
    </div>
);

// --- Componente Principal ---

export default function CadastrarSetor() {
    const [nome, setNome] = useState('');
    const [empresa, setEmpresa] = useState('');
    const [errors, setErrors] = useState({});

    const handleSubmit = (e) => {
        e.preventDefault();
        const newErrors = {};
        if (!empresa) newErrors.empresa = 'O campo Selecione a Empresa ao qual o setor irá pertencer é obrigatório';
        if (!nome) newErrors.nome = 'O campo Nome é obrigatório';

        setErrors(newErrors);

        if (Object.keys(newErrors).length === 0) {
            console.log('Formulário enviado');
            // Lógica de envio
        }
    };

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Setor</h1>
                </header>

                <form onSubmit={handleSubmit}>
                    {/* Seção Informações do Setor */}
                    <FormSection title="Informações do Setor">
                        <FormField label="Selecione a Empresa ao qual o setor irá pertencer" required className="lg:col-span-4" error={errors.empresa}>
                            <InputWithActions
                                placeholder="Nenhuma Empresa selecionada"
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </FormField>
                        <FormField label="Nome" required className="lg:col-span-4" error={errors.nome}>
                            <input
                                type="text"
                                placeholder="Digite o nome do setor"
                                value={nome}
                                onChange={(e) => setNome(e.target.value)}
                                className={`w-full py-2 px-3 border rounded-md focus:outline-none focus:ring-2 transition-colors ${errors.nome ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'}`}
                            />
                        </FormField>
                        <FormField label="Descrição do Setor" className="lg:col-span-4">
                            <textarea
                                placeholder="Digite a descrição do setor"
                                rows="3"
                                className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            ></textarea>
                        </FormField>
                    </FormSection>

                    {/* Seção Informações do Local */}
                    <FormSection title="Informações do Local">
                        <FormField label="Tipo de Empresa" className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Selecione</option>
                            </select>
                        </FormField>
                        <FormField label="Tipo do Documento" required className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Selecione</option>
                            </select>
                        </FormField>
                        <FormField label="Número do Documento" required className="lg:col-span-2">
                            <input type="text" placeholder="Digite o número do documento" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Lotação Tributária" className="lg:col-span-4">
                            <input type="text" placeholder="Código existente em e-Social - Tabela de Lotações Tributárias" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                            <p className="text-xs text-gray-500 mt-1">
                                * Caso os dados deste setor sejam relacionados ao envio do evento S-2240 do eSocial, a seleção do campo Tipo de Empresa se torna obrigatória.
                            </p>
                        </FormField>
                    </FormSection>

                    {/* Seção Unidade Operacional */}
                    <FormSection title="Unidade Operacional">
                        <FormField label="Vincular Unidade Operacional" className="lg:col-span-4">
                            <InputWithActions
                                placeholder="Nenhuma Unidade Operacional selecionada"
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </FormField>
                        <div className="lg:col-span-4 bg-blue-50 border-l-4 border-blue-400 text-blue-700 p-4 rounded-r-lg">
                            <div className="flex">
                                <Info size={20} className="mr-3 flex-shrink-0" />
                                <p className="text-sm">O setor ainda não possui vínculo com nenhuma unidade operacional.</p>
                            </div>
                        </div>
                    </FormSection>

                    {/* Botões de Ação */}
                    <div className="flex flex-wrap justify-end space-x-4 mt-8">
                        <button type="button" className="bg-red-600 text-white px-6 py-2.5 rounded-md font-semibold hover:bg-red-700 transition-colors">
                            Cancelar
                        </button>
                        <button type="submit" className="bg-green-600 text-white px-6 py-2.5 rounded-md font-semibold hover:bg-green-700 transition-colors">
                            Salvar e Sair
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
