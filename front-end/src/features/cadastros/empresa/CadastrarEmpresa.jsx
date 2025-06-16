import React, { useState } from 'react';
import {
    Trash2,
    Search,
    Plus,
    AlertCircle,
    ArrowLeft
} from 'lucide-react';


// Um wrapper para seções do formulário com um título
const FormSection = ({ title, children }) => (
    <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <h3 className="text-xl font-semibold text-gray-800 border-b border-gray-200 pb-4 mb-6">{title}</h3>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            {children}
        </div>
    </div>
);

// Um wrapper para campos de formulário (label + input)
const FormField = ({ label, required, children, className = '', error }) => (
    <div className={`flex flex-col space-y-1 ${className}`}>
        <label className="text-sm font-medium text-gray-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
        {error && <p className="text-xs text-red-500 flex items-center mt-1"><AlertCircle size={14} className="mr-1"/>{error}</p>}
    </div>
);

// Input com ícone ou botão
const InputWithAction = ({ placeholder, actionButton, type = "text" }) => (
    <div className="relative flex items-center">
        <input
            type={type}
            placeholder={placeholder}
            className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 transition-colors"
        />
        <div className="absolute right-0 flex">
            {actionButton}
        </div>
    </div>
);


// --- Componente Principal ---

export default function CadastrarEmpresa() {
    const [formData, setFormData] = useState({
        razaoSocial: '',
        // adicione outros campos aqui
    });

    const [error, setError] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!formData.razaoSocial) {
            setError('O campo Razão Social é obrigatório');
            return;
        }
        setError(null);
        // Lógica de envio do formulário
        console.log('Formulário enviado:', formData);
        alert('Empresa cadastrada com sucesso!');
    };

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <button className="flex items-center text-gray-600 hover:text-gray-900 mb-4">
                        <ArrowLeft size={20} className="mr-2" />
                        Voltar para a lista
                    </button>
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar Empresa</h1>
                </header>

                <form onSubmit={handleSubmit}>
                    {/* Seção de Informações Básicas */}
                    <FormSection title="Informações Básicas">
                        <FormField label="Tipo de Empresa" required className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Selecione</option>
                                <option>Pessoa Jurídica</option>
                                <option>Pessoa Física</option>
                            </select>
                        </FormField>
                        <FormField label="Número do CPF/CNPJ" required className="lg:col-span-1">
                            <input type="text" placeholder="Ex. 00.000.000/0000-00" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Inscrição Estadual" className="lg:col-span-1">
                            <input type="text" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Status" required className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Ativo</option>
                                <option>Inativo</option>
                            </select>
                        </FormField>
                        <FormField label="Razão Social" required className="lg:col-span-2" error={error}>
                            <input
                                type="text"
                                placeholder="Digite a razão social da clínica"
                                className={`w-full py-2 px-3 border rounded-md focus:outline-none focus:ring-2 transition-colors ${error ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'}`}
                                value={formData.razaoSocial}
                                onChange={(e) => {
                                    setFormData({...formData, razaoSocial: e.target.value});
                                    if (e.target.value) setError(null);
                                }}
                            />
                        </FormField>
                        <FormField label="Nome Fantasia" className="lg:col-span-2">
                            <input type="text" placeholder="Digite o nome Fantasia da Clínica" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Logomarca" className="lg:col-span-4">
                            <div className="flex items-center">
                                <label className="flex items-center w-full py-2 px-3 border border-gray-300 rounded-l-md bg-white cursor-pointer">
                                    <span className="text-gray-500">Selecione uma imagem</span>
                                    <input type="file" className="hidden" />
                                </label>
                                <span className="bg-gray-100 border-t border-b border-gray-300 text-gray-700 py-2 px-4 text-sm font-medium">Browse</span>
                                <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600">
                                    <Trash2 size={18}/>
                                </button>
                            </div>
                        </FormField>
                    </FormSection>

                    {/* Seção de Endereço */}
                    <FormSection title="Endereço">
                        <FormField label="CEP" className="lg:col-span-1">
                            <input type="text" placeholder="Ex. 37701-000" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Cidade" className="lg:col-span-2">
                            <input type="text" placeholder="Ex. Poços de Caldas" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Estado" className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Não Informado</option>
                                <option>Minas Gerais</option>
                                <option>São Paulo</option>
                            </select>
                        </FormField>
                        <FormField label="Logradouro" className="lg:col-span-3">
                            <input type="text" placeholder="Digite o Endereço" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Número" className="lg:col-span-1">
                            <input type="text" placeholder="Ex. 0000" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Bairro" className="lg:col-span-2">
                            <input type="text" placeholder="Ex. Centro" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Complemento" className="lg:col-span-1">
                            <input type="text" placeholder="Ex. Apto 22" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                        <FormField label="Região" className="lg:col-span-1">
                            <input type="text" placeholder="Ex. Zona Sul" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                    </FormSection>

                    {/* Seção de Contato */}
                    <FormSection title="Informações de Contato">
                        <FormField label="DDD" className="lg:col-span-1"><input type="text" placeholder="Ex. 35" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" placeholder="Ex. 987654321" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="DDD" className="lg:col-span-1"><input type="text" placeholder="Ex. 35" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Número" className="lg:col-span-1"><input type="text" placeholder="Ex. 987654321" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" /></FormField>
                        <FormField label="Endereço de E-mail" className="lg:col-span-4">
                            <input type="email" placeholder="Ex. exemplo@email.com.br" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                        </FormField>
                    </FormSection>

                    {/* Outras Informações */}
                    <FormSection title="Outras Informações">
                        <FormField label="Grau de Risco" className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Não Informado</option></select>
                        </FormField>
                        <FormField label="CNAE Principal" className="lg:col-span-3">
                            <InputWithAction placeholder="0111-3/99 - Cultivo de outros..." actionButton={
                                <>
                                    <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                    <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                </>
                            }/>
                        </FormField>
                        <FormField label="CNAEs Secundários" className="lg:col-span-4">
                            <InputWithAction placeholder="Nenhum CNAE selecionado" actionButton={
                                <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 rounded-r-md hover:bg-green-600"><Search size={18}/></button>
                            }/>
                        </FormField>
                        <FormField label="Matriz/Filial" className="lg:col-span-1">
                            <select className="w-full py-2 px-3 border border-gray-300 rounded-md bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Matriz</option></select>
                        </FormField>
                        <FormField label="Razão Social da Empresa Matriz" className="lg:col-span-3">
                            <InputWithAction placeholder="Não possui empresa Matriz selecionada" actionButton={
                                <>
                                    <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                    <button type="button" className="bg-red-500 text-white p-2.5 border border-red-500 rounded-r-md hover:bg-red-600"><Trash2 size={18}/></button>
                                </>
                            }/>
                        </FormField>
                        <FormField label="Médico Responsável pelo PCMSO" className="lg:col-span-4">
                            <InputWithAction placeholder="Nenhum médico coordenador selecionado" actionButton={
                                <>
                                    <button type="button" className="bg-green-500 text-white p-2.5 border border-green-500 hover:bg-green-600"><Search size={18}/></button>
                                    <button type="button" className="bg-blue-500 text-white p-2.5 border border-blue-500 rounded-r-md hover:bg-blue-600"><Plus size={18}/></button>
                                </>
                            }/>
                        </FormField>
                        <FormField label="Observações" className="lg:col-span-4">
                            <textarea placeholder="Digite as observações sobre esta empresa" rows="4" className="w-full py-2 px-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
                        </FormField>
                    </FormSection>

                    {/* Botões de Ação */}
                    <div className="flex justify-end space-x-4">
                        <button type="button" className="bg-white border border-gray-300 text-gray-700 px-6 py-2 rounded-md text-sm font-medium hover:bg-gray-50 transition-colors">
                            Cancelar
                        </button>
                        <button type="submit" className="bg-blue-600 text-white px-6 py-2 rounded-md text-sm font-medium hover:bg-blue-700 transition-colors">
                            Salvar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
