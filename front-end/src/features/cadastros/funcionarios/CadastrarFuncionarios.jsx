import React from 'react';
import {
    Check,
    HelpCircle
} from 'lucide-react';

// --- Componentes Reutilizáveis ---

// Um wrapper para seções do formulário com margem inferior ajustada
const FormSection = ({ title, children }) => (
    <div className="bg-white p-6 rounded-xl shadow-sm mb-8">
        <h3 className="text-xl font-semibold text-slate-800 border-b border-slate-200 pb-4 mb-6">{title}</h3>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 xl:grid-cols-5 gap-x-6 gap-y-5">
            {children}
        </div>
    </div>
);

// Um wrapper para campos de formulário
const FormField = ({ label, required, children, className = '' }) => (
    <div className={`flex flex-col space-y-1.5 ${className}`}>
        <label className="text-sm font-medium text-slate-600">
            {label} {required && <span className="text-red-500">*</span>}
        </label>
        {children}
    </div>
);

// Input com um ícone de status
const InputWithStatus = ({ defaultValue, status, type = 'text', placeholder }) => (
    <div className="relative">
        <input
            type={type}
            defaultValue={defaultValue}
            placeholder={placeholder}
            className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition-shadow"
        />
        {status === 'valid' && (
            <div className="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none">
                <Check className="h-5 w-5 text-green-500" />
            </div>
        )}
    </div>
);


// --- Componente Principal ---

export default function CadastrarFuncionario() {

    return (
        <div className="bg-slate-50 min-h-screen p-4 sm:p-8 font-sans">
            <div className="container mx-auto ">
                <header className="mb-8">
                    <h1 className="text-4xl font-bold text-slate-900">Cadastrar Funcionário</h1>
                </header>

                <form>
                    {/* Seção Informações Básicas */}
                    <FormSection title="Informações Básicas">
                        <FormField label="Nome" required className="xl:col-span-2"><InputWithStatus defaultValue="Joao" status="valid" /></FormField>
                        <FormField label="Sobrenome" required className="xl:col-span-2"><InputWithStatus defaultValue="mateus" status="valid" /></FormField>
                        <FormField label="Status" required><select defaultValue="Ativo" className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Ativo</option><option>Inativo</option></select></FormField>

                        <FormField label="Número do CPF" required><InputWithStatus defaultValue="123.456.789-09" status="valid" /></FormField>
                        <FormField label="Número do RG"><input type="text" placeholder="Ex: MG-12.345.678" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Orgão Emissor"><input type="text" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Data Emissão"><input type="date" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Estado Emissor"><select className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Selecione o Estado</option></select></FormField>

                        <FormField label="Raça"><select defaultValue="Amarela" className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Amarela</option><option>Branca</option><option>Preta</option><option>Parda</option><option>Indígena</option></select></FormField>
                        <FormField label="Sexo"><select defaultValue="Masculino" className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Masculino</option><option>Feminino</option></select></FormField>
                        <FormField label="Estado Civil"><select defaultValue="Solteiro" className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option></select></FormField>
                        <FormField label="Data de Nascimento"><input type="text" defaultValue="09/07/1993" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Idade"><input type="text" defaultValue="31 ano(s) e 10 mês(es)" disabled className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-slate-100"/></FormField>

                        <FormField label="Nome da Mãe" className="xl:col-span-2"><input type="text" defaultValue="nao informado" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Nome do Pai" className="xl:col-span-3"><input type="text" defaultValue="nao informado" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                    </FormSection>

                    {/* Seção Endereço */}
                    <FormSection title="Endereço">
                        <FormField label="CEP" className="xl:col-span-1"><input type="text" defaultValue="35720-000" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Cidade" className="xl:col-span-2"><input type="text" defaultValue="asdasdasd" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Estado" className="xl:col-span-2"><select defaultValue="Acre" className="w-full py-2 px-3 border border-slate-300 rounded-lg bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"><option>Acre</option></select></FormField>

                        <FormField label="Logradouro" className="xl:col-span-4"><input type="text" defaultValue="casa" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Número" className="xl:col-span-1"><input type="text" defaultValue="1042" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>

                        <FormField label="Bairro" className="xl:col-span-2"><input type="text" defaultValue="cruzeiro" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Complemento" className="xl:col-span-2"><input type="text" defaultValue="casa" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Região" className="xl:col-span-1"><input type="text" defaultValue="zona sul" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                    </FormSection>

                    {/* Seção Informações de Contato */}
                    <FormSection title="Informações de Contato">
                        <FormField label="DDD" required className="xl:col-span-1"><InputWithStatus defaultValue="31" status="valid" /></FormField>
                        <FormField label="Número" className="xl:col-span-1"><input type="text" defaultValue="96255999" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="DDD" className="xl:col-span-1"><input type="text" defaultValue="11" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                        <FormField label="Número" className="xl:col-span-2"><input type="text" defaultValue="710956688" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>

                        <FormField label="Endereço de E-mail" className="xl:col-span-5"><input type="email" defaultValue="joao@gmail.com" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/></FormField>
                    </FormSection>

                    {/* Seção Outras Informações */}
                    <FormSection title="Outras Informações">
                        <FormField label="Observações" className="xl:col-span-5">
                            <textarea defaultValue="nao tem" rows="3" className="w-full py-2 px-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
                        </FormField>
                    </FormSection>

                    {/* Botões de Ação */}
                    <div className="pt-6 mt-2 flex flex-col items-center">
                        <div className="flex items-center mb-6">
                            <input id="create-record" type="checkbox" className="h-4 w-4 text-blue-600 border-slate-300 rounded focus:ring-blue-500" />
                            <label htmlFor="create-record" className="ml-3 text-sm font-medium text-slate-700">Criar registro profissional</label>
                        </div>
                        <div className="flex flex-col sm:flex-row w-full sm:w-auto gap-4">
                            <button type="button" className="w-full sm:w-auto bg-white border border-slate-300 text-slate-700 px-8 py-3 rounded-lg font-semibold hover:bg-slate-50 transition-colors">
                                Cancelar
                            </button>
                            <button type="submit" className="w-full sm:w-auto bg-blue-600 text-white px-8 py-3 rounded-lg font-semibold hover:bg-blue-700 transition-colors shadow-sm">
                                Salvar
                            </button>
                        </div>
                        <button type="button" className="mt-6 flex items-center text-sm text-slate-500 hover:text-blue-600 hover:underline">
                            <HelpCircle size={16} className="mr-1.5"/>
                            Ajude a melhorar o Metra Cloud
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
