import React, { useState } from 'react';
import {
    Search,
    Trash2,
    AlertCircle,
    HelpCircle
} from 'lucide-react';

// --- Componentes Reutilizáveis ---

// Um wrapper para seções do formulário
const FormSection = ({ title, children }) => (
    <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <h3 className="text-lg font-semibold text-gray-700 border-b border-gray-200 pb-4 mb-6">{title}</h3>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-6 gap-y-6">
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

// Input com botões de ação
const InputWithActions = ({ placeholder, actions, error }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            className={`w-full py-2 pl-4 pr-20 border rounded-md focus:outline-none transition-colors bg-white focus:ring-2 ${error ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'}`}
        />
        <div className="absolute right-0 flex">
            {actions}
        </div>
    </div>
);


// --- Componente Principal ---

export default function CadastrarPrestador() {
    // Exemplo de estado para validação
    const [formData, setFormData] = useState({
        nome: '',
        sobrenome: '',
        cbo: '',
        numeroConselho: '',
        estadoConselho: ''
    });
    const [errors, setErrors] =useState({});

    const validateForm = () => {
        const newErrors = {};
        if (!formData.nome) newErrors.nome = 'O campo Nome é obrigatório';
        if (!formData.sobrenome) newErrors.sobrenome = 'O campo Sobrenome é obrigatório';
        if (!formData.cbo) newErrors.cbo = 'O campo CBO é obrigatório';
        if (!formData.numeroConselho) newErrors.numeroConselho = 'O campo Número de Inscrição do Conselho é obrigatório';
        if (!formData.estadoConselho) newErrors.estadoConselho = 'O campo Estado é obrigatório';
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateForm()) {
            console.log("Formulário Válido, enviando...", formData);
            // Lógica para submeter o formulário
        } else {
            console.log("Erros de validação", errors);
        }
    }


    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Profissional</h1>
                </header>

                <form onSubmit={handleSubmit}>
                    {/* Seção Informações Básicas */}
                    <FormSection title="Informações Básicas">
                        <FormField label="Nome" required className="lg:col-span-2" error={errors.nome}>
                            <InputWithError
                                placeholder="Primeiro Nome"
                                value={formData.nome}
                                onChange={(e) => setFormData({...formData, nome: e.target.value})}
                                error={errors.nome}
                            />
                        </FormField>
                        <FormField label="Sobrenome" required className="lg:col-span-2" error={errors.sobrenome}>
                            <InputWithError
                                placeholder="Sobrenome"
                                value={formData.sobrenome}
                                onChange={(e) => setFormData({...formData, sobrenome: e.target.value})}
                                error={errors.sobrenome}
                            />
                        </FormField>

                        <FormField label="Documento" className="lg:col-span-1"><select defaultValue="cpf" className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option value="cpf">CPF</option><option value="outro">Outro</option></select></FormField>
                        <FormField label="Número do CPF" className="lg:col-span-1"><input type="text" placeholder="000.000.000-00" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Sexo" className="lg:col-span-1"><select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Selecione uma Opção</option><option>Masculino</option><option>Feminino</option></select></FormField>
                        <FormField label="Órgão Emissor RG" className="lg:col-span-1"><input type="text" placeholder="Ex: SSP/MG" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Número do RG" className="lg:col-span-4"><input type="text" placeholder="Ex: MG-12.345.678" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <p className="text-xs text-gray-500 lg:col-span-4">* O CPF é obrigatório para Médico Responsável pelo PCMSO e para Responsável pelos Registros Ambientais.</p>
                    </FormSection>

                    {/* Seção Endereço */}
                    <FormSection title="Endereço">
                        <FormField label="CEP" className="lg:col-span-1"><input type="text" placeholder="Ex. 37701-000" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Cidade" className="lg:col-span-2"><input type="text" placeholder="Ex. Poços de Caldas" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Estado" className="lg:col-span-1"><select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Não Informado</option></select></FormField>
                        <FormField label="Logradouro" className="lg:col-span-3"><input type="text" placeholder="Digite o Endereço" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" placeholder="Ex. 0000" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Bairro" className="lg:col-span-2"><input type="text" placeholder="Ex. Centro" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Complemento" className="lg:col-span-1"><input type="text" placeholder="Ex. Apto 22" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Região" className="lg:col-span-1"><input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                    </FormSection>

                    {/* Seção Informações de Contato */}
                    <FormSection title="Informações de Contato">
                        <FormField label="DDD" className="lg:col-span-1"><input type="text" placeholder="Ex. 35" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" placeholder="Ex. 987654321" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="DDD" className="lg:col-span-1"><input type="text" placeholder="Ex. 35" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" placeholder="Ex. 987654321" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Endereço de E-mail" className="lg:col-span-4"><input type="email" placeholder="Ex. exemplo@email.com.br" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                    </FormSection>

                    {/* Seção Outras Informações */}
                    <FormSection title="Outras Informações">
                        <FormField label="CBO" required className="lg:col-span-4" error={errors.cbo}>
                            <InputWithActions
                                placeholder="0101-05 - Oficial General da Aeronáutica"
                                error={errors.cbo}
                                actions={
                                    <>
                                        <button type="button" className="p-2.5 text-gray-500 hover:text-green-600"><Search size={18}/></button>
                                        <button type="button" className="p-2.5 text-gray-500 hover:text-red-600"><Trash2 size={18}/></button>
                                    </>
                                }
                            />
                        </FormField>
                        <FormField label="Número do NIS" className="lg:col-span-1"><input type="text" placeholder="Número PIS, PASEP ou NIT" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Conselho" className="lg:col-span-1"><select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Selecione</option></select></FormField>
                        <FormField label="Número de Inscrição do Conselho" required className="lg:col-span-1" error={errors.numeroConselho}>
                            <InputWithError
                                placeholder="Ex. 00000"
                                value={formData.numeroConselho}
                                onChange={(e) => setFormData({...formData, numeroConselho: e.target.value})}
                                error={errors.numeroConselho}
                            />
                        </FormField>
                        <FormField label="Estado" required className="lg:col-span-1" error={errors.estadoConselho}>
                            <select
                                className={`w-full py-2 px-3 border rounded-md bg-white focus:outline-none focus:ring-2 ${errors.estadoConselho ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'}`}
                                onChange={(e) => setFormData({...formData, estadoConselho: e.target.value})}
                                value={formData.estadoConselho}
                            >
                                <option value="">Selecione um estado</option>
                                <option value="MG">Minas Gerais</option>
                                <option value="SP">São Paulo</option>
                            </select>
                        </FormField>
                        <p className="text-xs text-gray-500 lg:col-span-4">* Caso o prestador seja vinculado à algum evento de envio para eSocial é necessário inserir o Conselho.</p>
                    </FormSection>

                    {/* Botões de Ação */}
                    <div className="flex flex-col items-center mt-8">
                        <div className="flex flex-wrap justify-center gap-4">
                            <button type="button" className="bg-red-600 text-white px-8 py-2.5 rounded-md font-semibold hover:bg-red-700 transition-colors">
                                Cancelar
                            </button>
                            <button type="submit" className="bg-green-600 text-white px-8 py-2.5 rounded-md font-semibold hover:bg-green-700 transition-colors">
                                Salvar
                            </button>
                        </div>
                        <button type="button" className="mt-6 flex items-center text-sm text-blue-600 hover:underline">
                            <HelpCircle size={16} className="mr-1.5"/>
                            Ajude a melhorar o Metra Cloud
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
