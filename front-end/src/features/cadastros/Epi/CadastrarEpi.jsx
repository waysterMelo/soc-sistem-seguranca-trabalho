import React, { useState } from 'react';
import {
    AlertCircle,
    Plus,
    Pencil
} from 'lucide-react';

// --- Componentes Reutilizáveis ---

// Um wrapper para seções do formulário
const FormSection = ({ title, children }) => (
    <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <h3 className="text-lg font-semibold text-gray-700 border-b border-gray-200 pb-4 mb-6">{title}</h3>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-x-6 gap-y-6">
            {children}
        </div>
    </div>
);

// Um wrapper para campos de formulário (label + input)
const FormField = ({ label, required, children, className = '', error }) => (
    <div className={`flex flex-col space-y-1.5 ${className}`}>
        <label className="text-sm font-medium text-gray-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
        {error && <p className="text-xs text-red-500 flex items-center mt-1"><AlertCircle size={14} className="mr-1"/>{error}</p>}
    </div>
);

// Input com ícone de erro
const InputWithError = ({ type = 'text', value, onChange, placeholder, error }) => (
    <div className="relative">
        <input
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            className={`w-full py-2 px-3 border rounded-md focus:outline-none focus:ring-2 transition-colors ${error ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'}`}
        />
        {error && (
            <div className="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none">
                <AlertCircle className="h-5 w-5 text-red-500" />
            </div>
        )}
    </div>
);


// --- Componente Principal ---

export default function CadastrarEquipamento() {
    // Exemplo de estado para validação
    const [formData, setFormData] = useState({
        nome: '',
    });
    const [errors, setErrors] = useState({});

    const validateForm = () => {
        const newErrors = {};
        if (!formData.nome) newErrors.nome = 'O campo Nome é obrigatório';
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        validateForm();
        // Lógica de envio se o formulário for válido
    }

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Epi-Epc</h1>
                </header>

                <form onSubmit={handleSubmit}>
                    <FormSection title="Informações Básicas">
                        <FormField label="Nome" required className="md:col-span-3" error={errors.nome}>
                            <InputWithError
                                placeholder="Digite o nome do EPI/EPC"
                                value={formData.nome}
                                onChange={(e) => setFormData({...formData, nome: e.target.value})}
                                error={errors.nome}
                            />
                        </FormField>

                        <FormField label="Tipo" required>
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Não Informado</option>
                                <option>EPI</option>
                                <option>EPC</option>
                            </select>
                        </FormField>
                        <FormField label="Modelo">
                            <input type="text" placeholder="Modelo do equipamento" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                        </FormField>
                        <FormField label="Validade do CA">
                            <input type="text" placeholder="dd/mm/aaaa" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                        </FormField>

                        <div className="md:col-span-3 flex items-center">
                            <input id="pgr-only" type="checkbox" className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" />
                            <label htmlFor="pgr-only" className="ml-2 text-sm text-gray-700">Equipamento será utilizado apenas para PGR</label>
                        </div>

                        <FormField label="Certificado de Avaliação do EPI" required className="md:col-span-3">
                            <input type="text" placeholder="Informe o Certificado de Avaliação do EPI ou a Descrição abaixo" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                        </FormField>

                        <FormField label="Periodicidade De Uso" className="md:col-span-3">
                            <input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                        </FormField>

                        <FormField label="Categoria" className="md:col-span-1">
                            <div className="flex">
                                <select className="w-full py-2 px-3 border border-r-0 border-gray-300 rounded-l-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                    <option>Selecione uma Opção</option>
                                </select>
                                <button type="button" className="p-2.5 bg-green-500 text-white hover:bg-green-600 border border-green-500"><Plus size={18}/></button>
                                <button type="button" className="p-2.5 bg-yellow-400 text-gray-800 hover:bg-yellow-500 border border-yellow-400 rounded-r-md"><Pencil size={18}/></button>
                            </div>
                        </FormField>

                        <FormField label="Fabricante" className="md:col-span-2">
                            <div className="flex">
                                <select className="w-full py-2 px-3 border border-r-0 border-gray-300 rounded-l-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                    <option>Selecione uma Opção</option>
                                </select>
                                <button type="button" className="p-2.5 bg-green-500 text-white hover:bg-green-600 border border-green-500"><Plus size={18}/></button>
                                <button type="button" className="p-2.5 bg-yellow-400 text-gray-800 hover:bg-yellow-500 border border-yellow-400 rounded-r-md"><Pencil size={18}/></button>
                            </div>
                        </FormField>

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
