import React, { useState } from 'react';
import {
    Search,
    Trash2,
    Info
} from 'lucide-react';

// --- Componentes Reutilizáveis ---

// Wrapper para seções do formulário
const FormSection = ({ title, children, className = '' }) => (
    <div className={`bg-white p-6 rounded-lg shadow-md mb-8 ${className}`}>
        {title && <h3 className="text-xl font-semibold text-gray-800 border-b border-gray-200 pb-4 mb-6">{title}</h3>}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            {children}
        </div>
    </div>
);

// Wrapper para campos de formulário (label + input)
const FormField = ({ label, required, children, className = '' }) => (
    <div className={`flex flex-col space-y-1 ${className}`}>
        <label className="text-sm font-medium text-gray-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
    </div>
);

// Input com botões de ação (como busca e exclusão)
const InputWithActions = ({ placeholder, disabled = false, actions }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            disabled={disabled}
            className={`w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none transition-colors ${
                disabled ? 'bg-gray-100 cursor-not-allowed' : 'focus:ring-2 focus:ring-blue-500'
            }`}
        />
        <div className="absolute right-0 flex">
            {actions}
        </div>
    </div>
);

// --- Componente Principal ---

export default function CadastrarUnidade() {
    const [useCompanyAddress, setUseCompanyAddress] = useState(false);

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Unidade Operacional</h1>
                </header>

                <form>
                    {/* Seção Informações da unidade */}
                    <FormSection title="Informações da unidade">
                        <FormField label="Empresa" required className="lg:col-span-2">
                            <InputWithActions
                                placeholder="ADMIN - CLÍNICA-MARINA GARCIA LOPES"
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </FormField>
                        <FormField label="Nome da Unidade" required className="lg:col-span-1">
                            <input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Situação da Unidade Operacional" required className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Ativo</option>
                                <option>Inativo</option>
                            </select>
                        </FormField>
                        <FormField label="Descrição da Unidade" className="lg:col-span-4">
                            <textarea rows="3" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
                        </FormField>
                        <div className="lg:col-span-4 flex items-center">
                            <input
                                id="use-address"
                                type="checkbox"
                                checked={useCompanyAddress}
                                onChange={() => setUseCompanyAddress(!useCompanyAddress)}
                                className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                            />
                            <label htmlFor="use-address" className="ml-2 text-sm text-gray-700">Utilizar o mesmo endereço da empresa.</label>
                        </div>
                    </FormSection>

                    {/* Seção Setores a serem vinculados */}
                    <FormSection title="Setores a serem vinculados à unidade operacional">
                        <FormField label="Setores" required className="lg:col-span-4">
                            <InputWithActions
                                placeholder=""
                                actions={
                                    <>
                                        <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                        <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </FormField>
                    </FormSection>

                    {/* Seção Endereço (condicional) */}
                    {!useCompanyAddress && (
                        <FormSection title="Endereço">
                            <FormField label="CEP" className="lg:col-span-1"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                            <FormField label="Cidade" className="lg:col-span-2"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                            <FormField label="Estado" className="lg:col-span-1">
                                <select disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100"><option>Não Informado</option></select>
                            </FormField>
                            <FormField label="Logradouro" className="lg:col-span-3"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                            <FormField label="Número" className="lg:col-span-1"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                            <FormField label="Bairro" className="lg:col-span-2"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                            <FormField label="Complemento" className="lg:col-span-1"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                            <FormField label="Região" className="lg:col-span-1"><input type="text" disabled={useCompanyAddress} className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100" /></FormField>
                        </FormSection>
                    )}

                    {/* Seção Informações de Contato */}
                    <FormSection title="Informações de Contato">
                        <FormField label="DDD" className="lg:col-span-1"><input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="DDD" className="lg:col-span-1"><input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Endereço de E-mail" className="lg:col-span-4"><input type="email" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                    </FormSection>

                    {/* Seção Outras Informações */}
                    <FormSection title="Outras Informações">
                        <FormField label="Grau de Risco" className="lg:col-span-1"><select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Não Informado</option></select></FormField>
                        <FormField label="CNAE Principal" className="lg:col-span-3">
                            <InputWithActions actions={
                                <>
                                    <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                    <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                </>
                            }/>
                        </FormField>
                        <FormField label="CNAEs Secundários" className="lg:col-span-4">
                            <InputWithActions actions={
                                <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 rounded-r-md hover:bg-green-600"><Search size={18}/></button>
                            }/>
                        </FormField>
                    </FormSection>

                    {/* Seção Configuração */}
                    <FormSection title="Configuração">
                        <div className="lg:col-span-4 flex items-center">
                            <input id="third-party" type="checkbox" className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" />
                            <label htmlFor="third-party" className="ml-2 text-sm text-gray-700">Unidade está alocada em uma empresa terceira?</label>
                            <Info size={16} className="text-gray-400 ml-1" />
                        </div>
                        <FormField label="Tipo de Empresa" className="lg:col-span-1"><select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Físico</option></select></FormField>
                        <FormField label="Número do CPF" className="lg:col-span-1"><input type="text" disabled className="w-full py-2 px-3 border border-gray-300 rounded-md bg-gray-100" /></FormField>
                        <FormField label="Razão Social" className="lg:col-span-2"><input type="text" disabled className="w-full py-2 px-3 border border-gray-300 rounded-md bg-gray-100" /></FormField>
                    </FormSection>

                    {/* Botões de Ação */}
                    <div className="flex justify-end space-x-4 mt-8">
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
